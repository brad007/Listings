package com.oneafricamedia.classifieds.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.oneafricamedia.classifieds.CarComparator;
import com.oneafricamedia.classifieds.R;
import com.oneafricamedia.classifieds.ViewWeightingsActivity;
import com.oneafricamedia.classifieds.adapters.SwipeStackAdapter;
import com.oneafricamedia.classifieds.models.Car;
import com.oneafricamedia.classifieds.widget.swipestack.SwipeStack;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;


public class MainActivity extends AppCompatActivity implements
        SwipeStack.SwipeStackListener,
        SwipeStack.SwipeProgressListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    public static HashMap<String, Integer> titleMap = new HashMap<>();
    public static HashMap<String, Integer> modelMap = new HashMap<>();
    public static HashMap<String, Integer> priceMap = new HashMap<>();
    public static HashMap<String, Integer> negotiableMap = new HashMap<>();
    public static HashMap<String, Integer> yearMap = new HashMap<>();

    public int PAGE_COUNTER = 1;

    private FrameLayout frameLayout;
    //   private SwipeStack swipeStack;
    private ArrayList<Car> list;
    private SwipeStack swipeStack;
    private SwipeStackAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        swipeStack = (SwipeStack) findViewById(R.id.swipeStack);
        swipeStack.setListener(this);
        swipeStack.setSwipeProgressListener(this);
        list = new ArrayList<>();
        new FetchDataTask(PAGE_COUNTER).execute();
    }


    @Override
    public void onViewSwipedToLeft(int position) {
        Car car = (Car) adapter.getItem(position);
        manageWeights(car, false);
        sort(list);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onViewSwipedToRight(int position) {
        Car car = (Car) adapter.getItem(position);
        manageWeights(car, true);
        sort(list);
        adapter.notifyDataSetChanged();
    }


    private void manageWeightsHelper(HashMap<String, Integer> map, String key, boolean isRightSwipe) {
        //We need to give listings the users like a negative weighting, since sorting is done in
        //ascending order. But the weightings should be interpreted as the lowest(possibly a negative
        //value)weighting is the users estimated most liked it from the list.

        if (map.containsKey(key)) {
            if (isRightSwipe) {
                map.put(key, map.get(key) - 1);
            } else {
                map.put(key, map.get(key) + 1);
            }
        } else {
            if (isRightSwipe) {
                map.put(key, -1);
            } else {
                map.put(key, 1);
            }
        }
    }

    private void manageWeights(Car carParam, boolean isRightSwipe) {
        manageWeightsHelper(titleMap, carParam.getMake(), isRightSwipe);
        manageWeightsHelper(modelMap, carParam.getModel(), isRightSwipe);
        manageWeightsHelper(priceMap, carParam.getPrice() + "", isRightSwipe);
        manageWeightsHelper(negotiableMap, carParam.isNegotiable() + "", isRightSwipe);
        manageWeightsHelper(yearMap, carParam.getYear() + "", isRightSwipe);
    }

    @Override
    public void onStackEmpty() {
    }


    @Override
    public void onSwipeStart(int position) {
        //if the position is 5 cards away from the last, load more.
        if ((position + 10) % 30 == 0) {
            Log.v(TAG, "progress");
            PAGE_COUNTER++;
            new LoadMore(PAGE_COUNTER).execute();
            adapter.notifyDataSetChanged();
        }
        //    Log.v(TAG, position + "");
    }

    @Override
    public void onSwipeProgress(int position, float progress) {

        View view = swipeStack.getTopView();
        if (progress >= 0) {
            frameLayout = (FrameLayout) view.findViewById(R.id.like_gradient);
            frameLayout.setAlpha(progress);
        } else {
            frameLayout = (FrameLayout) view.findViewById(R.id.dislike_gradient);
            frameLayout.setAlpha(-1 * progress);
        }
    }

    @Override
    public void onSwipeEnd(int position) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_refresh) {
            list.clear();
            adapter.notifyDataSetChanged();
            new FetchDataTask(PAGE_COUNTER).execute();
            return true;
        } else if (item.getItemId() == R.id.action_weightings) {
            startActivity(new Intent(MainActivity.this, ViewWeightingsActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    public class FetchDataTask extends AsyncTask<Void, Void, Void> {

        private final int offset;

        public FetchDataTask(int offset) {

            this.offset = offset;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            loadData(offset);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {

            adapter = new SwipeStackAdapter(list, MainActivity.this);
            adapter.notifyDataSetChanged();
            swipeStack.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
    }

    public class LoadMore extends AsyncTask<Void, Void, Void> {

        private int offset;

        LoadMore(int offset) {

            this.offset = offset;
        }

        @Override
        protected Void doInBackground(Void... params) {
            loadData(offset);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            adapter.notifyDataSetChanged();
        }
    }


    private void loadData(int offset) {

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String checkiCarString;


        Uri builtUri = Uri.parse(getString(R.string.checki_url) + offset);

        URL url;
        try {
            url = new URL(builtUri.toString());
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setRequestProperty("Authorization", "Basic MWE4M2Y1NmRmQG9hbS5jb29sOkFoWHpLcUQ1");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                //Nothing to do
                return;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line + "\n");
            }

            if (buffer.length() == 0) {
                return;
            }


            checkiCarString = buffer.toString();
            Log.v(TAG, checkiCarString);
            JSONObject jsonObject = new JSONObject(checkiCarString);

            // Log.v(TAG, jsonObject.getJSONArray("data").toString());
            JSONArray vehiclesArray = jsonObject.getJSONArray("data");
            //list = new ArrayList<>();
            for (int i = 0; i < vehiclesArray.length(); i++) {
                JSONObject vehicleJson = vehiclesArray.getJSONObject(i);
                String title = vehicleJson.getString("title");

                String imageUrl = vehicleJson.getString("default_image");

                JSONObject makeJson = vehicleJson.getJSONObject("make");
                String make = makeJson.getString("title");

                JSONObject modelJson = vehicleJson.getJSONObject("model");
                String model = modelJson.getString("title");

                int price = vehicleJson.getInt("price");

                String currency = vehicleJson.getString("currency_symbol");

                boolean isNegotiable = vehicleJson.getBoolean("is_negotiable");

                JSONObject conditionJson = vehicleJson.getJSONObject("condition");
                String condition = conditionJson.getString("title");

                int year = vehicleJson.getInt("year");

                Car car = new Car();
                car.setTitle(title);
                car.setImageUrl(imageUrl);
                car.setMake(make);
                car.setModel(model);
                car.setPrice(price);
                car.setCurrency(currency);
                car.setNegotiable(isNegotiable);
                car.setCondition(condition);
                car.setYear(year);

                //    Log.v(TAG, car.toString());

                list.add(car);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e("MainActivity", "Error closing stream", e);
                }
            }
        }

        if (titleMap != null) {
            sort(list);
        }
    }

    private void sort(ArrayList<Car> list) {
        for (Car car : list) {
            car.setWeight(assignWeight(car));
        }
        Collections.sort(list, new CarComparator());
    }

    private int assignWeight(Car car) {
        int weight = 0;

        if (titleMap.containsKey(car.getModel())) {
            weight += titleMap.get(car.getModel());
        }

        if (modelMap.containsKey(car.getModel())) {
            weight += modelMap.get(car.getModel());
        }

        if (priceMap.containsKey(car.getPrice() + "")) {
            weight += priceMap.get(car.getPrice() + "");
        }

        if (negotiableMap.containsKey(car.isNegotiable() + "")) {
            weight += negotiableMap.get(car.isNegotiable() + "");
        }

        if (yearMap.containsKey(car.getYear() + "")) {
            weight += yearMap.get(car.getYear() + "");
        }

        //    Log.v(TAG, car.getTitle() + ": " + weight);
        return weight;
    }
}

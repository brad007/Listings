package com.oneafricamedia.classifieds.ui;

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

    HashMap<String, Integer> titleMap = new HashMap<>();
    HashMap<Integer, Integer> modelMap = new HashMap<>();
    HashMap<Integer, Integer> priceMap = new HashMap<>();
    HashMap<Boolean, Integer> negotiableMap = new HashMap<>();
    HashMap<Integer, Integer> yearMap = new HashMap<>();

    private FrameLayout frameLayout;
    //   private SwipeStack swipeStack;
    private ArrayList<Car> list;
    private SwipeStack swipeStack;
    private SwipeStackAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new FetchData().execute();
        swipeStack = (SwipeStack) findViewById(R.id.swipeStack);
        swipeStack.setListener(this);
        swipeStack.setSwipeProgressListener(this);

    }


    @Override
    public void onViewSwipedToLeft(int position) {
        Car car = (Car) adapter.getItem(position);
        manageWeights(car, true);
    }

    @Override
    public void onViewSwipedToRight(int position) {
        Car car = (Car) adapter.getItem(position);
        manageWeights(car, false);
    }


    private void manageWeights(Car carParam, boolean rightSwipe) {

        if (titleMap.containsKey(carParam.getTitle().split(" ")[0])) {
            if (rightSwipe) {
                titleMap.put(carParam.getTitle().split(" ")[0], titleMap.get(carParam.getTitle().split(" ")[0]) + 1);
            } else {
                titleMap.put(carParam.getTitle().split(" ")[0], titleMap.get(carParam.getTitle().split(" ")[0]) + 1);
            }
        } else {
            if (rightSwipe) {
                titleMap.put(carParam.getTitle().split(" ")[0], 1);
            } else {
                titleMap.put(carParam.getTitle().split(" ")[0], -1);
            }
        }

        if (modelMap.containsKey(carParam.getModelId())) {
            if (rightSwipe) {
                modelMap.put(carParam.getModelId(), modelMap.get(carParam.getModelId()) + 1);
            } else {
                modelMap.put(carParam.getModelId(), modelMap.get(carParam.getModelId()) + 1);
            }
        } else {
            if (rightSwipe) {
                modelMap.put(carParam.getModelId(), 1);
            } else {
                modelMap.put(carParam.getModelId(), -1);
            }
        }

        if (priceMap.containsKey(carParam.getPrice())) {
            if (rightSwipe) {
                priceMap.put(carParam.getPrice(), priceMap.get(carParam.getPrice()) + 1);
            } else {
                priceMap.put(carParam.getPrice(), priceMap.get(carParam.getPrice()) + 1);
            }
        } else {
            if (rightSwipe) {
                priceMap.put(carParam.getPrice(), 1);
            } else {
                priceMap.put(carParam.getPrice(), -1);
            }
        }

        if (negotiableMap.containsKey(carParam.isNegotiable())) {
            if (rightSwipe) {
                negotiableMap.put(carParam.isNegotiable(), negotiableMap.get(carParam.isNegotiable()) + 1);
            } else {
                negotiableMap.put(carParam.isNegotiable(), negotiableMap.get(carParam.isNegotiable()) + 1);
            }
        } else {
            if (rightSwipe) {
                negotiableMap.put(carParam.isNegotiable(), 1);
            } else {
                negotiableMap.put(carParam.isNegotiable(), -1);
            }
        }

        if (yearMap.containsKey(carParam.getYear())) {
            if (rightSwipe) {
                yearMap.put(carParam.getYear(), yearMap.get(carParam.getYear()) + 1);
            } else {
                yearMap.put(carParam.getYear(), yearMap.get(carParam.getYear()) + 1);
            }
        } else {
            if (rightSwipe) {
                yearMap.put(carParam.getYear(), 1);
            } else {
                yearMap.put(carParam.getYear(), -1);
            }
        }
    }

    @Override
    public void onStackEmpty() {
        list.clear();
        adapter.notifyDataSetChanged();
        new FetchData().execute();
    }

    @Override
    public void onSwipeStart(int position) {

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
            new FetchData().execute();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public class FetchData extends AsyncTask<Void, Void, Void> {

        private HttpURLConnection urlConnection;
        private BufferedReader reader;
        private String checkiCarString;

        @Override
        protected Void doInBackground(Void... voids) {
            Uri builtUri = Uri.parse(getString(R.string.checki_url));

            URL url = null;
            try {
                url = new URL(builtUri.toString());
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    //Nothing to do
                    return null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line + "\n");
                }

                if (buffer.length() == 0) {
                    return null;
                }


                checkiCarString = buffer.toString();
                JSONObject jsonObject = new JSONObject(checkiCarString);

                JSONObject vehiclesJson = jsonObject.getJSONObject("vehicles");


                JSONArray jsonPath = jsonObject.getJSONObject("images").getJSONArray("path");
                JSONArray jsonSlug = vehiclesJson.getJSONArray("slug");
                JSONArray jsonTitles = vehiclesJson.getJSONArray("title");
                JSONArray jsonModel_id = vehiclesJson.getJSONArray("model_id");
                JSONArray jsonPrice = vehiclesJson.getJSONArray("price");
                JSONArray jsonCurrency = vehiclesJson.getJSONArray("currency_symbol");
                JSONArray jsonNegotiable = vehiclesJson.getJSONArray("is_negotiable");
                JSONArray jsonYear = vehiclesJson.getJSONArray("year");
                list = new ArrayList<>();
                for (int i = 0; i < jsonTitles.length(); i++) {
                    String title = jsonTitles.getString(i);
                    int modelId = jsonModel_id.getInt(i);
                    int price = jsonPrice.getInt(i);
                    String currency = jsonCurrency.getString(i);
                    boolean negotiable = jsonNegotiable.getBoolean(i);
                    int year = jsonYear.getInt(i);
                    String slug = jsonSlug.getString(i).split("/")[1];
                    //vehicles/1265481
                    String imageUrl;
                    int counter = 0;
                    while (!(jsonPath.getString(counter).contains(slug) && jsonPath.getString(counter).contains("1_inventory"))) {
                        counter++;
                    }


                    Car car = new Car(jsonPath.getString(counter), title, modelId, price, currency, negotiable, year);
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

    private void sort(ArrayList<Car> list) {
        Log.v("MainActivity", "sorting right now");
        Log.v("MainActivity", "list size" + list.size());
        int counter = 0;
        for (Car car : list) {
            car.setWeight(assignWeight(car));
            Log.v("MainActivity", "Counter" + counter++);
        }
        Log.v("MainActivity", "weights set");
        Collections.sort(list, new CarComparator());
        Log.v("MainActivity","sorted");
    }

    private int assignWeight(Car car) {
        int weight = 0;

        if (titleMap.containsKey(car.getTitle())) {
            weight += titleMap.get(car.getTitle());
        }

        if (modelMap.containsKey(car.getModelId())) {
            weight += modelMap.get(car.getModelId());
        }

        if (priceMap.containsKey(car.getPrice())) {
            weight += priceMap.get(car.getPrice());
        }

        if (negotiableMap.containsKey(car.isNegotiable())) {
            weight += negotiableMap.get(car.isNegotiable());
        }

        if (yearMap.containsKey(car.getYear())) {
            weight += yearMap.get(car.getYear());
        }

        return weight;
    }
}

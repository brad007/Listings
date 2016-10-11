package com.oneafricamedia.classifieds;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;

import static com.oneafricamedia.classifieds.ui.MainActivity.modelMap;
import static com.oneafricamedia.classifieds.ui.MainActivity.negotiableMap;
import static com.oneafricamedia.classifieds.ui.MainActivity.priceMap;
import static com.oneafricamedia.classifieds.ui.MainActivity.titleMap;
import static com.oneafricamedia.classifieds.ui.MainActivity.yearMap;

public class ViewWeightingsActivity extends AppCompatActivity {


    private ListView weightingListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_weightings);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        weightingListView = (ListView) findViewById(R.id.weightings_listview);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(
                ViewWeightingsActivity.this,
                R.layout.item_layout_weightings,
                R.id.weight_item,
                createList()
        );

        weightingListView.setAdapter(arrayAdapter);
    }

    private ArrayList<String> createList() {
        ArrayList<String> arrayList = new ArrayList<>();

        arrayList.add("Title");
        for (HashMap.Entry<String, Integer> entry : titleMap.entrySet()) {
            String key = entry.getKey();
            arrayList.add(key + " : " + titleMap.get(key));
        }

        arrayList.add("Model");
        for (HashMap.Entry<String, Integer> entry : modelMap.entrySet()) {
            String key = entry.getKey();
            arrayList.add(key + " : " + modelMap.get(key));
        }

        arrayList.add("Price");
        for (HashMap.Entry<String, Integer> entry : priceMap.entrySet()) {
            String key = entry.getKey();
            arrayList.add(key + " : " + priceMap.get(key));
        }

        arrayList.add("Negiotable");
        for (HashMap.Entry<String, Integer> entry : negotiableMap.entrySet()) {
            String key = entry.getKey();
            arrayList.add(key + " : " + negotiableMap.get(key));
        }

        arrayList.add("Year");
        for (HashMap.Entry<String, Integer> entry : yearMap.entrySet()) {
            String key = entry.getKey();
            arrayList.add(key + " : " + yearMap.get(key));
        }

        return arrayList;
    }

}

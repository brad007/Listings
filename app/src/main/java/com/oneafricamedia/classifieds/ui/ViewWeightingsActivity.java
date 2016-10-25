package com.oneafricamedia.classifieds.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import com.oneafricamedia.classifieds.R;

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


    }


}

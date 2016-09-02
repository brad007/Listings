package com.oneafricamedia.classifieds;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by brad on 2016/09/02.
 */
public class SwipeStackAdapter extends BaseAdapter {

    private ArrayList<Cars> mData;
    private Activity activity;


    public SwipeStackAdapter(ArrayList<Cars> data, Activity activity) {
        this.mData = data;

        this.activity = activity;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int i) {
        return mData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = activity.getLayoutInflater().inflate(R.layout.card, viewGroup, false);

        ImageView carImage = (ImageView) view.findViewById(R.id.card_image);
        TextView carTitle = (TextView) view.findViewById(R.id.car_title);
        TextView carPrice = (TextView) view.findViewById(R.id.car_price);

        Cars car = mData.get(i);
        carImage.setImageResource(car.getCarImage());
        carTitle.setText(car.getCarName());
        carPrice.setText(car.getCarCost());
        return view;
    }
}

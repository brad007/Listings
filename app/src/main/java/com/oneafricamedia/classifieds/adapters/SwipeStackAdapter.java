package com.oneafricamedia.classifieds.adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.oneafricamedia.classifieds.R;
import com.oneafricamedia.classifieds.models.Car;

import java.util.ArrayList;

/**
 * Created by brad on 2016/09/02.
 */
public class SwipeStackAdapter extends BaseAdapter {

    private ArrayList<Car> mData;
    private Activity activity;


    public SwipeStackAdapter(ArrayList<Car> data, Activity activity) {
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
        TextView priceNegotiableText = (TextView)view.findViewById(R.id.negotiable_text);

        Car car = mData.get(i);
        carImage.setImageResource(car.getImage());
        carTitle.setText(car.getName());
        carPrice.setText(car.getCost());

        if(car.isNegotiable()){
            priceNegotiableText.setText("(Negotiable)");
        }else{
            priceNegotiableText.setText("(Non Negotiable)");
        }
        return view;
    }
}

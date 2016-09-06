package com.oneafricamedia.classifieds.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import com.oneafricamedia.classifieds.R;
import com.oneafricamedia.classifieds.adapters.SwipeStackAdapter;
import com.oneafricamedia.classifieds.models.Car;
import com.oneafricamedia.classifieds.widget.swipestack.SwipeStack;

import java.util.ArrayList;



public class MainActivity extends AppCompatActivity implements
        SwipeStack.SwipeStackListener,
        SwipeStack.SwipeProgressListener {

    private int[] car_images = new int[]{
            R.drawable.car,
            R.drawable.car2,
            R.drawable.car3,
            R.drawable.car4,
            R.drawable.car5,
            R.drawable.car6,
            R.drawable.car7,
            R.drawable.car8,
            R.drawable.car9,
            R.drawable.car10
    };

    private String[] car_names = new String[]{
            "Mercedes Benz C180",
            "Mercedes Benz Actros",
            "Mercedes Benz ML350",
            "Toyota Premio",
            "BMW X1",
            "Nissan juke",
            "Toyota Land Cruiser",
            "Toyota Mark X",
            "Toyota Land Cruiser",
            "Toyota Land Cruiser Prado"
    };

    private String[] car_prices = new String[]{
            "USh155,000,000",
            "USh189,000,000",
            "USh146,000,000",
            "USh163,000,000",
            "USh172,000,000",
            "USh181,000,000",
            "USh193,000,000",
            "USh159,000,000",
            "USh157,000,000",
            "USh152,000,000"
    };
    private FrameLayout frameLayout;
    private SwipeStack swipeStack;
    private ArrayList<Car> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        swipeStack = (SwipeStack) findViewById(R.id.swipeStack);
        swipeStack.setListener(this);
        swipeStack.setSwipeProgressListener(this);
        createData();
        swipeStack.setAdapter(new SwipeStackAdapter(list, MainActivity.this));
    }

    public void createData() {
        list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            int random = (int) (Math.random() * 10);
            int random1 = (int) (Math.random() * 10);
            int random2 = (int) (Math.random() * 10);
            int random3 = (int) (Math.random() * 1);
            list.add(new Car(car_images[random], car_names[random1], car_prices[random2], random3 == 0));
        }
    }


    @Override
    public void onViewSwipedToLeft(int position) {
        Log.v("MainActivity:left", position + "");
    }

    @Override
    public void onViewSwipedToRight(int position) {
        Log.v("MainActivity:right", position + "");
    }

    @Override
    public void onStackEmpty() {
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
}

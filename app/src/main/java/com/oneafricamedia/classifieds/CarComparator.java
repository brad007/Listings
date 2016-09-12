package com.oneafricamedia.classifieds;

import com.oneafricamedia.classifieds.models.Car;

import java.util.Comparator;

/**
 * Created by brad on 2016/09/12.
 */
public class CarComparator implements Comparator<Car> {
    @Override
    public int compare(Car car, Car t1) {
        if (car.getWeight() > t1.getWeight()) {
            return 1;
        }else if(car.getWeight() == t1.getWeight()){
            return 0;
        }
        return -1;
    }
}

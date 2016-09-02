package com.oneafricamedia.classifieds;

/**
 * Created by brad on 2016/09/02.
 */
public class Cars {


    private int carImage;
    private String carName;
    private String carCost;

    public Cars(int carImage, String carName, String carCost) {
        this.carImage = carImage;
        this.carName = carName;
        this.carCost = carCost;
    }

    public int getCarImage() {
        return carImage;
    }

    public void setCarImage(int carImage) {
        this.carImage = carImage;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getCarCost() {
        return carCost;
    }

    public void setCarCost(String carCost) {
        this.carCost = carCost;
    }
}




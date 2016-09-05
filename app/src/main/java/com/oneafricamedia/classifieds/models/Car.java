package com.oneafricamedia.classifieds.models;

/**
 * Created by brad on 2016/09/02.
 */
public class Car {
    private int image;
    private String name;
    private String cost;
    private boolean negotiable;

    public Car(int image, String name, String cost, boolean negotiable) {
        this.image = image;
        this.name = name;
        this.cost = cost;
        this.negotiable = negotiable;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public boolean isNegotiable() {
        return negotiable;
    }

    public void setNegotiable(boolean negotiable) {
        this.negotiable = negotiable;
    }
}




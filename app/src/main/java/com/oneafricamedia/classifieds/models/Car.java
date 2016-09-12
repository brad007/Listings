package com.oneafricamedia.classifieds.models;

/**
 * Created by brad on 2016/09/02.
 */
public class Car{
    private String imageUrl;
    private String title;
    private int modelId;
    private int price;
    private String currency;
    private boolean negotiable;
    private int year;
    private int weight;

    public Car(String imageUrl, String title, int modelId, int price, String currency, boolean negotiable, int year) {
        this.imageUrl = imageUrl;
        this.title = title;
        this.modelId = modelId;
        this.price = price;
        this.currency = currency;
        this.negotiable = negotiable;
        this.year = year;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getModelId() {
        return modelId;
    }

    public void setModelId(int modelId) {
        this.modelId = modelId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public boolean isNegotiable() {
        return negotiable;
    }


    public void setNegotiable(boolean negotiable) {
        this.negotiable = negotiable;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }


}




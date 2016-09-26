package com.oneafricamedia.classifieds.models;

/**
 * Created by brad on 2016/09/02.
 */
public class Car {
    private String imageUrl;
    private String title;
    private String make;
    private String model;
    private String condition;
    private int price;
    private String currency;
    private boolean negotiable;
    private int year;
    private int weight;

    public Car(String imageUrl, String title, String make, String model, String condition, int price, String currency, boolean negotiable, int year, int weight) {
        this.imageUrl = imageUrl;
        this.title = title;
        this.make = make;
        this.model = model;
        this.condition = condition;
        this.price = price;
        this.currency = currency;
        this.negotiable = negotiable;
        this.year = year;
        this.weight = weight;
    }

    public Car() {
        weight = 0;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
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


    @Override
    public String toString() {
        return "Car{" +
                "imageUrl='" + imageUrl + '\'' +
                ", title='" + title + '\'' +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", condition='" + condition + '\'' +
                ", price=" + price +
                ", currency='" + currency + '\'' +
                ", negotiable=" + negotiable +
                ", year=" + year +
                ", weight=" + weight +
                '}';
    }
}




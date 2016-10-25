package com.oneafricamedia.classifieds.models;

import com.oneafricamedia.classifieds.weightings.WeightingModel;

import java.util.HashMap;

/**
 * Created by brad on 2016/09/02.
 */
public class Car implements WeightingModel {
    private String imageUrl;
    private String title;
    private String make;
    private String model;
    private String condition;
    private int price;
    private String currency;
    private boolean negotiable;
    private int year;
    private String location;
    private String fuelType;
    private String bodyType;
    private String driverSetup;
    private String transmission;
    private int milage;
    private boolean isMoneyBack;
    private int weight;
    private boolean seen;


    public Car() {
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getBodyType() {
        return bodyType;
    }

    public void setBodyType(String bodyTye) {
        this.bodyType = bodyTye;
    }

    public String getDriverSetup() {
        return driverSetup;
    }

    public void setDriverSetup(String driverSetup) {
        this.driverSetup = driverSetup;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public int getMilage() {
        return milage;
    }

    public void setMilage(int milage) {
        this.milage = milage;
    }

    public boolean isMoneyBack() {
        return isMoneyBack;
    }

    public void setMoneyBack(boolean moneyBack) {
        isMoneyBack = moneyBack;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public boolean isSeen() {
        return seen;
    }

    public void setSeen(boolean seen) {
        this.seen = seen;
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
                ", location='" + location + '\'' +
                ", fuelType='" + fuelType + '\'' +
                ", bodyType='" + bodyType + '\'' +
                ", driverSetup='" + driverSetup + '\'' +
                ", transmission='" + transmission + '\'' +
                ", milage=" + milage +
                ", isMoneyBack=" + isMoneyBack +
                ", weight=" + weight +
                '}';
    }

    @Override
    public HashMap<String, String> getParams() {
        HashMap<String, String> params = new HashMap<>(20);

        params.put("make", make);
        params.put("model", model);
        params.put("condition", condition);
        params.put("price", price + "");
        params.put("currency", currency);
        params.put("negotiable", negotiable + "");
        params.put("year", year + "");
        params.put("location", location);
        params.put("fuelType", fuelType);
        params.put("bodyType", bodyType);
        params.put("driverSetup", driverSetup);
        params.put("transmission", transmission);
        params.put("milage", milage + "");
        params.put("isMoneyBack", isMoneyBack + "");
        return params;
    }

    /**
     * Returns a list of paramters that will be used in weightings
     * @return
     */

}




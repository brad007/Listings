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
    private String location;
    private String fuelType;
    private String bodyTye;
    private String driverSetup;
    private String transmission;
    private int milage;
    private boolean isMoneyBack;
    private int weight;

    public Car() {
    }

    public Car(String imageUrl, String title, String make, String model, String condition, int price, String currency, boolean negotiable, int year, String location, String fuelType, String bodyTye, String driverSetup, String transmission, int milage, boolean isMoneyBack, int weight) {
        this.imageUrl = imageUrl;
        this.title = title;
        this.make = make;
        this.model = model;
        this.condition = condition;
        this.price = price;
        this.currency = currency;
        this.negotiable = negotiable;
        this.year = year;
        this.location = location;
        this.fuelType = fuelType;
        this.bodyTye = bodyTye;
        this.driverSetup = driverSetup;
        this.transmission = transmission;
        this.milage = milage;
        this.isMoneyBack = isMoneyBack;
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
        return bodyTye;
    }

    public void setBodyType(String bodyTye) {
        this.bodyTye = bodyTye;
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
                ", bodyTye='" + bodyTye + '\'' +
                ", driverSetup='" + driverSetup + '\'' +
                ", transmission='" + transmission + '\'' +
                ", milage=" + milage +
                ", isMoneyBack=" + isMoneyBack +
                ", weight=" + weight +
                '}';
    }
}




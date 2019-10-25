package com.cars.front.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Car {

    private int carId;
    private String carMake;
    private String carModel;

    public Car() {
    }

    public Car(String carMake, String carModel) {
        this.carMake = carMake;
        this.carModel = carModel;
    }

    public Car(int id, String carMake, String carModel) {
        this.carId = id;
        this.carMake = carMake;
        this.carModel = carModel;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public String getCarMake() {
        return carMake;
    }

    public void setCarMake(String carMake) {
        this.carMake = carMake;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    @Override
    public String toString() {
        return "Car{" +
                "carId=" + carId +
                ", carMake='" + carMake + '\'' +
                ", carModel='" + carModel + '\'' +
                '}';
    }
}

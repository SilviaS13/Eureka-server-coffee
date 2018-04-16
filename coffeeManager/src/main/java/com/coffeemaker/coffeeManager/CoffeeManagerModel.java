package com.coffeemaker.coffeeManager;


import java.time.LocalTime;

public class CoffeeManagerModel {

    private LocalTime time;
    private boolean isOneTimeCoffee;
    private int idCoffee;
    private byte week;

    public CoffeeManagerModel(){};

    CoffeeManagerModel(String time, boolean isOneTimeCoffee, int idCoffee, byte week){
        this.time = LocalTime.parse(time);
        this.isOneTimeCoffee = isOneTimeCoffee;
        this.idCoffee = idCoffee;
        this.week = week;
    }

    //region Getters
    public LocalTime getTime() {
        return time;
    }

    public boolean getIsOneTimeCoffee() {
        return isOneTimeCoffee;
    }

    public int getIdCoffee() {
        return idCoffee;
    }

    public byte getWeek() {
        return week;
    }

    //endregion

    //region Setters

//    public void setTime(String time) {
//
//        this.time = LocalTime.parse(time);
//    }

//    public void setOneTimeCoffee(boolean oneTimeCoffee) {
//        isOneTimeCoffee = oneTimeCoffee;
//    }

    public void setIdCoffee(int idCoffee) {
        this.idCoffee = idCoffee;
    }

//    public void setWeek(byte week) {
//        this.week = week;
//    }

    //endregion
}

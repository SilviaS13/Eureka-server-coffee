package com.coffeemaker.coffeeIngredients;

public class CoffeeIngredientsModel {

    private static volatile CoffeeIngredientsModel instance;

    private int waterAvaliable;
    private int coffeeAvaliable;
    private int milkAvaliable;
    private int chocolateAvaliable;
    private int sugarAvaliable;
    private boolean cupIsOnMachine;

    private boolean cupReleased;


    private CoffeeIngredientsModel(){};

    public static CoffeeIngredientsModel getInstance(){

        CoffeeIngredientsModel localInstance = instance;

        if (localInstance == null){
            synchronized (CoffeeIngredientsModel.class){
                localInstance = instance;
                if (localInstance == null){
                    instance = localInstance = new CoffeeIngredientsModel();
                }
            }
        }

        return localInstance;
    }

    //region Getters

    public int getWaterAvaliable() {
        return waterAvaliable;
    }

    public int getCoffeeAvaliable() {
        return coffeeAvaliable;
    }

    public int getMilkAvaliable() {
        return milkAvaliable;
    }

    public int getChocolateAvaliable() {
        return chocolateAvaliable;
    }

    public int getSugarAvaliable() {
        return sugarAvaliable;
    }

    public boolean getCupIsOnMachine() {
        return cupIsOnMachine;
    }

    public boolean isCupReleased() {
        return cupReleased;
    }

    //endregion

    //region Setters

    public void setCoffeeAvaliable(int espressoAvaliable) {
        this.coffeeAvaliable = espressoAvaliable;
    }

    public static void setInstance(CoffeeIngredientsModel instance) {
        CoffeeIngredientsModel.instance = instance;
    }

    public void setWaterAvaliable(int waterAvaliable) {
        this.waterAvaliable = waterAvaliable;
    }

    public void setMilkAvaliable(int milkAvaliable) {
        this.milkAvaliable = milkAvaliable;
    }

    public void setChocolateAvaliable(int chocolateAvaliable) {
        this.chocolateAvaliable = chocolateAvaliable;
    }

    public void setSugarAvaliable(int sugarAvaliable) {
        this.sugarAvaliable = sugarAvaliable;
    }

    public void setCupIsІsOnMachine(boolean cupIsOnMachine) {
        this.cupIsOnMachine = cupIsOnMachine;
    }

    //endregion

}

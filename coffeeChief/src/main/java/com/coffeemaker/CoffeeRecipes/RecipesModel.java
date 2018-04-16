package com.coffeemaker.CoffeeRecipes;

public class RecipesModel {

    private int id;

    private String coffeeName;
    private int waterQuantity;
    private int espressoQuantity;
    private int milkQuantity;
    private int chocolateQuantity;
    private int sugarQuantity;
    public RecipesModel(){};

     public RecipesModel(int id, String coffeeName, int espressoQuantity, int waterQuantity, int milkQuantity, int chocolateQuantity, int sugarQuantity){
        this.id = id;
        this.coffeeName = coffeeName;
        this.espressoQuantity = espressoQuantity;
        this.waterQuantity = waterQuantity;
        this.milkQuantity = milkQuantity;
        this.sugarQuantity = sugarQuantity;
        this.chocolateQuantity = chocolateQuantity;
    }


    //region Getters

    public int getId() {
        return id;
    }
    public String getCoffeeName() {
        return coffeeName;
    }

    public int getWaterQuantity() {
        return waterQuantity;
    }

    public int getEspressoQuantity() {
        return espressoQuantity;
    }

    public int getMilkQuantity() {
        return milkQuantity;
    }

    public int getChocolateQuantity() {
        return chocolateQuantity;
    }

    public int getSugarQuantity() {
        return sugarQuantity;
    }


    //endregion

    public void setId(int id) {
        this.id = id;
    }
}

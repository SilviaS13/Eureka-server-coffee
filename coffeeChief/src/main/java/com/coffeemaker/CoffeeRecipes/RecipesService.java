package com.coffeemaker.CoffeeRecipes;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Iterator;

@Service
public class RecipesService {
    private static HashMap< Integer, RecipesModel> coffees = new HashMap<Integer, RecipesModel>();
    private static Integer id = 4;
    //private static List<IngredientModel> coffees = new ArrayList<IngredientModel>();

    static {
        //coffees.add(id, new IngredientModel("Cappucino", 30, 60, 30, 0, 10 ));
        coffees.put(0, new RecipesModel(0, "Cappucino", 30, 60, 30, 0, 10 ));
        coffees.put(1, new RecipesModel(1,"Americano", 30, 100, 0,0,  0 ));
        coffees.put(2, new RecipesModel(2,"Mocco",40, 30, 30,20,  0 ));
        coffees.put(3, new RecipesModel(3,"Espresso",40, 0, 0,0,  0 ));
    }
//
//    public static void addCoffee( String coffeeName, int espressoQuantity, int waterQuantity, int milkQuantity, int chocolateQuantity, int sugarQuantity){
//
//        coffees.put(id++, new IngredientModel( id-1, coffeeName, espressoQuantity, waterQuantity,  milkQuantity, chocolateQuantity, sugarQuantity));
//    }

    public static HashMap< Integer, RecipesModel> coffeelist(){
        return coffees;
    }

    public static void removeCoffee(Integer id){
        Iterator iter = coffees.keySet().iterator();
        while (iter.hasNext()){
            if(iter.next() == id)
                coffees.remove(id);
        }
    }

    public static RecipesModel getCoffee(Integer id){
        Iterator iter = coffees.keySet().iterator();
        while (iter.hasNext()){
            if(iter.next() == id)
                return coffees.get(id);
        }
       return null;
    }

    public static int createCoffee(RecipesModel coffee){
        coffee.setId(id);
        coffees.put(id++, coffee);
        return id - 1;
    }
}

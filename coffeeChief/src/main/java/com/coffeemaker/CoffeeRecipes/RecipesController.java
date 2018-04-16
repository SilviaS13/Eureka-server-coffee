package com.coffeemaker.CoffeeRecipes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;

@RestController
public class RecipesController {

    @Autowired
    private RecipesService service;


    @GetMapping("/")
    public String home(){
        return "Hello from Recipes service!";

    }

    @GetMapping("/api/recipes")
    public ArrayList<RecipesModel> getCoffees(){

        HashMap<Integer, RecipesModel>  coffeeList = RecipesService.coffeelist();
        return new ArrayList<>(coffeeList.values());

    }

    @GetMapping("/api/recipes/{id}")
    public String getCoffee(@PathVariable Integer id){
        RecipesModel coff = service.getCoffee(id);

        String mess = "Name = " + coff.getCoffeeName() + "; Espresso (g) = " + coff.getEspressoQuantity() +
                "; Water = " + coff.getWaterQuantity() + "; Milk = " + coff.getMilkQuantity() + "; Chocolate = "+
                coff.getChocolateQuantity() + "; Sugar = " + coff.getSugarQuantity();

        return mess;
    }

    @PostMapping(path = "/api/recipes/add")
    public int createCoffee(@RequestBody RecipesModel coffeeModel){
        return service.createCoffee(coffeeModel);
    }

    @PostMapping(path = "/api/recipes/rm")
    public String removeCoffee(@RequestBody Integer id){
        service.removeCoffee(id);
         return "Successful";
    }
}

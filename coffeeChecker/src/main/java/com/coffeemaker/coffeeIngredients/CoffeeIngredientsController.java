package com.coffeemaker.coffeeIngredients;

import org.springframework.web.bind.annotation.*;

@RestController
public class CoffeeIngredientsController {

    CoffeeIngredientsModel checker;

    @GetMapping("/")
    public String home(){
        return "Hello from Ingredient Checker service!";
    }

    @GetMapping("/api/ingredients")
    public String getComponents(){

        checker = CoffeeIngredientsModel.getInstance();

        return  "Products avaliable: " + "<br/>" +
                "The cup is on coffee machine: " + checker.getCupIsOnMachine() + "<br/>" +
                "Coffee = " + checker.getCoffeeAvaliable() +"<br/>" +
                "Water = " + checker.getWaterAvaliable() + "<br/>" +
                "Milk = " + checker.getMilkAvaliable() + "<br/>" +
                "Chocolate = "+ checker.getChocolateAvaliable() + "<br/>" +
                "Sugar = " + checker.getSugarAvaliable();

    }

    @GetMapping("/api/ingredients/{component}")
    public Integer getComponent(@PathVariable String component){

        checker = CoffeeIngredientsModel.getInstance();

        switch (component){
            case ("coffee"): return checker.getCoffeeAvaliable();
            case ("water"): return checker.getWaterAvaliable();
            case ("milk"): return checker.getMilkAvaliable();
            case ("sugar"): return checker.getSugarAvaliable();
            case ("chocolate"): return checker.getChocolateAvaliable();
            case ("cup"): return checker.getCupIsOnMachine() == true ? 1 : 0;
        }
        return 0;
    }

    @PostMapping(path = "/api/ingredients/update")
    public String createCoffee(@RequestBody int[] vals){
        checker = CoffeeIngredientsModel.getInstance();
        if (vals.length == 6) {
            checker.setCoffeeAvaliable(checker.getCoffeeAvaliable() + vals[0]);
            checker.setWaterAvaliable(checker.getWaterAvaliable() + vals[1]);
            checker.setMilkAvaliable(checker.getMilkAvaliable() + vals[2]);
            checker.setChocolateAvaliable(checker.getChocolateAvaliable() + vals[3]);
            checker.setSugarAvaliable(checker.getSugarAvaliable() + vals[4]);
            checker.setCupIsІsOnMachine(vals[5] == 1 ? true : false);
        }
        //checkerService.updateCheckerVals(arr);
        return "Successfuly";
    }

}

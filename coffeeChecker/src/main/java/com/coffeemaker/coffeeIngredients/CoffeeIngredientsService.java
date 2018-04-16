package com.coffeemaker.coffeeIngredients;

import org.springframework.stereotype.Service;

@Service
public class CoffeeIngredientsService {

    private static CoffeeIngredientsModel checker = CoffeeIngredientsModel.getInstance();

    static {
        checker.setChocolateAvaliable(200);
        checker.setCoffeeAvaliable(150);
        checker.setCupIsІsOnMachine(false);
        checker.setMilkAvaliable(300);
        checker.setSugarAvaliable(250);
        checker.setWaterAvaliable(500);
    }

//    public void updateCheckerVals(int[] vals){
//        checker = CoffeeCheckerModel.getInstance();
//        if (vals.length == 6) {
//            checker.setCoffeeAvaliable(checker.getCoffeeAvaliable() + vals[0]);
//            checker.setWaterAvaliable(checker.getWaterAvaliable() + vals[1]);
//            checker.setMilkAvaliable(checker.getMilkAvaliable() + vals[2]);
//            checker.setChocolateAvaliable(checker.getChocolateAvaliable() + vals[3]);
//            checker.setSugarAvaliable(checker.getSugarAvaliable() + vals[4]);
//            checker.setCupIsІsOnMachine(vals[5] == 1 ? true : false);
//        }
//    }
}

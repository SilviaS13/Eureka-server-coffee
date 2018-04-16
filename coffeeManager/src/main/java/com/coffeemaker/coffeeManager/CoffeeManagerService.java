package com.coffeemaker.coffeeManager;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Iterator;

@Service
public class CoffeeManagerService {

    private static String[] week = new String[]{"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};

    private static HashMap< Integer, CoffeeManagerModel> coffeeTime = new HashMap<Integer, CoffeeManagerModel>();
    private static Integer id = 0;

    static {
        coffeeTime.put(id++, new CoffeeManagerModel("10:30",true,id-1, (byte) 0));
        coffeeTime.put(id++, new CoffeeManagerModel("12:30",false,id-1, (byte) 49));
        coffeeTime.put(id++, new CoffeeManagerModel("18:00",true,id-1, (byte) 95));
        coffeeTime.put(id++, new CoffeeManagerModel("15:30",true,id-1, (byte) 8));
        coffeeTime.put(id++, new CoffeeManagerModel("20:00",true,id-1, (byte) 0));
    }



    public static void addCoffeeTime(String time, boolean isOneTimeCoffee, int idCoffee, byte days){

        coffeeTime.put(id++, new CoffeeManagerModel(time,isOneTimeCoffee,idCoffee, days));
    }

    public static HashMap< Integer, CoffeeManagerModel> coffeeTimeList(){
        return coffeeTime;
    }

    public static void removeCoffeeTime(Integer id){
        Iterator iter = coffeeTime.keySet().iterator();
        while (iter.hasNext()){
            if(iter.next() == id)
                coffeeTime.remove(id);
        }
    }

    public static CoffeeManagerModel getCoffeeTime(Integer id){
        Iterator iter = coffeeTime.keySet().iterator();
        while (iter.hasNext()){
            if(iter.next() == id)
                return coffeeTime.get(id);
        }
       return null;
    }

//    public static byte WeekStrToByte(String myweek){
//        byte byteWeek = 0;
//        for (int i = 0; i<week.length; i++){
//            if (myweek.contains(week[i]))
//                byteWeek |= 1<<i;
//        }
//        return byteWeek;
//    }

    public static String WeekByteToStr(byte myweek){
        String strWeek = " ";

        for (int i = 0; i<week.length; i++){
            if ((myweek & 1) == 1)
                strWeek += week[i] + " ";
            myweek >>=1;
        }
        if (strWeek == " ")
            strWeek = "Today";

        return strWeek;
    }

    public static int createCoffeeTime(CoffeeManagerModel coffee){
        coffee.setIdCoffee(id);
        coffeeTime.put(id++, coffee);

        return id - 1;
    }
}

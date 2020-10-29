package com.viyom.menu;

import com.viyom.menu.DisplayMenu;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MainMenuHandler {
    public static int getShoppingDate(){
        Scanner in = new Scanner(System.in);
        System.out.println("Enter date, +ve for future date,-ve for back date");
        int date = in.nextInt();
        return date;
    }

    public static Map<String, Integer> addProducts(){
        DisplayMenu displayMenu=new DisplayMenu();
        Map<String, Integer> itemQuantityMap= new HashMap<>();
        int moreItems=1;
        while(moreItems>0){
            displayMenu.displayMenu();
            Scanner in = new Scanner(System.in);
            displayMenu.promptForProductNo();
            String productName = in.nextLine();
            displayMenu.promptForQuantity();
            int quantity=in.nextInt();
            displayMenu.promptAddMoreProducts();
            moreItems=in.nextInt();
            if(itemQuantityMap.containsKey(productName)){
                Integer previousQuantity=itemQuantityMap.get(productName);
                itemQuantityMap.put(productName,previousQuantity+quantity);
            }
            itemQuantityMap.putIfAbsent(productName,quantity);
        }
        return itemQuantityMap;
    }
}

package com.example.garbagesortingapp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemsDB {

    private final HashMap<String, String> itemsDB= new HashMap<String, String>();
    private static ItemsDB sItemsDB;

    //constructor must be private in singleton
    private ItemsDB() {
        fillItemsDB();
    }

    /**
     * We initialize a ItemsDB if it hasen't ben initialized yet
     * We cerate a sItemDB to state that an ItemsDB hasn't been initialized yet.
     * If sItemsBB != null we won't initialize one.
     * In the If statement we assign the ItemsBD to be the arraylist of items
     */
    public static void initialize(){
        if(sItemsDB == null){
            sItemsDB = new ItemsDB();
        }
    }

    public static ItemsDB get(){
        if(sItemsDB==null){
            throw new IllegalStateException("ItemsDB must be initialized");

        }

        return sItemsDB;
    }


    public void addItem(String garbage, String where) {

        itemsDB.put(garbage, where);

    }

    public void fillItemsDB(Context context) {

        new InputStreamReader(context.getAssets().open(”garbage.txt”)));

    }

    public String checkLocation(String inputGarbage) {
        String returnString = " ";
        System.out.println(inputGarbage);

        for (Item item : ItemsDB) {


            if (item.getGarbage().equals(inputGarbage)) {

                return inputGarbage + " must be put to " + item.getWhere();
            }else{
                returnString = inputGarbage + " not found.";
            }
        }
        return returnString;
    }




}

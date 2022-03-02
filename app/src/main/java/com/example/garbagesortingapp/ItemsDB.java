package com.example.garbagesortingapp;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;


public class ItemsDB {

    private final HashMap<String, String> itemsDB= new HashMap<String, String>();
    private static ItemsDB sItemsDB;

    //constructor must be private in singleton
    private ItemsDB(Context context) { fillItemsDB(context,"garbage.txt"); }


    /**
     * We initialize a ItemsDB if it hasen't ben initialized yet
     * We cerate a sItemDB to state that an ItemsDB hasn't been initialized yet.
     * If sItemsBB != null we won't initialize one.
     * In the If statement we assign the ItemsBD to be the arraylist of items
     */
    public static void initialize(Context context) {
        if (sItemsDB == null) {
            sItemsDB = new ItemsDB(context);
        }
    }


    public static ItemsDB get(){
        if(sItemsDB==null){
            throw new IllegalStateException("ItemsDB must be initialized");

        }

        return sItemsDB;
    }


    public void addItem(String garbage, String where) {
        Item item = new Item(garbage, where);

        itemsDB.put(item.getGarbage(), item.getWhere());

    }

    public String listGarbages(){
        String line = " ";
        for (String garbage: itemsDB.keySet()) {
            line = line +  "\n Sort " + garbage + " to " + itemsDB.get(garbage);
        }
        return line;
    }

    public int size(){
        return itemsDB.size();
    }

    public String getWhere(String garbage){
        String where = itemsDB.get(garbage);
        return where;
    }

    public void remove(String garbage){
        if(itemsDB.get(garbage)!=null){
            itemsDB.remove(garbage);
        }
    }

    public void fillItemsDB(Context context, String filename) {

        try{
            BufferedReader reader= new BufferedReader(
                    new InputStreamReader(context.getAssets().open(filename)));

            String line = reader.readLine();
            while (line != null){
                String[] splitString = line.split(", ");
                String garbage = splitString[0];
                String where = splitString[1];

                itemsDB.put(garbage, where);

                line = reader.readLine();
            }

        }catch (IOException e){

        }
    }

    public String checkLocation(String inputGarbage) {
        String returnString = " ";
        System.out.println(inputGarbage);

        for (String key : itemsDB.keySet()) {


            if (key.equals(inputGarbage)) {

                return inputGarbage + " must be put to " + itemsDB.get(key);
            }else{
                returnString = inputGarbage + " not found.";
            }
        }
        return returnString;
    }




}
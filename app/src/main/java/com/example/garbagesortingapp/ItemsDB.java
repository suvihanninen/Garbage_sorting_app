package com.example.garbagesortingapp;

import android.content.Context;
import androidx.lifecycle.ViewModel;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

//database lives in this activity -> we add stuff
public class ItemsDB extends ViewModel{


    private final Map<String, String> itemsDB= new HashMap<String, String>();


    public ItemsDB(Context context) { fillItemsDB(context,"garbage.txt"); }


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
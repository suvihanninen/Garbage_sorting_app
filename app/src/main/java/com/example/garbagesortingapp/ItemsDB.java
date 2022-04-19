package com.example.garbagesortingapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.example.garbagesortingapp.database.ItemBaseHelper;
import com.example.garbagesortingapp.database.ItemCursorWrapper;
import com.example.garbagesortingapp.database.ItemsDbSchema;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//database lives in this activity -> we add stuff
public class ItemsDB extends ViewModel{
    private static SQLiteDatabase mDatabase;
    //private final ArrayList<Item> itemsDB= new ArrayList<>();

    public ItemsDB(Context context){
        if (mDatabase == null) {
            mDatabase= new ItemBaseHelper(context.getApplicationContext()).getWritableDatabase();
            if (getValues().size() == 0) fillItemsDB(context, "garbage.txt");
        }
    }


    static private ItemCursorWrapper queryItems(String whereClause, String[] whereArgs) {
        Cursor cursor= mDatabase.query(
                ItemsDbSchema.ItemTable.NAME,
                null, // Columns - null selects all columns
                whereClause, whereArgs,
                null, // groupBy
                null, // having
                null  // orderBy
        );
        return new ItemCursorWrapper(cursor);
    }

    public int size() {
        return getValues().size();
    }

    public void close() {   mDatabase.close();   }

    /*public ItemsDB(Context context) { fillItemsDB(context,"garbage.txt"); }*/


    public void addItem(String garbage, String where) {
        Item newItem= new Item(garbage, where);
        ContentValues values= getContentValues(newItem);
        mDatabase.insert(ItemsDbSchema.ItemTable.NAME, null, values);

    }


    public List<Item> getValues() {
        ArrayList<Item> items= new ArrayList<Item>();
        ItemCursorWrapper cursor= queryItems(null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            items.add(cursor.getItem());
            cursor.moveToNext();
        }
        cursor.close();
        return items;
    }

    /**public String listGarbages(){
        String line = " ";
        for (Item item: itemsDB) {
            line = line +  "\n Sort " + item.getGarbage() + " to " + item.getWhere();
        }
        return line;
    }**/

    /*public int size(){
        return itemsDB.size();
    }*/

    /**public String getWhere(String garbage){
        String returngarbage = " ";
        for (Item item: itemsDB) {
            if(item.getGarbage() == garbage){
                returngarbage = item.getWhere();
            }
        }
        return returngarbage;
    }**/

    public void remove(String garbage){
        Item newItem= new Item(garbage, "");
        String selection= ItemsDbSchema.ItemTable.Cols.WHAT + " LIKE ?";
        int changed= mDatabase.delete(ItemsDbSchema.ItemTable.NAME, selection, new String[]{newItem.getWhat()});
    }

    // Database helper methods to convert between Items and database rows
    private static ContentValues getContentValues(Item item) {
        ContentValues values=  new ContentValues();
        values.put(ItemsDbSchema.ItemTable.Cols.WHAT, item.getWhat());
        values.put(ItemsDbSchema.ItemTable.Cols.WHERE, item.getWhere());
        return values;
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
                addItem(garbage, where);
                line = reader.readLine();
            }

        }catch (IOException e){

        }
    }

    public String checkLocation(String inputGarbage) {

        Cursor c = mDatabase.rawQuery("SELECT what,whereC FROM Items ", null);
        String where = "";
        if (c.moveToFirst()){
            do {
                // Passing values
                Log.d("TAG", "checkLocation: " + c.getString(0));
                String what = c.getString(0);
                String whereC = c.getString(1);

                if (what.equals(inputGarbage))  {
                    where = whereC;
                }
                // Do something Here with values
            } while(c.moveToNext());
        }
        c.close();
        //mDatabase.close();
        return  where;
    }


}
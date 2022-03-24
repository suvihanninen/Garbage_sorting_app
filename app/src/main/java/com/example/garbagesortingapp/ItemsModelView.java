package com.example.garbagesortingapp;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import java.util.List;

public class ItemsModelView extends AndroidViewModel {
    private static MutableLiveData<ItemsDB> items;
    private ItemsDB itemsDB;

    public ItemsModelView(Application application) {
        super(application);
        items= new MutableLiveData<>();
        itemsDB = new ItemsDB(application);
        items.setValue(itemsDB);
    }

    public MutableLiveData<ItemsDB> getValue() { return items; }

    public List<Item> getList() { return items.getValue().getValues(); }

    public void addItem(String garbage, String where){
        ItemsDB temp= items.getValue();
        temp.addItem(garbage, where);
        items.setValue(temp);
    }

    public void removeItem(String garbage){
        ItemsDB temp= items.getValue();
        temp.remove(garbage);
        items.setValue(temp);
    }

    public String checkLocation(String inputGarbage) {
        ItemsDB temp= items.getValue();
        String result = temp.checkLocation(inputGarbage);
        items.setValue(temp);
        return result;
    }

}

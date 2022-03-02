package com.example.garbagesortingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private FragmentManager fm;
    Fragment WhereFragment, ListFragment;

    //Model: Database of items
    private static ItemsDB itemsDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        itemsDB.initialize(this); //returns my context
        itemsDB = ItemsDB.get();
        fm = getSupportFragmentManager();
        setUpFragments();
    }

    private void setUpFragments() {
        WhereFragment= fm.findFragmentById(R.id.container_where);
        ListFragment= fm.findFragmentById(R.id.container_list);
        if ((WhereFragment == null) && (ListFragment == null)) {
            WhereFragment= new WhereFragment();
            ListFragment= new ListFragment();
            fm.beginTransaction()
                    .add(R.id.container_where, WhereFragment)
                    .add(R.id.container_list, ListFragment)
                    .commit();
        }
    }
}




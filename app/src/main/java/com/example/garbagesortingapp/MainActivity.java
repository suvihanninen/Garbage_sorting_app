package com.example.garbagesortingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.content.res.Configuration;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fm = getSupportFragmentManager();
        setUpFragments();
    }


    private void setUpFragments() {
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            WhereFragment= fm.findFragmentById(R.id.container_where);
            ListFragment= fm.findFragmentById(R.id.container_list);
            if ((WhereFragment == null) && (ListFragment == null)) {
                Fragment fragmentUI= new WhereFragment();
                Fragment fragmentList= new ListFragment();
                fm.beginTransaction()
                        .add(R.id.container_where, fragmentUI)
                        .add(R.id.container_list, fragmentList)
                        .commit();
            }
        } else {
            //Orientation portrait
            if (WhereFragment== null) {
                WhereFragment = new WhereFragment();
                fm.beginTransaction()
                        .add(R.id.container_where, WhereFragment)
                        .commit();
            }
        }
    }
}




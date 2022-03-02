package com.example.garbagesortingapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class WhereFragment extends Fragment {

    private static ItemsDB itemsDB;
    private Button addItem, where, listItems;
    private TextView itemWhere;
    Intent intent= new Intent(WhereFragment.this, AddNewItemActivity.class);

    public WhereFragment() {
        // Required empty public constructor
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        itemsDB = ItemsDB.get();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_where_, container, false);

        //Text Fields
        where = v.findViewById(R.id.where_button);
        addItem = v.findViewById(R.id.add_new_item_inMainActivity_button);


        where.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String where= itemWhere.getText().toString().trim();
                String locationText = itemsDB.checkLocation(where);
                itemWhere.setText(locationText);
            }
        });

        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });


        return v;
    }
}
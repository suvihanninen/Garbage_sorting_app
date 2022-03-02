package com.example.garbagesortingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddNewItemActivity extends AppCompatActivity {
    private static ItemsDB itemsDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_item);

        EditText whatEditText = findViewById(R.id.what_edit_text);
        EditText whereEditText = findViewById(R.id.where_edit_text);
        Button addItemButton = findViewById(R.id.add_new_item_button);


        itemsDB= ItemsDB.get();

         addItemButton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 String whatString = whatEditText.getText().toString();
                 String whereString = whereEditText.getText().toString();
                 itemsDB.addItem(whatString, whereString);
                 finish();
             }
         });

    }
}
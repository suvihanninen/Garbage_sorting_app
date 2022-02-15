package com.example.garbagesortingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static ItemsDB itemsDB; //Why did we need to make this static
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent= new Intent(MainActivity.this, AddNewItemActivity.class);

        ItemsDB.initialize();
        itemsDB = ItemsDB.get();

        EditText findWhereEditText = findViewById(R.id.find_where_edit_text);
        Button whereButton = findViewById(R.id.where_button);
        Button addNewItemButton = findViewById(R.id.add_new_item_inMainActivity_button);


        whereButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputString = findWhereEditText.getText().toString();
                String locationText = itemsDB.checkLocation(inputString);
                findWhereEditText.setText(locationText);

            }
        });

        addNewItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });

    }
}


//Concurrency:
    /**
     * By concurrency there can be different statements running at the same time
     * On this code we can find inhereted concurreny on the "onClick" function. This is inhereted cocurrency
     * because the input is comeing from the UI. Here the MainActivity
     * is running by itself until the user clicks the onClick button which makes two streams run at the
     * same time.
     */

package com.example.garbagesortingapp;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.lifecycle.ViewModelProvider;

public class WhereFragment extends Fragment {

    private static ItemsModelView itemsDB;
    private Button addItem, where, listItems;
    private TextView itemWhere;


    public WhereFragment() {
        // Required empty public constructor
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_where_, container, false);

        //Text Fields
        itemWhere = v.findViewById(R.id.find_where_edit_text);
        //buttons
        where = v.findViewById(R.id.where_button);
        addItem = v.findViewById(R.id.add_new_item_inMainActivity_button);
        listItems = v.findViewById(R.id.list_button);

        itemsDB= new ViewModelProvider(requireActivity()).get(ItemsModelView.class);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            listItems.setOnClickListener(view ->
                    getActivity().
                            getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.container_where,
                                    new ListFragment()).commit());
        }

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
                Intent intent= new Intent(getActivity(), AddNewItemActivity.class);
                startActivity(intent);
            }
        });


        return v;
    }
}
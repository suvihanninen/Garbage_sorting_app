package com.example.garbagesortingapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.lifecycle.ViewModelProvider;



public class ListFragment extends Fragment {
    private static ItemsDB itemsDB;
    private TextView listThings;

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v= inflater.inflate(R.layout.fragment_list_, container, false);
        listThings= v.findViewById(R.id.listItems);
        listThings.setMovementMethod(new ScrollingMovementMethod());

        ItemsModelView itemsDB= new ViewModelProvider(requireActivity()).get(ItemsModelView.class);
        itemsDB.getValue().observe(getViewLifecycleOwner(), items -> listThings.setText("Garbages: " + items.listGarbages()));

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        ItemsModelView itemsDB = new ViewModelProvider(requireActivity()).get(ItemsModelView.class);
        itemsDB.getValue().observe(getViewLifecycleOwner(), items -> listThings.setText("Garbages: " + items.listGarbages()));
    }
}
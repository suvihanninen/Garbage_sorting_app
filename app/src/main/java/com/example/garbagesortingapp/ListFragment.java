package com.example.garbagesortingapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class ListFragment extends Fragment {
    private ItemsModelView itemsDB;
    private TextView listThings;
    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        final View v= inflater.inflate(R.layout.fragment_list_, container, false);
        recyclerView = v.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewAdapter= new RecyclerViewAdapter();
        recyclerView.setAdapter(recyclerViewAdapter);

        itemsDB= new ViewModelProvider(requireActivity()).get(ItemsModelView.class);


        itemsDB.getValue().observe(getActivity(), itemsDB -> recyclerViewAdapter.notifyDataSetChanged());



        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        ItemsModelView itemsDB = new ViewModelProvider(requireActivity()).get(ItemsModelView.class);
        itemsDB.getValue().observe(getActivity(), db -> recyclerViewAdapter.notifyDataSetChanged());    }

    public class RecyclerViewAdapter extends RecyclerView.Adapter<MyViewHolder>{

        //Here we need to somehow get our data from the items db i would assume and create a variable for that

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            final View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.one_row, parent, false);
            return new MyViewHolder(v);

        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            Log.d("SUVIS LOGS", String.valueOf(itemsDB.getList().size()));
            Item item= itemsDB.getList().get(position);
            holder.bind(item, position);
        }

        @Override
        public int getItemCount() {
            return itemsDB.getList().size();
        }


    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private final TextView number, dataWhat, dataWhere;
        public MyViewHolder(View itemView){
            super(itemView);
            number = itemView.findViewById(R.id.item_no);
            dataWhat = itemView.findViewById(R.id.item_what);
            dataWhere = itemView.findViewById(R.id.item_where);
            itemView.setOnClickListener(this);

        }

        public void bind(Item item, int position){


            number.setText(" "+position+" ");
            dataWhat.setText(item.getWhat());
            dataWhere.setText(item.getWhere());


        }

        public void onClick(View v) {
            // Trick from https://stackoverflow.com/questions/5754887/accessing-view-inside-the-linearlayout-with-code
            String what= (String)((TextView)v.findViewById(R.id.item_what)).getText();
            //once we have a value for what, we can delete the item
            itemsDB.removeItem(what);
        }
    }


}
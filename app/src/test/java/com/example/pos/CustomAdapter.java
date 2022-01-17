package com.example.pos;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.Holder> {
    List<String> itemList;


    public CustomAdapter(List<String> item){
        this.itemList = item;

    }


    @NonNull
    @Override
    public CustomAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_item, parent,false);
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.Holder holder, int position) {

        holder.texting.setText(itemList.get(position));

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView texting;
        public Holder(View view) {
            super(view);
            texting = view.findViewById(R.id.text_view);
        }
    }
}

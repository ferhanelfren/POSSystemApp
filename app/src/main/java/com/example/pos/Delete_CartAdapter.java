package com.example.pos;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Delete_CartAdapter extends RecyclerView.Adapter<Delete_CartAdapter.ViewHolder> {



    List<Delete_CartModel> list;

    public Delete_CartAdapter(List<Delete_CartModel> list) {
        this.list = list;
    }


    @NonNull
    @Override
    public Delete_CartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
        //return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.mycart_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Delete_CartAdapter.ViewHolder holder, int position) {

        //holder.imageView.setImageResource(list.get(position).getImage());
        holder.name.setText(list.get(position).getName());
        holder.rating.setText(list.get(position).getPrice());
        holder.price.setText(list.get(position).getRate());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView name, rating, price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            //imageView = itemView.findViewById(R.id.);
            //name = itemView.findViewById(R.id.);
            //rating = itemView.findViewById(R.id.);
            //price = itemView.findViewById(R.id.);


        }
    }
}

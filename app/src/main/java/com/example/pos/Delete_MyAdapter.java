package com.example.pos;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Random;

public class Delete_MyAdapter extends RecyclerView.Adapter<Delete_MyAdapter.ViewHolder>  {
   private ArrayList<Delete_Model> modelArrayList;
   private Context context;

   //constructor


    public Delete_MyAdapter(ArrayList<Delete_Model> modelArrayList, Context context) {
        this.modelArrayList = modelArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public Delete_MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.delete_singledata,parent, false );

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Delete_MyAdapter.ViewHolder holder, int position) {
        Delete_Model model = modelArrayList.get(position);
        holder.txtname.setText(model.getSname());
        holder.txtsubj.setText(model.getSsubject());

        //icon background random color
        Random random = new Random();
        int color = Color.argb(255, random.nextInt(255), random.nextInt(255), random.nextInt(256));
        holder.icon.setBackgroundColor(color);

    }

    @Override
    public int getItemCount() {
        return modelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView txtname, txtsubj;
        private ImageView icon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtname = (TextView) itemView.findViewById(R.id.textviewname);
            txtsubj = (TextView) itemView.findViewById(R.id.textviewsubj);
            icon = (ImageView) itemView.findViewById(R.id.icon);
        }
    }
}


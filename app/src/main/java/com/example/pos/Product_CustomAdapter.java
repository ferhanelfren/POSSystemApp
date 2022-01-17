package com.example.pos;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Product_CustomAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<Product_Item> product_items;

    public Product_CustomAdapter(Context context, int layout, ArrayList<Product_Item> product_items) {
        this.context = context;
        this.layout = layout;
        this.product_items = product_items;
    }

    @Override
    public int getCount() {
        return product_items.size();
    }

    @Override
    public Object getItem(int position) {
        return product_items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder{
        ImageView imageView;
        TextView t1,t2,t3,t4;
    }


    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View row = view;
        ViewHolder holder = new ViewHolder();

                if(row == null){
                    LayoutInflater inflater = (LayoutInflater) context.getSystemService(
                            Context.LAYOUT_INFLATER_SERVICE);

                    row = inflater.inflate(layout, null);

                    holder.t1 = (TextView) row.findViewById(R.id.Productnames);
                    holder.t2 = (TextView) row.findViewById(R.id.ProductQTY);
                    holder.t3 = (TextView) row.findViewById(R.id.ProductPRICE);
                    holder.t4 = (TextView) row.findViewById(R.id.ProductID);
                    holder.imageView = (ImageView)row.findViewById(R.id.imageView4);
                    row.setTag(holder);

                } else{
                    holder = (ViewHolder) row.getTag();

                }

                    Product_Item productitems = product_items.get(position);
                     holder.t1.setText(productitems.getName());
                     holder.t2.setText(productitems.getQty()+" available");
                     holder.t3.setText("Php "+productitems.getPrice());
                     holder.t4.setText(Integer.toString(productitems.getId()));

                     byte[] prodImage = productitems.getImage();
                     Bitmap bitmap = BitmapFactory.decodeByteArray(prodImage, 0 ,prodImage.length);
                     holder.imageView.setImageBitmap(bitmap);

        return row;
    }




}

package com.example.pos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;

public class ProductCartList extends AppCompatActivity {
    ImageView img;
    TextView t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_cart_list);
        t = findViewById(R.id.t1);

     //   img = findViewById(R.id.imageView5);

      //  Bundle extras = getIntent().getExtras();
       // byte[] byteArray = extras.getByteArray("images");

       // Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
       // img.setImageBitmap(bmp);

       // Bundle extras = getIntent().getExtras();
       // int imageRes = extras.getInt("images");
        //img.setImageResource(imageRes);

        Intent intent = getIntent();
        String id = intent.getStringExtra("idhan");

        t.setText(id);


    }
}
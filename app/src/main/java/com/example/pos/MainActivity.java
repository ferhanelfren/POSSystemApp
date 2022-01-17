package com.example.pos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    TextInputEditText mail, password;
    Button sign;
    TextView reghere;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        getSupportActionBar().setTitle("   Welcome to Point of Sale System");


        sign = findViewById(R.id.signin);
        mail = findViewById(R.id.email);
        password = findViewById(R.id.pass);
        reghere = findViewById(R.id.register_here);




        //sign in
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LogIn();






            }
        });





        reghere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                try{

                    registering();

                }catch(Exception e){

                    Toast.makeText(getApplicationContext(),e.getMessage(), Toast.LENGTH_SHORT).show();

                }



            }
        });


    }


    public void LogIn(){

        try {

            String username = mail.getText().toString();
            String pass = password.getText().toString();

            SQLiteDatabase db = openOrCreateDatabase("pointonsale", Context.MODE_PRIVATE, null);
            Cursor cursor = db.rawQuery("SELECT * FROM usertable WHERE email = '" + username + "' AND password = '" + pass +"'", null);

                if(cursor.moveToFirst() == true){

                    Intent intent = new Intent (MainActivity.this, HomeActivity.class);
                    intent.putExtra("names", mail.getText().toString());
                    startActivity(intent);
                    finish();

                }else if(username.equals("") || pass.equals("")){
                    password.setError("Fill this blank");
                    mail.setError("Fill this blank");

                    Toast.makeText(getApplicationContext(), "Please fill the blank above before you proceed", Toast.LENGTH_SHORT).show();

                }
                else{
                    Toast.makeText(getApplicationContext(), "Invalid username or password, please try again", Toast.LENGTH_SHORT).show();
                }



        }catch (Exception ex){

            Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_SHORT).show();

        }
    }






    public void registering(){

        Intent intent = new Intent (MainActivity.this, register.class);
        startActivity(intent);

    }







}
package com.example.pos;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class register extends AppCompatActivity {

    EditText email, fulname, pass, cpass;
    Button save;
    TextView login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        email = findViewById(R.id.emailone);
        fulname = findViewById(R.id.fullname);
        pass = findViewById(R.id.passwords);
        cpass = findViewById(R.id.confrimpassword);
        save = findViewById(R.id.signin);
        login = findViewById(R.id.log_in);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inserts();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    public void inserts(){

        String pass1 = pass.getText().toString();
        String pass2 = cpass.getText().toString();



                if(TextUtils.equals(pass1, pass2)){

                    try {

                        String ema = email.getText().toString();
                        String fname = fulname.getText().toString();
                        String passwordni = pass.getText().toString();
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        String currentTimeStamp = dateFormat.format(new Date()); // Find todays date

                        SQLiteDatabase db = openOrCreateDatabase("pointonsale", Context.MODE_PRIVATE, null);
                        db.execSQL("CREATE TABLE IF NOT EXISTS  usertable(id INTEGER PRIMARY KEY AUTOINCREMENT, email VARCHAR, fullname VARCHAR, password VARCHAR, timestamp TEXT)");
                        String SQL = "insert into usertable(email,fullname,password,timestamp)values(?,?,?,?)";
                        SQLiteStatement statement = db.compileStatement(SQL);
                        statement.bindString(1, ema);
                        statement.bindString(2, fname);
                        statement.bindString(3, passwordni);
                        statement.bindString(4, currentTimeStamp);
                        statement.execute();
                        Toast.makeText(this, "Users Successfully Added", Toast.LENGTH_SHORT).show();
                        email.setText("");
                        fulname.setText("");
                        pass.setText("");

                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        finish();



                    }catch (Exception ex){

                        Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();

                    }



                }else{
                    pass.setError("Your password didn't match");
                    cpass.setError("Your password didn't match");
                    return;
                }




        }

        }





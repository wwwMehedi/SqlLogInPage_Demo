package com.example.sajib.sqlloginpage_demo;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText username, password;
    private Button signibutton, signupbuuton;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = findViewById(R.id.textviewnameid);
        password = findViewById(R.id.textviewpasswordid);
        signibutton = findViewById(R.id.signin);
        signupbuuton = findViewById(R.id.signup);

        databaseHelper = new DatabaseHelper(this);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        signibutton.setOnClickListener(this);
        signupbuuton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        String uname = username.getText().toString();
        String pass = password.getText().toString();
        if (v.getId() == R.id.signin) {
            boolean result=databaseHelper.finpassword(uname,pass);
            if(result==true){
                Intent intent=new Intent(MainActivity.this,Main2Activity.class);
                startActivity(intent);
            }
           else {
                Toast.makeText(this, "Error found", Toast.LENGTH_SHORT).show();
            }
        }
        if (v.getId() == R.id.signup) {
            Intent intent = new Intent(MainActivity.this, SignUp.class);
            startActivity(intent);
        }
    }
}

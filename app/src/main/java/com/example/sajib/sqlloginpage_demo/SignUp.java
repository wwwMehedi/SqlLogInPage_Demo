package com.example.sajib.sqlloginpage_demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUp extends AppCompatActivity implements View.OnClickListener {
    private EditText nameedittext, emailedittext, useredittext, passwordedittext;
    private Button submit;
    UserDetails userDetails;
    DatabaseHelper dt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        nameedittext = findViewById(R.id.nameid);
        emailedittext = findViewById(R.id.emailid);
        useredittext = findViewById(R.id.usernameid);
        passwordedittext = findViewById(R.id.passwordid);
        submit = findViewById(R.id.signupbuttonid);
        userDetails = new UserDetails();
        dt = new DatabaseHelper(this);
        submit.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        String name = nameedittext.getText().toString();
        String email = emailedittext.getText().toString();
        String username = useredittext.getText().toString();
        String password = passwordedittext.getText().toString();

        userDetails.setName(name);
        userDetails.setEmail(email);
        userDetails.setUsername(username);
        userDetails.setPassword(password);
        if (v.getId() == R.id.signupbuttonid) {
            long rowId = dt.insertData(userDetails);

            if (rowId>0) {
                Toast.makeText(this, "saveData" + "rowid is"+rowId, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "falled", Toast.LENGTH_SHORT).show();
            }

        }
    }
}

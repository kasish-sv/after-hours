package com.example.after_hours;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class Donor_Login extends AppCompatActivity {

    EditText mEmail,mPassword;
    Button mLoginBtn;
    TextView mRegisterBtn;
    private DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logup);
        dbHelper = new DBHelper(Donor_Login.this);
        mEmail = findViewById(R.id.email);
        mPassword = findViewById(R.id.password);
        mRegisterBtn = findViewById(R.id.register);
        mLoginBtn = findViewById(R.id.login);

        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    mEmail.setError("Email is Required.");
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    mPassword.setError("Password is Required.");
                    return;
                }

                if (password.length() < 6) {
                    mPassword.setError("Password Must be >=6 Characters");
                    return;
                }
                int res=dbHelper.check_donor_login(email,password);
                if(res==1) {
                    Toast.makeText(Donor_Login.this, "Login Successful.", Toast.LENGTH_SHORT) .show();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.putExtra("username", email);
                    startActivity(intent);
                }
                else if(res==0){
                    Toast.makeText(Donor_Login.this, "Error!!!", Toast.LENGTH_SHORT) .show();
                }
                else {
                    Toast.makeText(Donor_Login.this, "Exception!!!", Toast.LENGTH_SHORT).show();
                }
            }
        });


        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // redirect to RegisterActivity
                Intent intent = new Intent(getApplicationContext(), Donor_Signup.class);
                startActivity(intent);
            }
        });
    }
}
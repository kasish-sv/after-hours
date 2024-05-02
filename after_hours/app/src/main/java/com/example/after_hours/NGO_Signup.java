package com.example.after_hours;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class NGO_Signup extends AppCompatActivity { ;
    EditText mFullName,mEmail,mPassword,mPhone;
    Button mRegisterBtn;
    TextView mLoginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        DBHelper dbHelper = new DBHelper(NGO_Signup.this);
        mFullName = findViewById(R.id.name);
        mEmail = findViewById(R.id.email);
        mPassword = findViewById(R.id.password);
        mPhone = findViewById(R.id.phone);
        mRegisterBtn=findViewById(R.id.register);
        mLoginBtn = findViewById(R.id.login);

        mRegisterBtn.setOnClickListener(new View.OnClickListener ()
        {
            @Override
            public void onClick(View v)
            {
                String email = mEmail.getText().toString().trim();
                String password= mPassword.getText().toString().trim();
                String name= mFullName.getText().toString().trim();
                String phone= mPhone.getText().toString().trim();

                if(TextUtils.isEmpty(email))
                {
                    mEmail.setError("Email is Required.");
                    return;
                }

                if(TextUtils.isEmpty(password))
                {
                    mPassword.setError("Password is Required.");
                    return;
                }

                if(password.length() < 6)
                {
                    mPassword.setError("Password Must be >=6 Characters");
                    return;
                }
                if(dbHelper.addNewNGO(email,phone,name,password)==0) {
                    Toast.makeText(NGO_Signup.this, "Registered Successfully.", Toast.LENGTH_SHORT) .show();
                    startActivity(new Intent(getApplicationContext(), NGO_Login.class));
                }
                else{
                    Toast.makeText(NGO_Signup.this, "Error in Credentials.", Toast.LENGTH_SHORT) .show();
                }

            }
        });

        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), NGO_Login.class));
            }
        });
    }
}
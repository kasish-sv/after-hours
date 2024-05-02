package com.example.after_hours;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;



public class NGO_dashboard extends AppCompatActivity {

    CardView receive, logout, about, contact, history;

    String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ngo_dashboard);

        receive = findViewById(R.id.cardReceive);
        logout = findViewById(R.id.cardLogout);
        history = findViewById(R.id.cardHistory);
        about = findViewById(R.id.cardAboutus);
        contact = findViewById(R.id.cardContact);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                email= null;
            } else {
                email= extras.getString("username");
            }
        } else {
            email= (String) savedInstanceState.getSerializable("username");
        }

        receive.setOnClickListener(new View.OnClickListener ()
        {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Receive.class);
                intent.putExtra("username", email);
                startActivity(intent);
            }
        });

        about.setOnClickListener(new View.OnClickListener ()
        {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), About.class);
                intent.putExtra("username", email);
                startActivity(intent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener ()
        {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NGO_dashboard.this, landingpage.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        contact.setOnClickListener(new View.OnClickListener ()
        {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), NGO_Contact.class);
                intent.putExtra("username", email);
                startActivity(intent);
            }
        });
        history.setOnClickListener(new View.OnClickListener ()
        {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), NGOactivity.class);
                intent.putExtra("username", email);
                startActivity(intent);
            }
        });
    }
}
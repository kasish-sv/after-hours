package com.example.after_hours;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;


public class landingpage extends AppCompatActivity {

    CardView donor, ngo,about;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landingpage);

        donor = findViewById(R.id.cardDonor);
        ngo = findViewById(R.id.cardNGO);
        about = findViewById(R.id.cardAboutus);


        donor.setOnClickListener(new View.OnClickListener ()
        {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Donor_Login.class));
            }
        });
        ngo.setOnClickListener(new View.OnClickListener ()
        {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), NGO_Login.class));
            }
        });
        about.setOnClickListener(new View.OnClickListener ()
        {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), About.class));
            }
        });
    }

}
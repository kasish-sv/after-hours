package com.example.after_hours;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


public class NGO_feedback extends AppCompatActivity {
    DBHelper dbHelper;
    String email;
    ArrayAdapter<String> adapter;
    ArrayList<String> d = new ArrayList<>();
    ListView donations_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        dbHelper = new DBHelper(NGO_feedback.this);
        donations_list = findViewById(R.id.donations_listview);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                email = null;
            } else {
                email = extras.getString("username");
            }
        } else {
            email = (String) savedInstanceState.getSerializable("username");
        }
        ArrayList<donations> res = dbHelper.get_feedback_donation(email);
        if (res.isEmpty()) {
            Toast.makeText(NGO_feedback.this, "No available donations!!", Toast.LENGTH_SHORT).show();
        } else {
            for (donations i : res) {
                d.add(i.tofeedbackString());
            }
            adapter = new ArrayAdapter<>(this, R.layout.list_white_text, d);
            donations_list.setAdapter(adapter);
            donations_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                    Object o = donations_list.getItemAtPosition(position);
                    String str = (String) o;
                    String[] result = str.split("\n");
                    String[] d_id = result[0].split(":");
                    Intent intent = new Intent(getApplicationContext(), get_feedback.class);
                    intent.putExtra("id", d_id[1]);
                    startActivity(intent);
                }
            });
        }
    }
}
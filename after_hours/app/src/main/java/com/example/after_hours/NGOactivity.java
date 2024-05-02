package com.example.after_hours;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


public class NGOactivity extends AppCompatActivity {
    DBHelper dbHelper;
    String email;
    ArrayAdapter<String> adapter;
    ArrayList<String> d = new ArrayList<>();
    ListView donations_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        dbHelper = new DBHelper(NGOactivity.this);
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
        ArrayList<donations> res = dbHelper.get_ngo_history(email);
        if (res.isEmpty()) {
            Toast.makeText(NGOactivity.this, "No available donations!!", Toast.LENGTH_SHORT).show();
        } else {
            for (donations i : res) {
                d.add(i.toString());
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
                    String res = dbHelper.take_a_donation(email, d_id[1]);
                }
            });
        }
    }
}
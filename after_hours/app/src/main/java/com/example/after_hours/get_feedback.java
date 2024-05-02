package com.example.after_hours;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class get_feedback extends AppCompatActivity{

    EditText mStatus, mRecipient;
    Button mSubmitBtn;
    DBHelper dbHelper;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_feedback);
        dbHelper = new DBHelper(get_feedback.this);
        mStatus = findViewById(R.id.status);
        mRecipient = findViewById(R.id.receipient);
        mSubmitBtn=findViewById(R.id.submit);


        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                id= null;
            } else {
                id= extras.getString("id");
            }
        } else {
            id= (String) savedInstanceState.getSerializable("id");
        }

        mSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String status = mStatus.getText().toString().trim();
                String recipient= mRecipient.getText().toString().trim();
                if(TextUtils.isEmpty(status))
                {
                    mStatus.setError("Status is Required.");
                    return;
                }

                if(TextUtils.isEmpty(recipient))
                {
                    mRecipient.setError("Required.");
                    return;
                }
                String res=dbHelper.update_feedback(id,status,recipient);
                if(res=="1"){
                    Toast.makeText(get_feedback.this, "Updated Successfully. Please login again", Toast.LENGTH_SHORT) .show();
                    Intent intent = new Intent(getApplicationContext(), NGO_Login.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(get_feedback.this, "Error!!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

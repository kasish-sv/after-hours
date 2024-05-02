package com.example.after_hours;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class Donate extends AppCompatActivity {

    EditText mType, mQuantity,mDescription, mdeadline;
    Button mSubmitBtn;
    DBHelper dbHelper;
    String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate);
        dbHelper = new DBHelper(Donate.this);
        mType = findViewById(R.id.d_type);
        mQuantity = findViewById(R.id.quantity);
        mdeadline = findViewById(R.id.d_deadline);
        mDescription = findViewById(R.id.description);
        mSubmitBtn=findViewById(R.id.submit);


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

        mSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String type = mType.getText().toString().trim();
                String quantity= mQuantity.getText().toString().trim();
                String description= mDescription.getText().toString().trim();
                String deadline= mdeadline.getText().toString().trim();

                if(TextUtils.isEmpty(type))
                {
                    mType.setError("Type is Required.");
                    return;
                }

                if(TextUtils.isEmpty(quantity))
                {
                    mQuantity.setError("Required.");
                    return;
                }

                if(TextUtils.isEmpty(deadline))
                {
                    mdeadline.setError("Required.");
                    return;
                }
                String res=dbHelper.make_a_donation(email,type,quantity,deadline,description);
                if(res=="0"){
                    Toast.makeText(Donate.this, "Donated Successfully", Toast.LENGTH_SHORT) .show();
                }
                else {
                    Toast.makeText(Donate.this, "Error!!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

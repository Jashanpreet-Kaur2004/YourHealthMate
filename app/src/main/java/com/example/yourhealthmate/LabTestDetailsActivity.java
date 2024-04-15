package com.example.yourhealthmate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LabTestDetailsActivity extends AppCompatActivity {

    TextView tvPackagename, tvtotalcost;
    EditText edDetails;
    Button btnAddtocart, btnback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test_details);

        tvPackagename = findViewById(R.id.textviewLDpackagename);
        tvtotalcost =findViewById(R.id.textviewldtotalcost);
        edDetails = findViewById(R.id.edittextldtextMultiline);
        btnAddtocart = findViewById(R.id.buttonldAddtocart);
        btnback = findViewById(R.id.buttonLTBack);


        edDetails.setKeyListener(null);

        Intent intent = getIntent();
        tvPackagename.setText(intent.getStringExtra("text1"));
        edDetails.setText(intent.getStringExtra("text2"));
        tvtotalcost.setText("Total Cost : "+intent.getStringExtra("text3")+"$");

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LabTestDetailsActivity.this,LabTestActivity.class));

            }
        });

        btnAddtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username","").toString();
                String product = tvPackagename.getText().toString();
                float price = Float.parseFloat(intent.getStringExtra("text3").toString());

                Database db = new Database(getApplicationContext(),"healthcare",null,1);
                if(db.checkcart(username,product)==1){
                    Toast.makeText(getApplicationContext(),"Product Already Added", Toast.LENGTH_SHORT).show();
                }else {
                    db.addcart(username,product,price,"lab");
                    Toast.makeText(getApplicationContext(),"Record Inserted To Cart", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LabTestDetailsActivity.this,LabTestActivity.class));

                }

            }
        });

    }
}
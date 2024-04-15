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

public class BuyMedicineDetailActivity extends AppCompatActivity {

    TextView tvPAckagename, tvTotalcost;
    EditText edDetails;
    Button btnBack, btngotocart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medicine_detail);

        tvPAckagename = findViewById(R.id.textviewbmdetailpackagename);
        edDetails = findViewById(R.id.edittextbmtextMultiline);
        edDetails.setKeyListener(null);
        tvTotalcost = findViewById(R.id.textviewbmtotalcost);
        btnBack = findViewById(R.id.buttonbmBack);
        btngotocart = findViewById(R.id.buttonbmAddtocart);


        Intent intent=getIntent();
        tvPAckagename.setText(intent.getStringExtra("text1"));
        edDetails.setText(intent.getStringExtra("text2"));
        tvTotalcost.setText("Total codet : $" +intent.getStringExtra("text3"));


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BuyMedicineDetailActivity.this,BuyMedicineActivity.class));

            }
        });

        btngotocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username","").toString();
                String product = tvPAckagename.getText().toString();
                float price = Float.parseFloat(intent.getStringExtra("text3").toString());

                Database db = new Database(getApplicationContext(),"healthcare",null,1);
                if(db.checkcart(username,product)==1){
                    Toast.makeText(getApplicationContext(),"Product Already Added", Toast.LENGTH_SHORT).show();
                }else {
                    db.addcart(username,product,price,"lab");
                    Toast.makeText(getApplicationContext(),"Record Inserted To Cart", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(BuyMedicineDetailActivity.this,BuyMedicineActivity.class));

                }
            }
        });
    }
}
package com.example.yourhealthmate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class FindDoctor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_doctor);

        CardView exit = findViewById(R.id.cardFDBack);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FindDoctor.this, HomeActivity.class));

            }
        });


        CardView familyphysician = findViewById(R.id.cardFDFamilyPhysician);
        familyphysician.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent it = new Intent(FindDoctor.this,DoctorDetailsActivity.class);
                it.putExtra("title", "Family Physicians");
                startActivity(it);
            }
        });

        CardView deitician = findViewById(R.id.cardFDDietician);
        deitician.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent it = new Intent(FindDoctor.this,DoctorDetailsActivity.class);
                it.putExtra("title", "Dieticians");
                startActivity(it);
            }
        });

        CardView dentist = findViewById(R.id.cardFDDentist);
        dentist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent it = new Intent(FindDoctor.this,DoctorDetailsActivity.class);
                it.putExtra("title", "Dentists");
                startActivity(it);
            }
        });

        CardView surgeon = findViewById(R.id.cardFDSurgeon );
        surgeon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent it = new Intent(FindDoctor.this,DoctorDetailsActivity.class);
                it.putExtra("title", "Surgeons");
                startActivity(it);
            }
        });
        CardView cardiologist = findViewById(R.id.cardFDCardiologist);
        cardiologist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent it = new Intent(FindDoctor.this,DoctorDetailsActivity.class);
                it.putExtra("title", "Cardiologists ");
                startActivity(it);
            }
        });
    }

}

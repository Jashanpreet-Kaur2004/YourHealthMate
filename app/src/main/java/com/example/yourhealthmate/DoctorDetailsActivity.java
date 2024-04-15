package com.example.yourhealthmate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class DoctorDetailsActivity extends AppCompatActivity {

    private String[][] doctor_details1 =
            {
                    {"Doctor Name : Nilesh Borate", "Hospital Address : Toronto", "Exp : 5yrs", "Mobile No: +1888776655", "1000"},
                    {"Doctor Name : Sophie Pawar", "Hospital Address : Etobekoe", "Exp: 15yrs", "Mobile No: +1777888999", "1200"},
                    {"Doctor Name : Samanatha Lele", "Hospital Address: Pine Beach", "Exp : 8yrs", "Mobile No:+1898989898", "430"},
                    {"Doctor Name: Damon Wilson", "Hospital Address : Oakville", "Exp : 6yrs", "Mobile No:+1898000000", "500"},
                    {"Doctor Name : Arshi Panda", "Hospital Address : Brossard", "Exp : 7yrs", "Mobile No:+1798989898", "1500"}
            };

    private String[][] doctor_details2 =
            {
                    {"Doctor Name : Benjamin ", "Hospital Address : Vancouver", "Exp : 9yrs", "Mobile No:+1898989898", "1600"},
                    {"Doctor Name : Lily Pawar", "Hospital Address : Halifax", "Exp: 14yrs", "Mobile No: +1898989898", "1900"},
                    {"Doctor Name : Mia Hall", "Hospital Address: Surrey", "Exp : 5yrs", "Mobile No:+1898989898", "1300"},
                    {"Doctor Name: Ava Max", "Hospital Address : Yukon", "Exp : 10yrs", "Mobile No:+1898000000", "1500"},
                    {"Doctor Name : Ashley Corrny", "Hospital Address : Montreal", "Exp : 17yrs", "Mobile No:+1798989898", "1800"}
            };

    private String[][] doctor_details3 =
            {
                    {"Doctor Name : Karan Sehnmi", "Hospital Address : Ottawa", "Exp : 5yrs", "Mobile No:+1898989898", "1600"},
                    {"Doctor Name : Shubh Sidhu", "Hospital Address : Toronto", "Exp: 15yrs", "Mobile No: +1898989898", "1900"},
                    {"Doctor Name : Aiden Nelson", "Hospital Address: Scarbrough", "Exp : 8yrs", "Mobile No:+1898989898", "1300"},
                    {"Doctor Name: Aeron Rachel", "Hospital Address : Hampstead", "Exp : 6yrs", "Mobile No:+1898000000", "1500"},
                    {"Doctor Name : Martha Jenny", "Hospital Address : Coteau Du Luc", "Exp : 7yrs", "Mobile No:+1798989898", "1800"}
            };

    private String[][] doctor_details4 =
            {
                    {"Doctor Name : Mary Brown", "Hospital Address : Longueil", "Exp : 5yrs", "Mobile No:+1598989898", "1600"},
                    {"Doctor Name : Logan Perez", "Hospital Address : Laval", "Exp: 15yrs", "Mobile No: +1898989898", "1900"},
                    {"Doctor Name : Zoe Lele", "Hospital Address: Sanburg", "Exp : 8yrs", "Mobile No:+1898989898", "1300"},
                    {"Doctor Name: Hoai Thu", "Hospital Address : Montreal", "Exp : 6yrs", "Mobile No:+1898000000", "1500"},
                    {"Doctor Name : Harper Scott", "Hospital Address : Katrai", "Exp : 7yrs", "Mobile No:+1798989898", "1800"}
            };

    private String[][] doctor_details5 =
            {
                    {"Doctor Name : Noah King", "Hospital Address : Missisuaga", "Exp : 5yrs", "Mobile No:+1898989898", "1600"},
                    {"Doctor Name : Madison Wright", "Hospital Address : Laval", "Exp: 15yrs", "Mobile No: +1898989898", "1900"},
                    {"Doctor Name : Riley Adams", "Hospital Address: Kingston", "Exp : 8yrs", "Mobile No:+1898989898", "1300"},
                    {"Doctor Name: Ethan Martinaz", "Hospital Address : Chinchwad", "Exp : 6yrs", "Mobile No:+1898000000", "1500"},
                    {"Doctor Name : Olivia  Nguyen", "Hospital Address : Kitchener", "Exp : 7yrs", "Mobile No:+1798989898", "1800"}
            };

    TextView tv;
    Button btn;
    String[][] doctor_details = {};
    HashMap<String,String> item;
    ArrayList list;
    SimpleAdapter sa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);

        tv = findViewById(R.id.textviewDDtitle);
        btn = findViewById(R.id.buttonDDBack);

        Intent it = getIntent();
        String title = it.getStringExtra("title");
        tv.setText(title);

        if (title.compareTo("Family Physicians")==0)
            doctor_details = doctor_details1;
        else
            if (title.compareTo("Dieticians")==0)
            doctor_details = doctor_details2;
        else
            if (title.compareTo("Dentists")==0)
            doctor_details = doctor_details3;
        else
            if (title.compareTo("Surgeons")==0)
            doctor_details = doctor_details4;
        else
                doctor_details = doctor_details5;

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DoctorDetailsActivity.this, FindDoctor.class));

            }
        });

        list = new ArrayList();
        for(int i=0;i<doctor_details.length;i++){
            item = new HashMap<String,String>();
            item.put("line1", doctor_details[i][0]);
            item.put("line2", doctor_details[i][1]);
            item.put("line3", doctor_details[i][2]);
            item.put("line4", doctor_details[i][3]);
            item.put("line5", "Cons Fees: "+doctor_details[i][4]+"$");
            list.add(item);
        }
        sa= new SimpleAdapter(this,list,
                R.layout.multi_lines,
                new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e}
        );

        ListView lst = findViewById(R.id.listViewDD);
        lst.setAdapter(sa);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it = new Intent(DoctorDetailsActivity.this,BookAppointmentActivity.class);
                it.putExtra("text1", title);
                it.putExtra("text2", doctor_details[i][0]);
                it.putExtra("text3", doctor_details[i][1]);
                it.putExtra("text4", doctor_details[i][3]);
                it.putExtra("text5", doctor_details[i][4]);
                startActivity(it);


            }
        });
    }
}
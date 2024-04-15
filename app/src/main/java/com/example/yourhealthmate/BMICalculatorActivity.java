package com.example.yourhealthmate;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class BMICalculatorActivity extends AppCompatActivity {

    EditText edW, edH;
    TextView tv;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmicalculator);

        edW = findViewById(R.id.editTextWeight);
        edH = findViewById(R.id.editTextHeight);
        btn = findViewById(R.id.buttonCalculateBmi);
        tv =  findViewById(R.id.textViewbmi);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String weight = edW.getText().toString();
                String height = edH.getText().toString();
                if (!weight.isEmpty() && !height.isEmpty()) {
                    double wt = Double.parseDouble(weight);
                    double ht = Double.parseDouble(height);
                    new FetchBmiTask().execute(wt, ht);
                } else {
                    tv.setText("Please enter weight and height.");
                }
            }
        });
        
    }


    private class FetchBmiTask extends AsyncTask<String, Void, String>{


        @Override
        protected String doInBackground(String... params) {
            String apiKey = "10e50a1c3emsh4e0718557acbc4fp11491bjsn87589bd53cc8";
            double weight  = Double.parseDouble(params[0]);
            double height = Double.parseDouble(params[1]);
            String apiURL = "https://body-mass-index-bmi-calculator.p.rapidapi.com/metric"+ weight + height + apiKey;


            try {
                URL url = new URL(apiURL);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = connection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null){
                    stringBuilder.append(line).append("   ");
                }
                bufferedReader.close();
                return stringBuilder.toString();
            }
            catch (IOException e) {
                e.printStackTrace();
                return null;

            }

        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (result != null){
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    JSONObject mainObject = jsonObject.getJSONObject("main");
                    double bmi = mainObject.getDouble("bmi");
                    JSONArray bmiArray = jsonObject.getJSONArray("BMI ");

                    JSONArray bmiarrayrec = bmiArray.getJSONArray(0);
                    String bmidesc = bmiarrayrec.getString(1);

                    String BmiResult = "BMI: " + bmidesc + "Status: "+ bmi ;
                    tv.setText(BmiResult);



                }catch(JSONException e){
                    e.printStackTrace();
                }

            }
        }

        public void execute(double wt, double ht) {
        }
    }
}











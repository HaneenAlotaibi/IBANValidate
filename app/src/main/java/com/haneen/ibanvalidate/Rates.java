package com.haneen.ibanvalidate;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Rates extends AppCompatActivity {
    EditText amount;
    TextView EUR,AED,USD,GBP;
    String url;
    Button Convert;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rates);
        amount = findViewById(R.id.editTextNumber);
        Convert = findViewById(R.id.button2);
        EUR =findViewById(R.id.EURValue);
        AED =findViewById(R.id.AEDValue);
        USD =findViewById(R.id.USDValue);
        GBP =findViewById(R.id.GBPValue);


        Convert.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
//
               System.out.println("URL: " + url);
                url = "https://api.apilayer.com/fixer/convert?to=EUR&from=KWD&amount=" + amount.getText().toString() + "&apikey=PkxRBFn5iSacIOm9eTDSPvfbSENIGBGn";
                Json(url,EUR);
                System.out.println("URL: " + url);

                url = "https://api.apilayer.com/fixer/convert?to=AED&from=KWD&amount=" + amount.getText().toString() + "&apikey=PkxRBFn5iSacIOm9eTDSPvfbSENIGBGn";
                Json(url,AED);
                System.out.println("URL: " + url);
                url = "https://api.apilayer.com/fixer/convert?to=USD&from=KWD&amount=" + amount.getText().toString() + "&apikey=PkxRBFn5iSacIOm9eTDSPvfbSENIGBGn";
                Json(url,USD);
                System.out.println("URL: " + url);
                url = "https://api.apilayer.com/fixer/convert?to=GBP&from=KWD&amount=" + amount.getText().toString() + "&apikey=PkxRBFn5iSacIOm9eTDSPvfbSENIGBGn";
                Json(url,GBP);

            }
        });
    }

    public void Json(String url1,TextView Currency ) {

        // System.out.println("inside json");

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url1, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    System.out.println("inside ");
                    System.out.println("Currency" +Currency);

                    String result = response.getString("result");
                    System.out.println(result);
                    Currency.setVisibility(View.VISIBLE);
                    Currency.setText(result);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });


        Volley.newRequestQueue(Rates.this).add(request);
    }

}
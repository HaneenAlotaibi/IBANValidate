package com.haneen.ibanvalidate;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
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

public class Rates extends AppCompatActivity {
    EditText amount;
    TextView text;
    String url;
    Button Convert;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rates);
        amount = findViewById(R.id.editTextNumber);
        Convert = findViewById(R.id.button2);
        text=findViewById(R.id.EURValue);
        url = "https://api.apilayer.com/fixer/convert?to=EUR&from=KWD&amount=5&apikey=PkxRBFn5iSacIOm9eTDSPvfbSENIGBGn";
        System.out.println("url "+url);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String result = response.getString("result");
                    Convert.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                                System.out.println("result"+result);
                                text.setVisibility(View.VISIBLE);
                                text.setText(result);
                        }


                    });
//
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });


        Volley.newRequestQueue(this).add(request);
    }
}
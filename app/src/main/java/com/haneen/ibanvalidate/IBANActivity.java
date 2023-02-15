package com.haneen.ibanvalidate;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
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

public class IBANActivity extends AppCompatActivity {
    TextView IBAN;
    String url;
    EditText mEdit;
    Button mButton, move;
    TextView text;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iban);
        mEdit = findViewById(R.id.IBANID);
        mButton = findViewById(R.id.button);
        move = findViewById(R.id.move);
        IBAN = findViewById(R.id.textView2);
        text = findViewById(R.id.text);


        System.out.println("empty" + mEdit.getText().toString());

        mButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println("mEdit: " + mEdit.getText().toString());
                url = "https://api.apilayer.com/bank_data/iban_validate?iban_number=" + mEdit.getText().toString() + "&apikey=PkxRBFn5iSacIOm9eTDSPvfbSENIGBGn";
                System.out.println("URL: " + url);
                Json(url);
            }
        });
        move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(IBANActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void Json(String url1) {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url1, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String iban = response.getString("message");
                    IBAN.setVisibility(View.VISIBLE);
                    IBAN.setText(iban);
//

                    Log.d("MainActivity", "iban");

                } catch (JSONException e) {
                    e.printStackTrace();

                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                IBAN.setVisibility(View.VISIBLE);
                IBAN.setText("IBAN number is invalid");

            }
        });
        System.out.println(IBAN.getText().toString());
        Volley.newRequestQueue(IBANActivity.this).add(request);
    }

}




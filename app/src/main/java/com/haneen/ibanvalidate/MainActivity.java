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

public class MainActivity extends AppCompatActivity {
    TextView IBAN;
    String url;
    EditText mEdit;
    Button mButton,move;
    TextView text;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEdit = findViewById(R.id.IBANID);
        mButton = findViewById(R.id.button);
        move=findViewById(R.id.move);
        IBAN = findViewById(R.id.textView2);
        text=findViewById(R.id.text);
        move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,Rates.class);
                startActivity(intent);
            }
        });

//
        url = "https://api.apilayer.com/bank_data/iban_validate?iban_number=DE89370400440532013000&apikey=PkxRBFn5iSacIOm9eTDSPvfbSENIGBGn";
        System.out.println("empty"+mEdit.getText().toString());

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String iban = response.getString("iban");
                    IBAN.setText(iban);

                    Log.d("MainActivity", "iban");
                    mButton.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {

                            if(mEdit.getText().toString().equals(iban))
                            {
                                System.out.println("iban"+iban);
                                System.out.println(IBAN.getText().toString());
                                text.setVisibility(View.VISIBLE);
                                text.setText("the IBAN is valid");}
                            if(!mEdit.getText().toString().equals(iban)) {
                                text.setVisibility(View.VISIBLE);
                                text.setText("the IBAN is invalid");
                            }
                        }
                    });
//                    clickbutton( view,iban);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        System.out.println(IBAN.getText().toString());

        Volley.newRequestQueue(this).add(request);
//        mButton.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View view) {
//            Toast.makeText(getApplicationContext(), "the IBAN is valid ", Toast.LENGTH_SHORT).show();
//        }
//    })

}
//    public void clickbutton(View view,String iban){
//        if(iban.equals(IBAN.getText().toString()))
//        { Toast.makeText(getApplicationContext(), "the IBAN is valid ", Toast.LENGTH_SHORT).show();
//      text.setVisibility(View.VISIBLE);
//      text.setText("the IBAN is valid");}
//        else {
//            text.setVisibility(View.VISIBLE);
//            text.setText("the IBAN is invalid");
//        }

    }




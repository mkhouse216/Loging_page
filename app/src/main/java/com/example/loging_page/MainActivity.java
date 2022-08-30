package com.example.loging_page;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.button.MaterialButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    ImageView male;
    MaterialButton containedButton;
    EditText pass, email;
    TextView signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        male = findViewById(R.id.male);
        signup = findViewById(R.id.signup);
        email = findViewById(R.id.email);
        pass = findViewById(R.id.pass);
        containedButton = findViewById(R.id.containedButton);

        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                email.setError(null);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (email.getText().toString().equals("")) {
                    email.setError("Enter email");
                } else {
                    email.setError(null);
                }
            }
        });

        pass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                pass.setError(null);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (pass.getText().toString().equals("")) {
                    pass.setError("Enter pass");
                } else {
                    pass.setError(null);
                }
            }
        });

        containedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (email.getText().toString().trim().equalsIgnoreCase("")) {
                    email.setError("enter name");
                } else if (pass.getText().toString().trim().equalsIgnoreCase("")) {
                    pass.setError("enter number ");
                } else {

                    String email1 = email.getText().toString().toLowerCase();
                    String pass1 = pass.getText().toString().toLowerCase();


                    RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                    String url = "https://quakier-multitask.000webhostapp.com/ApiCalling/login.php";

// Request a string response from the provided URL.
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {

                                    Log.e("=====R", "onResponse: " + response);


                                    try {
                                        JSONObject jb = new JSONObject(response);

                                        int bb = jb.getInt("connection");
                                        int res = jb.getInt("result");

                                        JSONObject user = jb.getJSONObject("UserData");

                                        String id = user.getString("Id");
                                        String Name = user.getString("Name");
                                        String Email = user.getString("Email");
                                        String Number = user.getString("Number");
                                        String Dob = user.getString("Dob");
                                        String Password = user.getString("Password");
                                        String Imagename = user.getString("Imagename");

                                        Splash_Activity.editor.putBoolean("logisttuaus", true);

                                        Splash_Activity.editor.putString("id", id);
                                        Splash_Activity.editor.putString("Name", Name);
                                        Splash_Activity.editor.putString("Email", Email);
                                        Splash_Activity.editor.putString("Number", Number);
                                        Splash_Activity.editor.putString("Dob", Dob);
                                        Splash_Activity.editor.putString("Password", Password);
                                        Splash_Activity.editor.putString("Imagename", Imagename);


                                        Splash_Activity.editor.apply();

                                        Intent intent = new Intent(MainActivity.this, Deta_activity.class);
                                        startActivity(intent);


                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                            Log.e("=====E", "onResponse: " + error.getLocalizedMessage());

                        }
                    }) {

                        @Nullable
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {

                            HashMap map = new HashMap();

                            map.put("email", email1);
                            map.put("password", pass1);

                            return map;
                        }
                    };
                    queue.add(stringRequest);

                    Toast.makeText(MainActivity.this, "done", Toast.LENGTH_SHORT).show();

                }
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });
    }
}
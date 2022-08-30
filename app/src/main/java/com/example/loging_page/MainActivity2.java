package com.example.loging_page;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.button.MaterialButton;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity2 extends AppCompatActivity {

    EditText user, email, num, dob, pass;
    MaterialButton sub;
    CircleImageView userdp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        user = findViewById(R.id.user);
        email = findViewById(R.id.email);
        num = findViewById(R.id.num);
        dob = findViewById(R.id.dob);
        pass = findViewById(R.id.pass);
        sub = findViewById(R.id.sub);
        userdp = findViewById(R.id.userdp);


        userdp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CropImage.activity()
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .start(MainActivity2.this);

            }
        });


        user.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                user.setError(null);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (user.getText().toString().equals("")) {
                    user.setError("Enter user name");
                } else {
                    user.setError(null);
                }
            }
        });
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
        num.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                num.setError(null);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (num.getText().toString().equals("")) {
                    num.setError("Enter number");
                } else {
                    num.setError(null);
                }
            }
        });
        dob.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                dob.setError(null);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (dob.getText().toString().equals("")) {
                    dob.setError("Enter dob");
                } else {
                    dob.setError(null);
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

        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user.getText().toString().trim().equalsIgnoreCase("")) {
                    user.setError("enter user name");
                } else if (email.getText().toString().trim().equalsIgnoreCase("")) {
                    email.setError("enter email ");
                } else if (num.getText().toString().trim().equalsIgnoreCase("")) {
                    num.setError("enter number ");
                } else if (dob.getText().toString().trim().equalsIgnoreCase("")) {
                    dob.setError("enter dob ");
                } else if (pass.getText().toString().trim().equalsIgnoreCase("")) {
                    pass.setError("enter pass ");
                } else {

                    String user1 = user.getText().toString();
                    String email1 = email.getText().toString();
                    String num1 = num.getText().toString();
                    String dob1 = dob.getText().toString();
                    String pass1 = pass.getText().toString();



                    Bitmap bitmap = ((BitmapDrawable) userdp.getDrawable()).getBitmap();
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                    byte[] imageInByte = baos.toByteArray();

                    String imagedata = Base64.encodeToString(imageInByte,0);

//save your stuff

                    RequestQueue queue = Volley.newRequestQueue(MainActivity2.this);
                    String url = "https://quakier-multitask.000webhostapp.com/ApiCalling/register.php";

// Request a string response from the provided URL.
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {

                                    Log.e("=====R", "onResponse: " + response);

                                    Toast.makeText(MainActivity2.this, "done", Toast.LENGTH_SHORT).show();
                                    Intent intent=new Intent(MainActivity2.this,MainActivity.class);
                                    startActivity(intent);
                                    // Display the first 500 characters of the response string.loge
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                            Log.e("=====E", "onResponse: " + error);

                        }
                    })  {

                        @Nullable
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {

                            HashMap map  = new HashMap();

                            map.put("name",user1);
                            map.put("email",email1);
                            map.put("number",num1);
                            map.put("dob",dob1);
                            map.put("password",pass1);
                            map.put("imagedata",imagedata);

                            return map;
                        }
                    };

// Add the request to the RequestQueue.
                    queue.add(stringRequest);



                }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                Uri resultUri = result.getUri();

                userdp.setImageURI(resultUri);

            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }
    }
}
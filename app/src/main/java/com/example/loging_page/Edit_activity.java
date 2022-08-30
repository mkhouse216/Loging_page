package com.example.loging_page;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class Edit_activity extends AppCompatActivity {


        String pname, pid, pdisc, pprice, img,imgname;
        CircleImageView proimg;
        EditText proaddpro, proprice, prod;
        Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

            pname = getIntent().getStringExtra("name");
            pid = getIntent().getStringExtra("id");
            pdisc = getIntent().getStringExtra("$description");
            pprice = getIntent().getStringExtra("price");
            img = getIntent().getStringExtra("$imagedata");
            imgname = getIntent().getStringExtra("$imagename");

        proimg = findViewById(R.id.proimg);
        proaddpro = findViewById(R.id.proaddpro);
        proprice = findViewById(R.id.proprice);
        prod = findViewById(R.id.prod);
        submit = findViewById(R.id.submit);


            Glide.with(Edit_activity.this).load(img).into(proimg);

            proaddpro.setText(pname);
            proprice.setText(pprice);
            prod.setText(pdisc);

            proimg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(pickPhoto, 1);//one can be replaced with any action code

                }
            });

            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String pname = proaddpro.getText().toString();
                    String price = proprice.getText().toString();
                    String pdis = prod.getText().toString();


                    Bitmap bitmap = ((BitmapDrawable) proimg.getDrawable()).getBitmap();
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 70, baos);
                    byte[] imageInByte = baos.toByteArray();

                    String imagedata = Base64.encodeToString(imageInByte, 0);

                    //save your stuff
                    RequestQueue queue = Volley.newRequestQueue(Edit_activity.this);
                    String url = "https://quakier-multitask.000webhostapp.com/ApiCalling/updateproduct.php";

                    // Request a string response from the provided URL.
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {

                                    Log.e("====RR", "onResponse: " + response);

                                    startActivity(new Intent(Edit_activity.this, Deta_activity.class));
                                    finish();
                                    // Display the first 500 characters of the response string.loge
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                            Log.e("=====E", "onResponse: " + error);
                        }
                    }) {

                        @Nullable
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {

                            HashMap map = new HashMap();

                            map.put("id", pid);
                            map.put("name", pname);
                            map.put("price", price);
                            map.put("description", pdis);
                            map.put("imagedata", imagedata);
                            map.put("imagename", imgname);

                            return map;
                        }
                    };
                    // Add the request to the RequestQueue.
                    queue.add(stringRequest);

                    Toast.makeText(Edit_activity.this, "Listing Updated", Toast.LENGTH_SHORT).show();

                }
            });
        }

        public void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
            super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
            switch (requestCode) {

                case 0:
                    if (resultCode == RESULT_OK) {
                        Uri selectedImage = imageReturnedIntent.getData();
                        proimg.setImageURI(selectedImage);
                    }
                    break;
                case 1:
                    if (resultCode == RESULT_OK) {
                        Uri selectedImage = imageReturnedIntent.getData();
                        proimg.setImageURI(selectedImage);
                    }
                    break;
                default:
                    break;
            }
        }
    }

package com.example.loging_page.Fragmnet;

import static android.app.Activity.RESULT_OK;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.loging_page.Deta_activity;
import com.example.loging_page.MainActivity;
import com.example.loging_page.MainActivity2;
import com.example.loging_page.R;
import com.example.loging_page.Splash_Activity;
import com.google.android.material.button.MaterialButton;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class Add_Product_Fragment extends Fragment {

    CircleImageView addp;
    EditText product, price, description;
    MaterialButton abutton;

    Context context;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_add__product_, container, false);

        context = view.getContext();
        product = view.findViewById(R.id.product);
        price = view.findViewById(R.id.price);
        description = view.findViewById(R.id.description);
        addp = view.findViewById(R.id.addp);
        abutton = view.findViewById(R.id.abutton);


        addp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(pickPhoto , 1);//one can be replaced with any action code


            }
        });



        abutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (product.getText().toString().trim().equalsIgnoreCase("")) {
                    product.setError("enter product ");
                } else if (price.getText().toString().trim().equalsIgnoreCase("")) {
                    price.setError("enter price ");
                } else if (description.getText().toString().trim().equalsIgnoreCase("")) {
                    description.setError("enter description ");
                } else {

                    String product1 = product.getText().toString();
                    String price1 = price.getText().toString();
                    String description1 = description.getText().toString();
                    String loginid = Splash_Activity.sp.getString("id","");

                    Bitmap bitmap = ((BitmapDrawable) addp.getDrawable()).getBitmap();
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                    byte[] imageInByte = baos.toByteArray();

                    String imagedata = Base64.encodeToString(imageInByte, 0);

                    RequestQueue queue = Volley.newRequestQueue(getActivity());
                    String url = "https://quakier-multitask.000webhostapp.com/ApiCalling/addprodcut.php";

                    StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {

                                    Log.e("===add", "onResponse: " + response);

                                    Toast.makeText(getActivity(), "done", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getActivity(), Deta_activity.class);
                                    startActivity(intent);

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

                            map.put("loginid",loginid);
                            map.put("product", product1);
                            map.put("price", price1);
                            map.put("description", description1);
                            map.put("imagedata", imagedata);

                            return map;
                        }
                    };
                    queue.add(stringRequest);
                }
            }

        });

        return view;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        switch(requestCode) {
            case 0:
                if(resultCode == RESULT_OK){
                    Uri selectedImage = imageReturnedIntent.getData();
                    addp.setImageURI(selectedImage);
                }

                break;
            case 1:
                if(resultCode == RESULT_OK){
                    Uri selectedImage = imageReturnedIntent.getData();
                    addp.setImageURI(selectedImage);
                }
                break;
        }
    }

}

package com.example.loging_page.Fragmnet;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
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
import com.example.loging_page.Myadapter;
import com.example.loging_page.R;
import com.example.loging_page.Splash_Activity;
import com.example.loging_page.Usermodel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class View_Product_Fragment extends Fragment {

RecyclerView viewp;
    ArrayList<Usermodel> list2 =new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_view__product_, container, false);

        String loginid = Splash_Activity.sp.getString("id", "");
        viewp = view.findViewById(R.id.viewp);

        RequestQueue queue = Volley.newRequestQueue(getActivity());
        String url = "https://quakier-multitask.000webhostapp.com/ApiCalling/viewproduct.php";

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.e("==adR", "onResponse: " + response);


                                    try {
                                        JSONObject jb = new JSONObject(response);

                                        int bb = jb.getInt("connection");
                                        int res = jb.getInt("result");

                                        JSONArray productdata=jb.getJSONArray("productdata");

                                        for(int i=0;i<productdata.length();i++) {
                                            JSONObject ja = productdata.getJSONObject(i);

                                            String Id = ja.getString("Id");
                                            String Userid = ja.getString("Userid");
                                            String Product = ja.getString("Product");
                                            String Price = ja.getString("Price");
                                            String Description = ja.getString("Description");
                                            String Image = ja.getString("Image");

                                            Usermodel usermodel =new Usermodel(Id,Userid,Product,Price,Description,Image);
                                            list2.add(usermodel);
                                        }
                                        Myadapter myadapter=new Myadapter(getActivity(),list2);
                                        viewp.setAdapter(myadapter);

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

                map.put("loginid", loginid);

                return map;
            }
        };
        queue.add(stringRequest);

        return view;
    }
}
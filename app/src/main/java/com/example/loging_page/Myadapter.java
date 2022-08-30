package com.example.loging_page;

import static com.example.loging_page.Splash_Activity.sp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Myadapter extends RecyclerView.Adapter<Myadapter.Myprod> {
    FragmentActivity activity;
    ArrayList<Usermodel> list2;

    public Myadapter(FragmentActivity activity, ArrayList<Usermodel> list2) {
        this.activity = activity;
        this.list2 = list2;
    }

    @NonNull
    @Override
    public Myprod onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.pro_layout, parent, false);

        return new Myprod(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Myadapter.Myprod holder,@SuppressLint("RecyclerView") int position) {
        Usermodel ll = list2.get(position);

        holder.Id.setText("Id :-" + ll.getId());
        holder.Userid.setText("Userid :-" + ll.getUserid());
        holder.Product.setText("Product :-" + ll.getProduct());
        holder.Price.setText("Price :-" + ll.getPrice0());
        holder.Description.setText("Description :-" + ll.getDescription());
        String aa = "https://quakier-multitask.000webhostapp.com/ApiCalling/"+ll.getImage();

        Log.e("====", "onBindViewHolder: "+ll.getImage() );

        Glide.with(activity).load(aa).into(holder.Image);

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                PopupMenu popmenu = new PopupMenu(activity, v);
                popmenu.getMenu().add(Menu.NONE, 1, 1, "edit");
                popmenu.getMenu().add(Menu.NONE, 2, 2, "delete");
                popmenu.show();

                popmenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        int i=item.getItemId();

                        String proid=ll.getId().toString();
                        String proname=ll.getProduct().toString();
                        String proprice=ll.getPrice0().toString();
                        String prodes=ll.getDescription().toString();
                        String proimg=ll.getImage().toString();

                        if(i==1)

                        {

                            Intent intent=new Intent(activity,Edit_activity.class);
                            intent.putExtra("id",proid);
                            intent.putExtra("name",proname);
                            intent.putExtra("price",proprice);
                            intent.putExtra("$description",prodes);
                            intent.putExtra("$imagedata",aa);
                            intent.putExtra("$imagename",proimg);
                            activity.startActivity(intent);

                        } else if (i == 2) {
                            //save your stuff
                            RequestQueue queue = Volley.newRequestQueue(activity);
                            String url = "https://quakier-multitask.000webhostapp.com/ApiCalling/updateproduct.php";

                            // Request a string response from the provided URL.
                            StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                                    new Response.Listener<String>() {
                                        @Override
                                        public void onResponse(String response) {

                                            Log.e("====ddd", "onResponse: " + response);

                                            list2.remove(position);
                                            notifyDataSetChanged();
                                            Intent intent=new Intent(activity,Delete_activity.class);
                                            activity.startActivity(intent);
                                        Toast.makeText(activity, "Listing Deleted", Toast.LENGTH_SHORT).show();
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

                                    map.put("id", proid);

                                    return map;
                                }
                            };
                            // Add the request to the RequestQueue.
                            queue.add(stringRequest);
                        }

                        return false;
                    }
                });
                return false;
            }
        });

    }

    @Override
    public int getItemCount() {
        return list2.size();
    }

    public class Myprod extends RecyclerView.ViewHolder {
        TextView Id, Userid, Product, Price, Description;
        ImageView Image;

        public Myprod(@NonNull View itemView) {
            super(itemView);

            Id = itemView.findViewById(R.id.Id);
            Userid = itemView.findViewById(R.id.Userid);
            Product = itemView.findViewById(R.id.Product);
            Price = itemView.findViewById(R.id.Price);
            Description = itemView.findViewById(R.id.Description);
            Image = itemView.findViewById(R.id.Image);

        }
    }
}

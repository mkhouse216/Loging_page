package com.example.loging_page;

import static com.example.loging_page.Splash_Activity.sp;

import androidx.annotation.GravityInt;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.loging_page.Fragmnet.Add_Product_Fragment;
import com.example.loging_page.Fragmnet.View_Product_Fragment;
import com.google.android.material.navigation.NavigationView;

import de.hdodenhof.circleimageview.CircleImageView;

public class Deta_activity extends AppCompatActivity {


    NavigationView navigationView;
    DrawerLayout drawerLayout;

    LinearLayout linearLayout;

    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_act);

        navigationView = findViewById(R.id.navivieww);
        drawerLayout = findViewById(R.id.drawelayout);
        linearLayout = findViewById(R.id.myview);
        toolbar = findViewById(R.id.toolbar);

        String  imagepath = sp.getString("Imagename","");
        String aa = "https://quakier-multitask.000webhostapp.com/ApiCalling/"+imagepath;

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(Deta_activity.this,drawerLayout,toolbar,R.string.open,R.string.close);
        actionBarDrawerToggle.syncState();


     View view = navigationView.getHeaderView(0);

        ImageView imageView = view.findViewById(R.id.dp);

        Glide.with(Deta_activity.this).load(aa).into(imageView);

        TextView textView = view.findViewById(R.id.myname);

        textView.setText(""+sp.getString("Name",null));

        TextView textView1 = view.findViewById(R.id.myemail);

        textView1.setText(""+sp.getString("Email",null));

        getSupportFragmentManager().beginTransaction().replace(R.id.myview,new View_Product_Fragment()).commit();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if(item.getItemId()==R.id.viewproduct){

                drawerLayout.closeDrawer(GravityCompat.START);
                getSupportFragmentManager().beginTransaction().replace(R.id.myview, new View_Product_Fragment()).commit();

            }
                else if(item.getItemId()==R.id.addproduct){
                    drawerLayout.closeDrawer(GravityCompat.START);
                    getSupportFragmentManager().beginTransaction().replace(R.id.myview, new Add_Product_Fragment()).commit();

                }
                else {
                    Splash_Activity.editor.putBoolean("logisttuaus",false);

                    Intent intent=new Intent(Deta_activity.this,MainActivity.class);
                    startActivity(intent);

                }


                return false;
            }
        });

//        a1=findViewById(R.id.a1);
//        a2=findViewById(R.id.a2);
//        a3=findViewById(R.id.a3);
//        a4=findViewById(R.id.a4);
//        a5=findViewById(R.id.a5);
//        a6=findViewById(R.id.a6);
//        dp=findViewById(R.id.dp);
//
//        a1.setText("id:"+sp.getString("id",null));
//        a2.setText("Name:"+sp.getString("Name",null));
//        a3.setText("Email:"+sp.getString("Email",null));
//        a4.setText("Number:"+sp.getString("Number",null));
//        a5.setText("Dob:"+sp.getString("Dob",null));
//        a6.setText("Password:"+sp.getString("Password",null));
//
//        String  imagepath = sp.getString("Imagename","");
//
//        Log.e("--->", "onCreate: "+imagepath );
//
//        String aa = "https://quakier-multitask.000webhostapp.com/ApiCalling/"+sp.getString("Imagename","");
//
//        Glide.with(Deta_activity.this).load(aa).into(dp);

    }
}
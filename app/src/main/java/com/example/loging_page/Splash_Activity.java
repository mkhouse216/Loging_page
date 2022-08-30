package com.example.loging_page;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class Splash_Activity extends AppCompatActivity {

    public static SharedPreferences sp;
    public static SharedPreferences.Editor editor;

    Boolean Logisttuaus=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        sp=getSharedPreferences("Demo",0);
        editor=sp.edit();
        Logisttuaus=sp.getBoolean("logisttuaus",false);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if(Logisttuaus){
                    startActivity(new Intent(Splash_Activity.this, Deta_activity.class));
                    finish();
                }
                else {
                    startActivity(new Intent(Splash_Activity.this, MainActivity.class));
                    finish();
                }
            }
        },3000);
    }
}
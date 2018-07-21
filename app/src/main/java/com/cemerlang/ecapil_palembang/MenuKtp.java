package com.cemerlang.ecapil_palembang;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;


/**
 * Created by RICHI on 2016/10/20.
 */

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class MenuKtp extends AppCompatActivity {
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.cemerlang.ecapil_palembang.R.layout.menu_ktp);
        setupToolbar();

        Button btx1 =(Button)findViewById(com.cemerlang.ecapil_palembang.R.id.button1);
        btx1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ak = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(ak);
            }
        });


        AdView mAdView = (AdView) findViewById(com.cemerlang.ecapil_palembang.R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        //button2
        Button btx2 =(Button)findViewById(com.cemerlang.ecapil_palembang.R.id.button2);
        btx2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ak = new Intent(getApplicationContext(), MainGanti.class);
                startActivity(ak);
            }
        });
        //button4
        Button btx4 =(Button)findViewById(com.cemerlang.ecapil_palembang.R.id.button4);
        btx4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ak = new Intent(getApplicationContext(), FormHilang.class);
                startActivity(ak);
            }
        });

        //button3
        Button btx3 =(Button)findViewById(com.cemerlang.ecapil_palembang.R.id.button3);
        btx3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ak = new Intent(getApplicationContext(), FormRusak.class);
                startActivity(ak);
            }
        });
    }


    private void setupToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }
}
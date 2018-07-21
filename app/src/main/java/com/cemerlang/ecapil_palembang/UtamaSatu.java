package com.cemerlang.ecapil_palembang;

/**
 * Created by RICHI on 2016/10/20.
 */
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
//import android.support.v7.widget.Toolbar;
//import android.view.Window;
public class UtamaSatu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(com.cemerlang.ecapil_palembang.R.layout.utama);


        Button btx1 =(Button)findViewById(com.cemerlang.ecapil_palembang.R.id.bt1);
        btx1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ak = new Intent(getApplicationContext(), Buka_Acti.class);
                startActivity(ak);
            }
        });

        Button btx2 =(Button)findViewById(com.cemerlang.ecapil_palembang.R.id.bt2);
        btx2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent ak=new Intent(getApplicationContext(),MenuKtp.class);
                startActivity(ak);
            }
        });
    }
}
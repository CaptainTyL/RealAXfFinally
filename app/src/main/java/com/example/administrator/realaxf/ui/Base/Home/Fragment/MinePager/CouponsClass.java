package com.example.administrator.realaxf.ui.Base.Home.Fragment.MinePager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.administrator.realaxf.R;

public class CouponsClass extends AppCompatActivity {
    private Button button;
    private ImageView imgZuoFanHui;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupons_class);
        imgZuoFanHui=findViewById(R.id.img_zuofanhui);
        imgZuoFanHui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        button=findViewById(R.id.bottom_duihuan);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(CouponsClass.this,DuiHuanClass.class);
                startActivity(intent);
            }
        });
    }
}

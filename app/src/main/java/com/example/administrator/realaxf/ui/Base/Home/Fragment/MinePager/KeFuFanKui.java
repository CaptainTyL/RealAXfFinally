package com.example.administrator.realaxf.ui.Base.Home.Fragment.MinePager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.administrator.realaxf.R;

public class KeFuFanKui extends AppCompatActivity {
    private ImageView imgZuoFanHui;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ke_fu_fan_kui);
        imgZuoFanHui=findViewById(R.id.img_zuofanhui);
        imgZuoFanHui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}

package com.example.administrator.realaxf.ui.Base.Home.Fragment.MinePager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.administrator.realaxf.R;

public class GuanLiShouHuoDiZhi extends AppCompatActivity {
    private ImageView imgZuoFanHui;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guan_li_shou_huo_di_zhi);
        imgZuoFanHui=findViewById(R.id.img_zuofanhui);
        button=findViewById(R.id.bottom);
        imgZuoFanHui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(GuanLiShouHuoDiZhi.this,CircleTurntableActivity.class);
                startActivity(intent);
            }
        });
    }
}

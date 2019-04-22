package com.example.administrator.realaxf.ui.Base.Home.Fragment.MinePager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chaychan.viewlib.NumberRunningTextView;
import com.example.administrator.realaxf.R;

public class DuiHuanClass extends AppCompatActivity {

    private ImageView imgZuoFanHui;
    private NumberRunningTextView tvJifena;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_duihuanclass);
        imgZuoFanHui=findViewById(R.id.img_zuofanhui);
        imgZuoFanHui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tvJifena=findViewById(R.id.tv_jifena);
        tvJifena.setContent("100.00");

    }
}

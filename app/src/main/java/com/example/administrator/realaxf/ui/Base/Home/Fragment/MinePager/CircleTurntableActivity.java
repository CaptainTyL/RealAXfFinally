package com.example.administrator.realaxf.ui.Base.Home.Fragment.MinePager;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.administrator.realaxf.R;
import com.lljjcoder.citypickerview.widget.CityPicker;

public class CircleTurntableActivity extends AppCompatActivity {

    private ImageView imgZuoFanHui;
    private EditText edMinechengshi;
    private CityPicker cityPicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_turntable);
        imgZuoFanHui=findViewById(R.id.img_zuofanhui);
        imgZuoFanHui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        edMinechengshi=(EditText)findViewById(R.id.ed_mine_chengshi);
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(edMinechengshi.getWindowToken(),0);
        edMinechengshi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initCityPicker();
                cityPicker.show();

            }
        });
    }

    public void initCityPicker() {

        //滚轮文字的大小
        //滚轮文字的颜色
//省份滚轮是否循环显示
        //城市滚轮是否循环显示
        //地区（县）滚轮是否循环显示
        //滚轮显示的item个数
        //滚轮item间距
        cityPicker = new CityPicker.Builder(CircleTurntableActivity.this)
                .textSize(15)//滚轮文字的大小
                .title("地址选择")
                .backgroundPop(0xa0000000)
                .titleBackgroundColor("#0CB6CA")
                .titleTextColor("#000000")
                .backgroundPop(0xa0000000)
                .confirTextColor("#000000")
                .cancelTextColor("#000000")
                .province("xx省")
                .city("xx市")
                .district("xx区")
                .textColor(Color.parseColor("#000000"))//滚轮文字的颜色
                .provinceCyclic(true)//省份滚轮是否循环显示
                .cityCyclic(false)//城市滚轮是否循环显示
                .districtCyclic(false)//地区（县）滚轮是否循环显示
                .visibleItemsCount(7)//滚轮显示的item个数
                .itemPadding(10)//滚轮item间距
                .onlyShowProvinceAndCity(false)
                .build();

        //监听方法，获取选择结果
        cityPicker.setOnCityItemClickListener(new CityPicker.OnCityItemClickListener() {

            public void onSelected(String... citySelected) {
                //省份
                String province = citySelected[0];
                //城市
                String city = citySelected[1];
                //区县（如果设定了两级联动，那么该项返回空）
                String district = citySelected[2];
                //邮编
                String code = citySelected[3];

                edMinechengshi.setText(province + city + district);
                Log.e("aa",edMinechengshi.getText().toString());
            }


            public void onCancel() {


            }
        });
    }

}

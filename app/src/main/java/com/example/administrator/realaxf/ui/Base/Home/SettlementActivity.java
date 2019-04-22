package com.example.administrator.realaxf.ui.Base.Home;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.administrator.realaxf.R;
import com.example.administrator.realaxf.ui.Base.Adapter.VerifyOrderAdapter;
import com.example.administrator.realaxf.ui.Base.Base.BaseActivity;
import com.example.administrator.realaxf.ui.Base.Home.Bean.VerifyOrderBean;
import com.example.administrator.realaxf.ui.Base.Util.SharePrefUtil;
import com.example.administrator.realaxf.ui.Base.okhttputils.OkHttpHelper;
import com.example.administrator.realaxf.ui.Base.okhttputils.SpotsCallBack;
import com.squareup.okhttp.Response;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class SettlementActivity extends BaseActivity {

    OkHttpHelper okHttpHelper=OkHttpHelper.getInstance();
    String jsonStr="";
    List<VerifyOrderBean.DataBean> list=new ArrayList<>();

    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.rely_verifyorder)
    RecyclerView relyVerifyOrder;
    @BindView(R.id.verify_otder_otal_price1)
    TextView verifyOtderTotalPrice1;
    @BindView(R.id.verify_otder_otal_price2)
    TextView verifyOtderTotalPrice2;
    @BindView(R.id.verify_otder_otal_price3)
    TextView verifyOtderTotalPrice3;
    @BindView(R.id.set_ok)
    TextView setTv;
    VerifyOrderAdapter verifyOrderAdapter;
    LinearLayoutManager linearLayoutManager;

    @Override
    public void init() {
        setFinsh(imgBack);
        Intent intent=getIntent();
        jsonStr= intent.getStringExtra("jsonStr");
        Log.e("",""+jsonStr);
        getHttpSettlementActivity(jsonStr);
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        relyVerifyOrder.setLayoutManager(linearLayoutManager);
        setTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num=list.size();
                SharePrefUtil.saveString(getBaseContext(),"num","num");
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_settlement;
    }

    private void getHttpSettlementActivity(String json){
        String url = "http://questionnaire.dzqcedu.com:81/Shop/confirmorder";
        String userId = SharePrefUtil.getString(this, "UserId", "0");
        Map<String, String> map = new HashMap<>();
        map.put("userId", userId);
        map.put("json", json);



        okHttpHelper.post(url, map, new SpotsCallBack<VerifyOrderBean>(SettlementActivity.this,true) {
            @Override
            public void onSuccess(Response response, VerifyOrderBean o) throws ParseException {
                if (o!=null){
                    verifyOtderTotalPrice1.setText("总价：￥"+o.getTotalprice());
                    verifyOtderTotalPrice2.setText("￥"+o.getTotalprice());
                    verifyOtderTotalPrice3.setText("￥"+o.getTotalprice());
                    list.addAll(o.getData());
                    verifyOrderAdapter = new VerifyOrderAdapter(list);
                    relyVerifyOrder.setAdapter(verifyOrderAdapter);
                }

            }

            @Override
            public void onError(Response response, int code, Exception e) {

            }
        });
    }


}

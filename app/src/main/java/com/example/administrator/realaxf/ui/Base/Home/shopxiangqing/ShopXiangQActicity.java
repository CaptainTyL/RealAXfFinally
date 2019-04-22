package com.example.administrator.realaxf.ui.Base.Home.shopxiangqing;

import android.content.Intent;
import android.graphics.Paint;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.administrator.realaxf.R;
import com.example.administrator.realaxf.ui.Base.Base.BaseActivity;
import com.example.administrator.realaxf.ui.Base.Home.Bean.AddShopCarBean;
import com.example.administrator.realaxf.ui.Base.Util.SharePrefUtil;
import com.example.administrator.realaxf.ui.Base.okhttputils.OkHttpHelper;
import com.example.administrator.realaxf.ui.Base.okhttputils.SpotsCallBack;
import com.squareup.okhttp.Response;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import cn.sharesdk.onekeyshare.OnekeyShare;

public class ShopXiangQActicity extends BaseActivity {
    @BindView(R.id.tv_xQshop_name)
    TextView tvXQshopName;
    @BindView(R.id.img_xiangqing_shopImg)
    ImageView imgXiangqingShopImg;
    @BindView(R.id.tv_big_shopName)
    TextView tvBigShopName;
    @BindView(R.id.tv_VipPrice)
    TextView tvVipPrice;
    @BindView(R.id.tv_nolmarprice)
    TextView tvNolmarprice;
    @BindView(R.id.img_order_back)
    ImageView imgOrderBack;
    @BindView(R.id.img_shard)
    ImageView imgShard;
    @BindView(R.id.img_addcar_shop)
    RelativeLayout imgAddcarShop;
    String GoodsId;
    private OkHttpHelper okHttpHelper=OkHttpHelper.getInstance();
    @Override
    public void init() {
        setFinsh(imgOrderBack);
        Intent intent=getIntent();
        String shopName=intent.getStringExtra("shopName");
        String shopImg=intent.getStringExtra("shopImage");
        String vipPrice=intent.getStringExtra("vipPrice");
        String nomalPrice=intent.getStringExtra("nomalPrice");
        GoodsId=intent.getStringExtra("goodsId");
        tvXQshopName.setText(shopName);
        tvBigShopName.setText(shopName);
        tvNolmarprice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG|Paint.ANTI_ALIAS_FLAG);
        tvVipPrice.setText("￥"+vipPrice);
        tvNolmarprice.setText("￥"+nomalPrice);
        Glide.with(ShopXiangQActicity.this).load(shopImg).into(imgXiangqingShopImg);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_shopxiangqing;
    }
    @OnClick({R.id.img_shard,R.id.img_addcar_shop})void onclick(View view){
        switch (view.getId()){
            case R.id.img_shard:
                showShare();
                break;
            case R.id.img_addcar_shop:
                AddShopToShopCarList(GoodsId);
                break;
        }
    }
    private void showShare() {
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

        // title标题，微信、QQ和QQ空间等平台使用
        oks.setTitle("哈哈哈");
        // titleUrl QQ和QQ空间跳转链接
        oks.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url在微信、微博，Facebook等平台中使用
        oks.setUrl("http://sharesdk.cn");
        // comment是我对这条分享的评论，仅在人人网使用
        oks.setComment("我是测试评论文本");
        // 启动分享GUI
        oks.show(this);
    }
    private void AddShopToShopCarList(String commodityId){
        String userId=SharePrefUtil.getString(ShopXiangQActicity.this,"UserId","1");
        String url="http://questionnaire.dzqcedu.com:81/Shop/addcart";
        Map<String,String> map=new HashMap<>();
        map.put("userId",userId);
        map.put("commodityId",commodityId);
        okHttpHelper.post(url, map, new SpotsCallBack<AddShopCarBean>(ShopXiangQActicity.this,true) {
            @Override
            public void onSuccess(Response response, AddShopCarBean o) throws ParseException {
                Toast.makeText(ShopXiangQActicity.this, "添加购物车成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Response response, int code, Exception e) {

            }
        });
    }
}

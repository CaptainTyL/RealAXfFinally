package com.example.administrator.realaxf.ui.Base.Home.Fragment;

import android.content.Intent;
import android.hardware.camera2.CameraCaptureSession;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.realaxf.R;

import com.example.administrator.realaxf.ui.Base.Adapter.HotAdapter;
import com.example.administrator.realaxf.ui.Base.Adapter.TejiaAdapter;
import com.example.administrator.realaxf.ui.Base.Base.BaseFragment;
import com.example.administrator.realaxf.ui.Base.Home.Bean.TejiaShopListBean;
import com.example.administrator.realaxf.ui.Base.Home.adress.AdressActivty;
import com.example.administrator.realaxf.ui.Base.Home.search.SearchActivity;
import com.example.administrator.realaxf.ui.Base.Home.shopxiangqing.ShopXiangQActicity;
import com.example.administrator.realaxf.ui.Base.Util.SharePrefUtil;
import com.example.administrator.realaxf.ui.Base.okhttputils.OkHttpHelper;
import com.example.administrator.realaxf.ui.Base.okhttputils.SpotsCallBack;
import com.github.library.listener.OnRecyclerItemClickListener;
import com.recker.flybanner.FlyBanner;
import com.stx.xhb.xbanner.XBanner;
import com.yzq.zxinglibrary.android.CaptureActivity;
import com.yzq.zxinglibrary.bean.ZxingConfig;
import com.yzq.zxinglibrary.common.Constant;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ShouyeFragment extends BaseFragment {
    @BindView(R.id.tv_shouye_address)
    TextView tvShouyeAddress;
    @BindView(R.id.img_shouye_sousuo)
    ImageView imgShouyeSousuo;
    @BindView(R.id.rely_home_tejia)
    RecyclerView relyHomeTejia;
    @BindView(R.id.rely_home_shangxin)
    RecyclerView relyHomeShangxin;
    @BindView(R.id.rely_home_hot)
    RecyclerView relyHomeHot;
    @BindView(R.id.home_swip)
    SwipeRefreshLayout homeSwip;
    @BindView(R.id.fly_home_imglunbo)
    FlyBanner flyHomeImglunbo;
    @BindView(R.id.img_shouye_saoyisao)
    ImageView imgShouyeSaoyisao;
    //声明集合存放特价实体类
    List<TejiaShopListBean.DataBean> listBeans = new ArrayList<>();
    //存放上新商品的实体类
    List<TejiaShopListBean.DataBean> Shangxinlist = new ArrayList<>();
    //存放热销商品的实体类集合
    List<TejiaShopListBean.DataBean> hotList = new ArrayList<>();
    //实现网络框架的类
    OkHttpHelper okHttpHelper = OkHttpHelper.getInstance();
    private TejiaAdapter tejiaAdapter;
    private HotAdapter hotAdapter;
    LinearLayoutManager linearLayoutManager, linearLayoutManager1;
    GridLayoutManager gridLayoutManager;

    @Override
    public void init() {
        setFlBanner();
        //特价和上新的布局管理
        linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        //
        linearLayoutManager1 = new LinearLayoutManager(getActivity());
        linearLayoutManager1.setOrientation(LinearLayoutManager.HORIZONTAL);
        //热销商品的垂直布局
        gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        getHttpSHopListData("0");
        getHttpSHopListData("1");
        getHttpSHopListData("2");
        homeSwip.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getHttpSHopListData("0");
                getHttpSHopListData("1");
                getHttpSHopListData("2");
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @OnClick({R.id.tv_shouye_address, R.id.img_shouye_sousuo,R.id.img_shouye_saoyisao})
    void onclick(View v) {
        switch (v.getId()) {
            case R.id.tv_shouye_address:
                Intent intent = new Intent(getActivity(), AdressActivty.class);
                startActivityForResult(intent, 0);
                break;
            case R.id.img_shouye_sousuo:
                Intent intent1 = new Intent(getActivity(), SearchActivity.class);
                startActivity(intent1);
                break;
            case R.id.img_shouye_saoyisao:
                Intent intent2=new Intent(getActivity(),CaptureActivity.class);
                ZxingConfig config = new ZxingConfig();
                config.setPlayBeep(true);//是否播放扫描声音 默认为true
                config.setShake(true);//是否震动  默认为true
                config.setDecodeBarCode(true);//是否扫描条形码 默认为true
                config.setReactColor(R.color.colorAccent);//设置扫描框四个角的颜色 默认为白色
                config.setFrameLineColor(R.color.colorAccent);//设置扫描框边框颜色 默认无色
                config.setScanLineColor(R.color.colorAccent);//设置扫描线的颜色 默认白色
                config.setFullScreenScan(false);//是否全屏扫描  默认为true  设为false则只会在扫描框中扫描
                intent2.putExtra(Constant.INTENT_ZXING_CONFIG, config);
                startActivityForResult(intent2,2);
                break;
        }
    }
    //接收传过来的值

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 0:
                if (resultCode == 1) {
                    String cityName = data.getStringExtra("cityName");
                    tvShouyeAddress.setText(cityName);
                }
                break;
        }
    }

    //请求商品数据
    private void getHttpSHopListData(final String listType) {
        String userId = SharePrefUtil.getString(getActivity(), "UserId", "1");
        String userName = SharePrefUtil.getString(getActivity(), "UserName", "2");
        String url = "http://questionnaire.dzqcedu.com:81/Shop/category";
        Map<String, String> map = new HashMap<>();
        map.put("userId", userId);
        map.put("sortingType", "0");
        map.put("listType", listType);
        map.put("mark", userName);
        okHttpHelper.post(url, map, new SpotsCallBack<TejiaShopListBean>(getActivity(), true) {
            @Override
            public void onSuccess(com.squareup.okhttp.Response response, TejiaShopListBean tejiaShopListBean) throws ParseException {
                //请求到的数据存放在实体类中，进行与列表适配
                if (tejiaShopListBean.getData() != null) {
                    homeSwip.setRefreshing(false);
                    if (listType == "0") {//特价的数据适配
                        //将数据给列表
                        listBeans = tejiaShopListBean.getData();
                        //数据适配给列表
                        relyHomeTejia.setLayoutManager(linearLayoutManager);
                        tejiaAdapter = new TejiaAdapter(getActivity(), listBeans);
                        relyHomeTejia.setAdapter(tejiaAdapter);
                        tejiaAdapter.setOnRecyclerItemClickListener(new OnRecyclerItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                Intent intent = new Intent(getActivity(), ShopXiangQActicity.class);
                                intent.putExtra("shopImage", listBeans.get(position).getGoodsbigurl());
                                intent.putExtra("shopName", listBeans.get(position).getGoodsname());
                                intent.putExtra("vipPrice", listBeans.get(position).getGoodsvipprice());
                                intent.putExtra("nomalPrice", listBeans.get(position).getGoodsprice());
                                intent.putExtra("goodsId",listBeans.get(position).getGoodsid());
                                startActivity(intent);
                            }
                        });
                    } else if (listType == "1") {//上新的数据适配
                        Shangxinlist = tejiaShopListBean.getData();
                        relyHomeShangxin.setLayoutManager(linearLayoutManager1);
                        tejiaAdapter = new TejiaAdapter(getActivity(), Shangxinlist);
                        relyHomeShangxin.setAdapter(tejiaAdapter);
                        tejiaAdapter.setOnRecyclerItemClickListener(new OnRecyclerItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                Intent intent = new Intent(getActivity(), ShopXiangQActicity.class);
                                intent.putExtra("shopImage", Shangxinlist.get(position).getGoodsbigurl());
                                intent.putExtra("shopName", Shangxinlist.get(position).getGoodsname());
                                intent.putExtra("vipPrice", Shangxinlist.get(position).getGoodsvipprice());
                                intent.putExtra("nomalPrice", Shangxinlist.get(position).getGoodsprice());
                                intent.putExtra("goodsId",Shangxinlist.get(position).getGoodsid());
                                startActivity(intent);
                            }
                        });
                    } else if (listType == "2") {//热销的数据匹配
                        hotList = tejiaShopListBean.getData();
                        relyHomeHot.setLayoutManager(gridLayoutManager);

                        //禁止滑动
                        relyHomeHot.setNestedScrollingEnabled(false);
                        hotAdapter = new HotAdapter(getActivity(), hotList);
                        relyHomeHot.setAdapter(hotAdapter);
                        hotAdapter.setOnRecyclerItemClickListener(new OnRecyclerItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                Intent intent = new Intent(getActivity(), ShopXiangQActicity.class);
                                intent.putExtra("shopImage", hotList.get(position).getGoodsbigurl());
                                intent.putExtra("shopName", hotList.get(position).getGoodsname());
                                intent.putExtra("vipPrice", hotList.get(position).getGoodsvipprice());
                                intent.putExtra("nomalPrice", hotList.get(position).getGoodsprice());
                                intent.putExtra("goodsId",hotList.get(position).getGoodsid());
                                startActivity(intent);
                            }
                        });
                    }

                }
            }

            @Override
            public void onError(com.squareup.okhttp.Response response, int code, Exception e) {

            }
        });
    }

    //    private void setLinearLayoutManager(RecyclerView recyclerView){
//        linearLayoutManager = new LinearLayoutManager(getActivity());
//        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//        recyclerView.setLayoutManager(linearLayoutManager);
//    }
    private void setFlBanner() {
        List<Integer> list = new ArrayList<>();
        list.add(R.drawable.img1);
        list.add(R.drawable.img2);
        flyHomeImglunbo.setImages(list);
    }
}

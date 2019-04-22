package com.example.administrator.realaxf.ui.Base.Home.Fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import com.aitsuki.swipe.SwipeMenuRecyclerView;
import com.example.administrator.realaxf.R;
import com.example.administrator.realaxf.ui.Base.Adapter.GetShopCarListAdpater;
import com.example.administrator.realaxf.ui.Base.Base.BaseFragment;
import com.example.administrator.realaxf.ui.Base.Home.Bean.DeleteShopBean;
import com.example.administrator.realaxf.ui.Base.Home.Bean.GetShopCarListBean;
import com.example.administrator.realaxf.ui.Base.Home.SettlementActivity;
import com.example.administrator.realaxf.ui.Base.Home.order.OrderActivity;
import com.example.administrator.realaxf.ui.Base.Util.SharePrefUtil;
import com.example.administrator.realaxf.ui.Base.okhttputils.OkHttpHelper;
import com.example.administrator.realaxf.ui.Base.okhttputils.SpotsCallBack;
import com.google.gson.Gson;
import com.squareup.okhttp.Response;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;


public class ShopCarFragment extends BaseFragment implements GetShopCarListAdpater.ChackListener,GetShopCarListAdpater.DeleteListener,GetShopCarListAdpater.JiaListener,GetShopCarListAdpater.JianListener {


    /**
     * 在前面商品添加成功后，
     * 通过接口请求，获得购物车商品列表
     * 将获得的数据与SwipeItemLayout相适配
     */
    //得到Okhttp
    OkHttpHelper okHttpHelper = OkHttpHelper.getInstance();
    //得到购物车商品列表控件
    @BindView(R.id.shop_scroll)
    ScrollView shopScroll;
    @BindView(R.id.shop_rela)
    RelativeLayout shopRela;
    @BindView(R.id.swip_car_shoplist)
    SwipeMenuRecyclerView swipCarShoplist;
    @BindView(R.id.shop_totalprices)
    TextView shopTotalprices;
    @BindView(R.id.shop_ok)
    TextView shopok;
    //得到全选框
    @BindView(R.id.check_car_all)
    CheckBox checkCarAll;
    //使用List存放购物车商品信息
    List<GetShopCarListBean.DataBean> list = new ArrayList<>();
    //声明布局管理
    LinearLayoutManager linearLayoutManager1;
    //适配器
    private GetShopCarListAdpater getShopCarListAdpater;
    List<Map<String, Object>> listjson;
    String jsonStr = null;
    Gson gson;
    Boolean isAllchack=false;
    Map map;

    @Override
    public void init() {
        linearLayoutManager1 = new LinearLayoutManager(getActivity());
        linearLayoutManager1.setOrientation(LinearLayoutManager.VERTICAL);

        getShopCarListData(true);

        getShopCarListAdpater = new GetShopCarListAdpater(list);
        getShopCarListAdpater.setOnChackClickListener(this);
        getShopCarListAdpater.setOnDeleteListener(this);
        getShopCarListAdpater.setJiaListener(this);
        getShopCarListAdpater.setJianListener(this);
        swipCarShoplist.setAdapter(getShopCarListAdpater);

    }
    //控制Fragment使他再打开时走这个方法
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        getShopCarListData(false);
        sum=0;
        checkCarAll.setChecked(false);
        shopTotalprices.setText(""+sum);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_car;
    }

    //请求购物车数据
    private void getShopCarListData(boolean b) {
        String url = "http://questionnaire.dzqcedu.com:81/Shop/cartlist";
        String userId=SharePrefUtil.getString(getActivity(),"UserId","2");
        Map<String, String> map = new HashMap<>();
        map.put("userId", userId);
        okHttpHelper.post(url, map, new SpotsCallBack<GetShopCarListBean>(getActivity(),b) {
            @Override
            public void onSuccess(Response response, GetShopCarListBean getShopCarListBean) throws ParseException {
                if (getShopCarListBean.getData().size()>0) {
                    list.clear();
                    shopRela.setVisibility(View.GONE);
                    shopScroll.setVisibility(View.VISIBLE);
                    list.addAll(getShopCarListBean.getData());
                    getShopCarListAdpater.notifyDataSetChanged();
                    swipCarShoplist.setLayoutManager(linearLayoutManager1);
                    swipCarShoplist.setNestedScrollingEnabled(false);
                    swipCarShoplist.setAdapter(getShopCarListAdpater);
                }else {
                    shopRela.setVisibility(View.GONE);
                    shopScroll.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onError(Response response, int code, Exception e) {

            }
        });
    }

    @OnClick({R.id.check_car_all,R.id.shop_ok})
    void onclick(View view){
        switch (view.getId()){
            case R.id.check_car_all:
                isAllchack=!isAllchack;
                setCheckCarAll();
                break;
            case R.id.shop_ok:
                selectGoodsJson();
                if (listjson.size()>0) {
//                    Intent intent = new Intent(getActivity(), OrderActivity.class);
//                    intent.putExtra("jsonStr", jsonStr);

                    Intent intent1 = new Intent(getActivity(), SettlementActivity.class);
                    intent1.putExtra("jsonStr", jsonStr);
                    startActivity(intent1);
                }else {
                    Toast.makeText(getActivity(), "请选择商品", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    //判断是否全选
    private void isAllSeleclt() {
        for (int i = 0; i < list.size(); i++) {
            //遍历判断是否被选中
            if (!list.get(i).isCheck()) {
                isAllchack = false;
                checkCarAll.setChecked(false);
                return;
            }
        }
        isAllchack = true;
        checkCarAll.setChecked(true);
    }
    //点击全选商品全选
    private void setCheckCarAll(){
        for (int i = 0; i <list.size(); i++) {
            if (isAllchack){
                list.get(i).setCheck(true);
                allsum();
            }else {
                list.get(i).setCheck(false);
                allsum();
            }
        }
        getShopCarListAdpater.notifyDataSetChanged();
    }
    //点击全选计算总价
    private void allsum(){
        if(isAllchack){
            sum=0;
            for (int i = 0; i < list.size(); i++) {
                //是判断是不是全部是ture
                int price = Integer.parseInt(list.get(i).getVipprice());
                int number = Integer.parseInt(list.get(i).getCommoditynumber());
                int totalprice = price * number;
                sum +=totalprice;
            }
            shopTotalprices.setText(""+sum);
        }else if (!isAllchack){
            sum=0;
            shopTotalprices.setText(""+sum);
        }

    }

    //调用接口请求删除
    private void getDeleteShopListData(String commodityId){
        String url="http://questionnaire.dzqcedu.com:81/Shop/delcart";
        String userId=SharePrefUtil.getString(getActivity(),"UserId","2");
        Map<String,String> map=new HashMap<>();
        map.put("userId",userId);
        map.put("commodityId",commodityId);
        okHttpHelper.post(url, map, new SpotsCallBack<DeleteShopBean>(getActivity(),false) {
            @Override
            public void onSuccess(Response response, DeleteShopBean deleteShopBean) throws ParseException {
                if (deleteShopBean.getResult()==1){
                    getShopCarListData(true);
                    checkCarAll.setChecked(false);
                    sum=0;
                    shopTotalprices.setText(sum+"");
                    getShopCarListAdpater.notifyDataSetChanged();
                }else {
                    getShopCarListData(true);
                    checkCarAll.setChecked(false);
                    getShopCarListAdpater.notifyDataSetChanged();
                }
            }

            @Override
            public void onError(Response response, int code, Exception e) {

            }
        });
    }
    //单选回调
    @Override
    public void onChackClick(int i) {
        list.get(i).setCheck(!list.get(i).isCheck());
        isAllSeleclt();
        int price = Integer.parseInt(list.get(i).getVipprice());
        int number = Integer.parseInt(list.get(i).getCommoditynumber());
        totalprice = price * number;
        danxuan(i);
        getShopCarListAdpater.notifyDataSetChanged();
    }
    //单选计算总价
    private void danxuan(int i){
        if (list.get(i).isCheck()){
            sum+=totalprice;
            shopTotalprices.setText(""+sum);
        }else {
            sum-=totalprice;
            shopTotalprices.setText(sum+"");
        }
    }
    @Override
    public void onDeleteGoods(int i) {
        getDeleteShopListData(list.get(i).getCommodityid());
    }
    //加号回调
    int sum=0;
    int totalprice;
    @Override
    public void onJiaListener(int i) {
        String idnum=list.get(i).getCommoditynumber();
        int sum= Integer.parseInt(idnum);
        sum+=1;
        list.get(i).setCommoditynumber(sum+"");
        jiahaoPrice(i);
        getShopCarListAdpater.notifyDataSetChanged();
    }
    //加号总价
    private void jiahaoPrice(int i){
        int nubPrice= Integer.parseInt(list.get(i).getVipprice());
        if ( list.get(i).isCheck()){
            totalprice=nubPrice;
            sum+=totalprice;
            shopTotalprices.setText(sum+"");
        }
        getShopCarListAdpater.notifyDataSetChanged();
    }
    //减号回调
    @Override
    public void onJianListener(int i) {
        String idnum=list.get(i).getCommoditynumber();
        int sum= Integer.parseInt(idnum);
        if (sum>1){
            sum-=1;
            list.get(i).setCommoditynumber(sum+"");
            jianhaoPrice(i);
        }else {
            Toast.makeText(getActivity(), "商品数量不能小于1", Toast.LENGTH_SHORT).show();
        }
        getShopCarListAdpater.notifyDataSetChanged();
    }
    //减号总价
    private void jianhaoPrice(int i){
        int nubPrice= Integer.parseInt(list.get(i).getVipprice());
        if ( list.get(i).isCheck()){
            totalprice=nubPrice;
            sum-=totalprice;
            shopTotalprices.setText(sum+"");
        }
        getShopCarListAdpater.notifyDataSetChanged();
    }


    private void selectGoodsJson(){
        listjson = new ArrayList();
        for (int i = 0; i <list.size() ; i++) {
            if (list.get(i).isCheck()==true) {
                //[{"goodsPrice":"4","goodsId":"70","goodsNumber":"2","goodsSmallImg":"http://questionnaire.dzqcedu.com:81/upload/2018-12-25/5c21a8ed6357c.jpg"}]
                map = new HashMap<>();
                map.put("goodsSmallImg",list.get(i).getSmallcommodityimage());
                map.put("goodsName",list.get(i).getCommoditytitle());
                map.put("goodsId", list.get(i).getCommodityid());
                map.put("goodsPrice", list.get(i).getVipprice());
                map.put("goodsNumber", list.get(i).getCommoditynumber());
                listjson.add(map);
                gson = new Gson();
                jsonStr = gson.toJson(listjson);

            }else {
                map = new HashMap<>();
                map.put("goodsName",list.get(i).getCommoditytitle());
                map.put("goodsId", list.get(i).getCommodityid());
                map.put("goodsPrice", list.get(i).getVipprice());
                map.put("goodsNumber", list.get(i).getCommoditynumber());
                map.put("goodsSmallImg",list.get(i).getSmallcommodityimage());
                listjson.remove(map);
                gson = new Gson();
                jsonStr = gson.toJson(listjson);
            }
        }
    }
}
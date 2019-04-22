package com.example.administrator.realaxf.ui.Base.Adapter;

import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.realaxf.R;
import com.example.administrator.realaxf.ui.Base.Home.Bean.VerifyOrderBean;

import java.util.List;

public class VerifyOrderAdapter extends BaseQuickAdapter<VerifyOrderBean.DataBean, BaseViewHolder> {


    public VerifyOrderAdapter(List<VerifyOrderBean.DataBean> data) {
        super(R.layout.item_verifyorder, data);


    }

    @Override
    protected void convert(final BaseViewHolder helper, final VerifyOrderBean.DataBean item) {
        Glide.with(mContext).load(item.getGoodsSmallImg()).into((ImageView)helper.getView(R.id.verify_order));
        helper.setText(R.id.verift_tv,item.getGoodsName()+"" );

    }

}

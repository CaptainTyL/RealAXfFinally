package com.example.administrator.realaxf.ui.Base.Adapter;


import android.view.View;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.administrator.realaxf.R;
import com.example.administrator.realaxf.ui.Base.Home.Bean.GetShopCarListBean;
import com.github.library.BaseViewHolder;
import java.util.List;


public class GetShopCarListAdpater extends BaseQuickAdapter<GetShopCarListBean.DataBean, com.chad.library.adapter.base.BaseViewHolder> {

    private ChackListener listener;
    private DeleteListener deleteListener;
    private JiaListener jiaListener;
    private JianListener jianListener;

    public GetShopCarListAdpater(List<GetShopCarListBean.DataBean> data) {
        super(R.layout.item_shop, data);

    }

    @Override
    protected void convert(final com.chad.library.adapter.base.BaseViewHolder helper,final GetShopCarListBean.DataBean item) {
        Glide.with(mContext).load(item.getSmallcommodityimage()).into((ImageView) helper.getView(R.id.img_shop));
        helper.setChecked(R.id.checkbox_shop, item.isCheck());
        helper.setText(R.id.tv_item_shop_name, item.getCommoditytitle());
        helper.setText(R.id.tv_item_shop_number, item.getCommoditynumber() + "");
        helper.setText(R.id.tv_item_shop_price, item.getVipprice());


        //单选
        helper.setOnClickListener(R.id.checkbox_shop, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onChackClick(helper.getPosition());
            }
        });
        //删除按钮
        helper.setOnClickListener(R.id.right_menu, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteListener.onDeleteGoods(helper.getPosition());
            }
        });
        //加号按钮
        helper.setOnClickListener(R.id.tv_jia, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jiaListener.onJiaListener(helper.getPosition());
            }
        });
        //减号按钮
        helper.setOnClickListener(R.id.tv_jian, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jianListener.onJianListener(helper.getPosition());
            }
        });
    }


//    @Override
//    protected void convert(final BaseViewHolder helper, GetShopCarListBean.DataBean item) {
//
//    }


    public  interface  JianListener{
        void  onJianListener(int i);
    }
    public interface ChackListener {
        void onChackClick(int i);
    }
    public interface DeleteListener {
        void onDeleteGoods(int i);
    }
    public interface JiaListener {
        void onJiaListener(int i);
    }
    public void setOnChackClickListener(ChackListener mListener) {
        this.listener = mListener;
    }
    public void setJiaListener(JiaListener jiaListener) {
        this.jiaListener=jiaListener;
    }

    public void setJianListener(JianListener jianListener) {
        this.jianListener = jianListener;
    }

    public void setOnDeleteListener(DeleteListener deleteListener) {
        this.deleteListener = deleteListener;
    }
}

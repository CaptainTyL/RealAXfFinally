package com.example.administrator.realaxf.ui.Base.okhttputils;

import android.content.Context;
import android.graphics.Color;

import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.zyao89.view.zloading.ZLoadingDialog;

import static com.zyao89.view.zloading.Z_TYPE.CIRCLE;
import static com.zyao89.view.zloading.Z_TYPE.DOUBLE_CIRCLE;
import static com.zyao89.view.zloading.Z_TYPE.PAC_MAN;
import static com.zyao89.view.zloading.Z_TYPE.STAR_LOADING;

public abstract class SpotsCallBack<T> extends BaseCallback<T> {


    private Context mContext;
    private ZLoadingDialog dialog;
    private boolean isLoding;

    public SpotsCallBack(Context context, boolean bool) {
        mContext = context;
        isLoding = bool;
        if (isLoding == true) {
            dialog = new ZLoadingDialog(context);
            initSpotsDialog(true);
        } else {
            return;
        }

    }


    private void initSpotsDialog(final boolean isShowDialog) {
        dialog.setLoadingBuilder(DOUBLE_CIRCLE)//设置类型
                .setLoadingColor(Color.BLACK)//颜色
                .setHintText("正在加载...")
                .show();
    }

    public void showDialog() {
        if (isLoding==true){
            dialog.show();
        }

    }

    public void dismissDialog() {
        if (isLoding==true){
            dialog.dismiss();
        }

    }


    @Override
    public void onFailure(Request request, Exception e) {
        if (isLoding==true){
            dismissDialog();
        }

    }

    @Override
    public void onBeforeRequest(Request request) {
        if (isLoding==true){
            showDialog();
        }

    }

    @Override
    public void onResponse(Response response) {
        if (isLoding==true){
            dismissDialog();
        }

    }
}

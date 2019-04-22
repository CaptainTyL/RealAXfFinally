package com.example.administrator.realaxf.ui.Base.Home.Fragment;

import android.content.Intent;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.administrator.realaxf.R;

import com.example.administrator.realaxf.ui.Base.Base.BaseFragment;
import com.example.administrator.realaxf.ui.Base.Home.Fragment.MinePager.Commodit;
import com.example.administrator.realaxf.ui.Base.Home.Fragment.MinePager.CouponsClass;
import com.example.administrator.realaxf.ui.Base.Home.Fragment.MinePager.DianPuShouChang;
import com.example.administrator.realaxf.ui.Base.Home.Fragment.MinePager.DuiHuanClass;
import com.example.administrator.realaxf.ui.Base.Home.Fragment.MinePager.GenRenactivity;
import com.example.administrator.realaxf.ui.Base.Home.Fragment.MinePager.GuanLiShouHuoDiZhi;
import com.example.administrator.realaxf.ui.Base.Home.Fragment.MinePager.HuiYuanJiFen;
import com.example.administrator.realaxf.ui.Base.Home.Fragment.MinePager.InformationClass;
import com.example.administrator.realaxf.ui.Base.Home.Fragment.MinePager.KeFuFanKui;
import com.example.administrator.realaxf.ui.Base.Home.Fragment.MinePager.SetUpTheClass;
import com.example.administrator.realaxf.ui.Base.Home.Fragment.MinePager.ShangPinShouChang;
import com.example.administrator.realaxf.ui.Base.Home.Fragment.MinePager.VoucherClass;
import com.example.administrator.realaxf.ui.Base.Home.Fragment.MinePager.WoDePingJia;
import com.example.administrator.realaxf.ui.Base.Home.Fragment.MinePager.YuErClass;
import com.example.administrator.realaxf.ui.Base.Home.order.OrderActivity;
import com.example.administrator.realaxf.ui.Base.Util.GlideRoundTransform;
import com.example.administrator.realaxf.ui.Base.Util.SelectPicPopupWindow;
import com.example.administrator.realaxf.ui.Base.Util.SharePrefUtil;
import com.recker.flybanner.FlyBanner;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static android.app.Activity.RESULT_CANCELED;

public class MineFragment extends BaseFragment {
    @BindView(R.id.img_shezhi)
    ImageView imgSheZhi;
    @BindView(R.id.img_xinxi)
    ImageView imgXinxi;
    @BindView(R.id.tv_mine_checkList)
    TextView tvMineCheckList;
    @BindView(R.id.re_mine_pay)
    RelativeLayout reMinePay;
    @BindView(R.id.re_mine_recycive)
    RelativeLayout reMineRecycive;
    @BindView(R.id.re_mine_talk)
    RelativeLayout reMineTalk;
    @BindView(R.id.re_mine_saleAfter)
    RelativeLayout reMineSaleAfter;
    @BindView(R.id.radio_yuer)
    RelativeLayout radioYuer;
    @BindView(R.id.radio_duihuanjuan)
    RelativeLayout radioDuihuanjuan;
    @BindView(R.id.radio_youhuijuan)
    RelativeLayout radioYouhuijuan;
    @BindView(R.id.radio_jifen)
    RelativeLayout radioJifen;
    @BindView(R.id.radio_shangpinshouchang)
    RelativeLayout radioShangpinshouchang;
    @BindView(R.id.radio_dianpushouchang)
    RelativeLayout radiDianpushouchang;
    @BindView(R.id.radio_kefufankui)
    RelativeLayout radioKefufankui;
    @BindView(R.id.radio_wodepingjia)
    RelativeLayout radioWodepingjia;
    @BindView(R.id.radio_shouhuodizhi)
    RelativeLayout radioShouhuodizhi;
    @BindView(R.id.text_shangping)
    TextView textShangping;
    @BindView(R.id.text_jifenduihuan)
    TextView textJifenduihuan;

    @BindView(R.id.tv_mine_userName)
    TextView tvMineUserName;
    //用户头像
    @BindView(R.id.girl)
    ImageView imagHead;
    @BindView(R.id.fly_banner)
    FlyBanner flyBanner;
    Intent intent1;
    private Uri uri;
    private SelectPicPopupWindow menuWindow;
    private static final String IMAGE_FILE_NAME = "temp_head_image.jpg";
    private static final int CODE_GALLERY_REQUEST = 0xa0;
    private static final int CODE_CAMERA_REQUEST = 0xa1;
    private static final int CODE_RESULT_REQUEST = 0xa2;

    private static int output_X = 50;
    private static int output_Y = 50;
    private ImageView img =null;
    @Override
    public void init() {
        setFlBanner();
        String userIcon=SharePrefUtil.getString(getActivity(),"UserIcon","1");
        RequestOptions myOptions = new RequestOptions()
                .centerCrop()
                .transform(new GlideRoundTransform(getActivity(),30));
        Glide.with(getActivity()).load(userIcon).apply(myOptions).into(imagHead);
        String userName=SharePrefUtil.getString(getActivity(),"UserName","3");

        tvMineUserName.setText(userName);

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @OnClick({R.id.tv_mine_checkList,R.id.girl, R.id.re_mine_pay, R.id.re_mine_recycive, R.id.re_mine_talk, R.id.re_mine_saleAfter,R.id.img_shezhi,R.id.img_xinxi,R.id.radio_yuer,R.id.radio_duihuanjuan,R.id.radio_youhuijuan,R.id.radio_jifen,R.id.text_shangping,R.id.text_jifenduihuan,R.id.radio_shangpinshouchang,R.id.radio_dianpushouchang,R.id.radio_kefufankui,R.id.radio_wodepingjia,R.id.tv_mine_userName,R.id.radio_shouhuodizhi})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_mine_checkList:
                setIntent("all");
                break;
            case R.id.re_mine_pay:
                setIntent("pay");
                break;
            case R.id.re_mine_recycive:
                setIntent("recycive");
                break;
            case R.id.re_mine_talk:
                setIntent("talk");
                break;
            case R.id.re_mine_saleAfter:
                setIntent("saleAfter");
                break;
            case R.id.img_shezhi:
                Intent intent=new Intent(getActivity(),SetUpTheClass.class);
                startActivity(intent);
                break;
            case R.id.radio_jifen:
                Intent intent1=new Intent(getActivity(),HuiYuanJiFen.class);
                startActivity(intent1);
                break;
            case R.id.radio_yuer:
                Intent intent2=new Intent(getActivity(),YuErClass.class);
                startActivity(intent2);
                break;
            case R.id.radio_duihuanjuan:
                Intent intent3=new Intent(getActivity(),VoucherClass.class);
                startActivity(intent3);
                break;
            case R.id.radio_youhuijuan:
                Intent intent4=new Intent(getActivity(),CouponsClass.class);
                startActivity(intent4);
                break;
            case R.id.text_shangping:
                Intent intent5=new Intent(getActivity(),Commodit.class);
                startActivity(intent5);
                break;
            case R.id.text_jifenduihuan:
                Intent intent6=new Intent(getActivity(),DuiHuanClass.class);
                startActivity(intent6);
                break;
            case R.id.img_xinxi:
                Intent intent7=new Intent(getActivity(),InformationClass.class);
                startActivity(intent7);
                break;
            case R.id.radio_shangpinshouchang:
                Intent intent8=new Intent(getActivity(),ShangPinShouChang.class);
                startActivity(intent8);
                break;
            case R.id.radio_dianpushouchang:
                Intent intent9=new Intent(getActivity(),DianPuShouChang.class);
                startActivity(intent9);
                break;
            case R.id.radio_kefufankui:
                Intent intent10=new Intent(getActivity(),KeFuFanKui.class);
                startActivity(intent10);
                break;
            case R.id.radio_wodepingjia:
                Intent intent11=new Intent(getActivity(),WoDePingJia.class);
                startActivity(intent11);
                break;
            case R.id.tv_mine_userName:
                Intent intent12=new Intent(getActivity(),GenRenactivity.class);
                startActivity(intent12);
                break;
            case R.id.radio_shouhuodizhi:
                Intent intent13=new Intent(getActivity(),GuanLiShouHuoDiZhi.class);
                startActivity(intent13);
                break;
            case R.id.girl:
                menuWindow = new SelectPicPopupWindow(getActivity(), itemsOnClick);
                //显示窗口
                //设置layout在PopupWindow中显示的位置
                menuWindow.showAtLocation(view.findViewById(R.id.girl), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                break;
        }
    }

    private void setIntent(String str) {
        Intent intent = new Intent(getActivity(), OrderActivity.class);
        intent.putExtra("type", str);
        startActivity(intent);
    }
    // 从本地相册选取图片作为头像
    private void choseHeadImageFromGallery() {
        Intent intentFromGallery = new Intent();
        // 设置文件类型
        intentFromGallery.setType("image/*");
        intentFromGallery.setAction(Intent.ACTION_PICK);
        startActivityForResult(intentFromGallery, CODE_GALLERY_REQUEST);
    }

    // 启动手机相机拍摄照片作为头像
    private void choseHeadImageFromCameraCapture() {
        Intent intentFromCapture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        // 判断存储卡是否可用，存储照片文件
        if (hasSdcard()) {
            intentFromCapture.putExtra(MediaStore.EXTRA_OUTPUT, Uri
                    .fromFile(new File(Environment
                            .getExternalStorageDirectory(), IMAGE_FILE_NAME)));
        }

        startActivityForResult(intentFromCapture, CODE_CAMERA_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode,
                                 Intent intent) {

        // 用户没有进行有效的设置操作，返回
        if (resultCode == RESULT_CANCELED) {
            Toast.makeText(getActivity(), "取消", Toast.LENGTH_LONG).show();
            return;
        }

        switch (requestCode) {
            case CODE_GALLERY_REQUEST:
                cropRawPhoto(intent.getData());
                break;

            case CODE_CAMERA_REQUEST:
                if (hasSdcard()) {
                    File tempFile = new File(
                            Environment.getExternalStorageDirectory(),
                            IMAGE_FILE_NAME);
                    cropRawPhoto(Uri.fromFile(tempFile));
                } else {
                    Toast.makeText(getActivity(), "没有SDCard!", Toast.LENGTH_LONG)
                            .show();
                }

                break;

            case CODE_RESULT_REQUEST:
                if (intent != null) {
                    setImageToHeadView(intent);
                }

                break;
        }

        super.onActivityResult(requestCode, resultCode, intent);
    }

    /**
     * 裁剪原始的图片
     */
    public void cropRawPhoto(Uri uri) {

        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");

        // 设置裁剪
        intent.putExtra("crop", "true");

        // aspectX , aspectY :宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);

        // outputX , outputY : 裁剪图片宽高
        intent.putExtra("outputX", output_X);
        intent.putExtra("outputY", output_Y);
        intent.putExtra("return-data", true);

        startActivityForResult(intent, CODE_RESULT_REQUEST);
    }

    /**
     * 提取保存裁剪之后的图片数据，并设置头像部分的View
     */
    private void setImageToHeadView(Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras != null) {
            Bitmap photo = extras.getParcelable("data");
            imagHead.setImageBitmap(photo);



        }
    }




    /**
     * 检查设备是否存在SDCard的工具方法
     */
    public static boolean hasSdcard() {
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            // 有存储的SDCard
            return true;
        } else {
            return false;
        }
    }

    private View.OnClickListener itemsOnClick = new View.OnClickListener() {

        public void onClick(View v) {
            menuWindow.dismiss();
            switch (v.getId()) {
                //通过拍照获取照片
                case R.id.btn_take_photo:
                    choseHeadImageFromCameraCapture();



                    break;
                //从相册获取照片
                case R.id.btn_pick_photo:
                    choseHeadImageFromGallery();

                    break;
                default:
                    break;
            }
        }
    };
    private void setFlBanner() {
        List<Integer> list = new ArrayList<>();
        list.add(R.drawable.img1);
        list.add(R.drawable.img2);
        flyBanner.setImages(list);
    }
}

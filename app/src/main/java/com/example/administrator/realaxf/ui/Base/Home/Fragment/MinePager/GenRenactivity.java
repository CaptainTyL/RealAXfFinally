package com.example.administrator.realaxf.ui.Base.Home.Fragment.MinePager;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;


import com.example.administrator.realaxf.R;
import com.example.administrator.realaxf.ui.Base.Base.BaseActivity;
import com.example.administrator.realaxf.ui.Base.Home.Fragment.MineFragment;
import com.example.administrator.realaxf.ui.Base.Util.GlideRoundTransform;
import com.example.administrator.realaxf.ui.Base.Util.SelectPicPopupWindow;
import com.example.administrator.realaxf.ui.Base.Util.SharePrefUtil;


import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;

public class GenRenactivity extends BaseActivity {
    @BindView(R.id.SEX_tv)
    TextView SEXTv;
    @BindView(R.id.age_tv)
    TextView ageTv;
    @BindView(R.id.sex_re)
    RelativeLayout sexRe;
    @BindView(R.id.nicheng_re)
    RelativeLayout nicehngRe;
    @BindView(R.id.age_re)
    RelativeLayout ageRe;
    @BindView(R.id.touxiang_img)
    ImageView touxiangImg;
    @BindView(R.id.touxiang_re)
    RelativeLayout touxiangRe;
    @BindView(R.id.Nicheng_tv)
    TextView NIcehngTv;
    @BindView(R.id.img_zuofanhui)
    ImageView next;
    private String[] sexArry = new String[]{"不告诉你", "女", "男"};// 性别选择
    private SelectPicPopupWindow menuWindow;
    private static final String IMAGE_FILE_NAME = "temp_head_image.jpg";
    private static final int CODE_GALLERY_REQUEST = 0xa0;
    private static final int CODE_CAMERA_REQUEST = 0xa1;
    private static final int CODE_RESULT_REQUEST = 0xa2;

    private static int output_X = 50;
    private static int output_Y = 50;
    private ImageView img;
    private int RR=1;

    @Override
    public void init() {

        String userIcon=SharePrefUtil.getString(GenRenactivity.this,"UserIcon","1");
        String userName=SharePrefUtil.getString(GenRenactivity.this,"UserName","0");
        RequestOptions myOptions = new RequestOptions()
                .centerCrop()
                .transform(new GlideRoundTransform(GenRenactivity.this,30));
        Glide.with(GenRenactivity.this).load(userIcon).apply(myOptions).into(touxiangImg);
        NIcehngTv.setText(userName);


    }
    private void showSexChooseDialog() {
        AlertDialog.Builder builder3 = new AlertDialog.Builder(this);// 自定义对话框
        builder3.setSingleChoiceItems(sexArry, 0, new DialogInterface.OnClickListener() {// 2默认的选中

            @Override
            public void onClick(DialogInterface dialog, int which) {// which是被选中的位置
                // showToast(which+"");
                SEXTv.setText(sexArry[which]);
                dialog.dismiss();// 随便点击一个item消失对话框，不用点击确认取消
            }
        });
        builder3.show();// 让弹出框显示
    }
    @OnClick({R.id.sex_re,R.id.nicheng_re,R.id.age_re,R.id.touxiang_re,R.id.img_zuofanhui})
    void onclick(View v){
        switch (v.getId()){
            case R.id.sex_re:
                showSexChooseDialog();

                break;
            case R.id.nicheng_re:
                onCreateNameDialog();


                break;
            case R.id.age_re:
                onCreateNameDialog1();
                break;
            case R.id.touxiang_re:
                TouXiang();



                break;
            case R.id.img_zuofanhui:
//                Intent intent=new Intent(GenRenactivity.this,HomeActivity.class);
//                intent.putExtra("touxiang", String.valueOf(touxiangImg));
//               String n= NIcehngTv.getText().toString();
//                intent.putExtra("nicheng",n);
//                intent.putExtra("biaoshi",1);
//                startActivity(intent);
//                SharePrefUtil.saveInt(GenRenactivity.this,"a",3);

//Mess mess=new Mess();
//mess.setBS("1");
finish();
//                EventBus.getDefault().post(mess);
                break;

        }
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_ge_ren_zi_liao;
    }
    private void onCreateNameDialog() {
        // 使用LayoutInflater来加载dialog_setname.xml布局
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View nameView = layoutInflater.inflate(R.layout.dialog_setname, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);

        // 使用setView()方法将布局显示到dialog
        alertDialogBuilder.setView(nameView);

        final EditText userInput = (EditText) nameView.findViewById(R.id.changename_edit);
        final TextView name = (TextView) findViewById(R.id.Nicheng_tv);


        // 设置Dialog按钮
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // 获取edittext的内容,显示到textview
                                name.setText(userInput.getText());
                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
    }
    private void onCreateNameDialog1() {
        // 使用LayoutInflater来加载dialog_setname.xml布局
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View nameView = layoutInflater.inflate(R.layout.dialog_setname1, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);

        // 使用setView()方法将布局显示到dialog
        alertDialogBuilder.setView(nameView);

        final EditText userInput = (EditText) nameView.findViewById(R.id.changename_edit1);
        final TextView name = (TextView) findViewById(R.id.age_tv);


        // 设置Dialog按钮
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // 获取edittext的内容,显示到textview
                                name.setText(userInput.getText());
                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
    }
    private void TouXiang(){
        MineFragment mineFragment;
        menuWindow = new SelectPicPopupWindow(GenRenactivity.this, itemsOnClick);
        //显示窗口
        //设置layout在PopupWindow中显示的位置
        menuWindow.showAtLocation(touxiangImg.findViewById(R.id.touxiang_img), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
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
            Toast.makeText(GenRenactivity.this, "取消", Toast.LENGTH_LONG).show();
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
                    Toast.makeText(GenRenactivity.this, "没有SDCard!", Toast.LENGTH_LONG)
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
            touxiangImg.setImageBitmap(photo);



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
}

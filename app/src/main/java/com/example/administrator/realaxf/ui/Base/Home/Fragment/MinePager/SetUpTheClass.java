package com.example.administrator.realaxf.ui.Base.Home.Fragment.MinePager;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.realaxf.R;
import com.example.administrator.realaxf.ui.Base.Home.Fragment.MineFragment;

import cn.sharesdk.onekeyshare.OnekeyShare;

public class SetUpTheClass extends AppCompatActivity {
    private ImageView imgZuoFanHui;
    private RelativeLayout relativeLayout, relativeLayout1;
    private TextView tvMinebreak;
private RelativeLayout relaShard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setuptheclass);
        imgZuoFanHui = findViewById(R.id.img_zuofanhui);
        relaShard=findViewById(R.id.rela_shard);
        imgZuoFanHui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        relativeLayout = findViewById(R.id.radio_jianchagenxin);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SetUpTheClass.this, "当前是最新版本", Toast.LENGTH_SHORT).show();
            }
        });
        relativeLayout1 = findViewById(R.id.radio_guanyuwomen);
        relativeLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent10 = new Intent(SetUpTheClass.this, GuanYuWoMen.class);
                startActivity(intent10);
            }
        });

        tvMinebreak = findViewById(R.id.tv_mine_break);
        tvMinebreak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(SetUpTheClass.this);
                dialog.setTitle("退出");
                dialog.setMessage("退出应用程序？");
                dialog.setCancelable(false);
                dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent MyIntent = new Intent(Intent.ACTION_MAIN);
                        MyIntent.addCategory(Intent.CATEGORY_HOME);
                        startActivity(MyIntent);
                        finish();
                    }
                });
                dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                dialog.show();
            }
        });
        relaShard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showShare();
            }
        });

    }
    private void showShare(){
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
}

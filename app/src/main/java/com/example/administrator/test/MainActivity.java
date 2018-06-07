package com.example.administrator.test;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView number;
    private RelativeLayout splash;

    AlphaAnimation animation;
    MyCount my;

    ShimmerTextView tv;
    Shimmer shimmer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Window window = getWindow();
        //隐藏标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //隐藏状态栏
        //定义全屏参数
        int flag = WindowManager.LayoutParams.FLAG_FULLSCREEN;
        //设置当前窗体为全屏显示
        window.setFlags(flag, flag);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        setContentView(R.layout.activity_main);

        number = findViewById(R.id.tv_number);
        splash = findViewById(R.id.rl_splash);
        tv = findViewById(R.id.shimmer_tv);

        if (shimmer != null && shimmer.isAnimating()) {
            shimmer.cancel();
        } else {
            shimmer = new Shimmer();
            shimmer.start(tv);
        }

        number.setOnClickListener(this);
        countDown();
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(MainActivity.this, Main2Activity.class);
        startActivity(intent);
        my.cancel();
        finish();
    }


    //-------------------设置的倒计时时间-------------------------------------------
    private void countDown() {
        animation = new AlphaAnimation(0.5f, 1.0f);  //背景透明度
        animation.setDuration(3000); //透明度时间
        my = new MyCount(4000, 1000);
        my.start();
        splash.setAnimation(animation);
    }


    public class MyCount extends CountDownTimer {

        public MyCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            number.setText("跳过" + millisUntilFinished / 1000 + "s");
        }

        @Override
        public void onFinish() {
            Intent it = new Intent(MainActivity.this, Main2Activity.class);
            startActivity(it);
            finish();
        }
    }

    @Override
    public void onBackPressed() {

    }
}

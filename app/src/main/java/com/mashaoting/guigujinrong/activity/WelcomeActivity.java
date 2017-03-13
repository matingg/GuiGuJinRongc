package com.mashaoting.guigujinrong.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mashaoting.guigujinrong.MainActivity;
import com.mashaoting.guigujinrong.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class WelcomeActivity extends AppCompatActivity {

    @InjectView(R.id.tv_welcom)
    TextView tvWelcom;
    @InjectView(R.id.activity_welcome)
    RelativeLayout activityWelcome;
    @InjectView(R.id.iv_welcome)
    ImageView ivWelcome;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ButterKnife.inject(this);
//        Log.i("TAG", "HomeFragment initView()"+1/0);
        Glide.with(this).load(R.drawable.abc).into(ivWelcome);

    }

    CountDownTimer countDownTimer = new CountDownTimer(4000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            int ad = (int) (millisUntilFinished / 1000);

            tvWelcom.setText("倒计时" + ad);

        }

        @Override
        public void onFinish() {
            Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
//            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out); 淡入淡出
            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            finish();
        }
    }.start();

}

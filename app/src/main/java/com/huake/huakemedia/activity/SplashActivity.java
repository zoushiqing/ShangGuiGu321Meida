package com.huake.huakemedia.activity;

/*
 * @创建者     兰昱
 * @创建时间  2016/9/28 11:57
 * @描述	      
 */

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.huake.huakemedia.R;

public class SplashActivity extends AppCompatActivity {

    private Handler mHandler=new Handler();
    private Button mSplash_button;
    private boolean isMainActivity=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mSplash_button = (Button) findViewById(R.id.activity_splash_button);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startMainActivity();
            }
        },2000);

        mSplash_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startMainActivity();
            }
        });
    }

    private void startMainActivity() {
        if (!isMainActivity){
            isMainActivity=true;
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        //不移除handler进去后还会反弹
        mHandler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }
}

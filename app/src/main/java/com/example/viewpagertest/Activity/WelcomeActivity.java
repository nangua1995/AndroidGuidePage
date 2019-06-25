package com.example.viewpagertest.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.viewpagertest.R;

//进入APP主页面之前的欢迎页面

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        boolean isFirstOpen = true;
        if(isFirstOpen){
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
            finish();
            return;
        }else{
            setContentView(R.layout.activity_welcome);
            //handler相当于启一个线程，延时2s以后执行enterRootActivity的函数
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    /*2秒后进入主页*/
                    enterRootActivity();
                }
            },2000);
        }
    }
    private void enterRootActivity(){
        Intent intent = new Intent(this,rootActivity.class);
        startActivity(intent);
        finish();
    }
}

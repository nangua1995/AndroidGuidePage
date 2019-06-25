package com.example.viewpagertest.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.viewpagertest.R;
import com.example.viewpagertest.Adapter.ViewPagerAdapter;

import java.util.ArrayList;

//mainActivity是对于ViewPager使用的
public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private ViewPager viewPager;
    private ArrayList<View> aList;
    private ViewPagerAdapter adpter;
    private Button startBtn;

    private int vp_viewer[] ={R.layout.guid_view1,R.layout.guid_view2,R.layout.guid_view3,
                             R.layout.guid_view4};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = findViewById(R.id.vp);

        aList = new ArrayList<View>();

        for(int i=0;i<vp_viewer.length;i++){
            View view = LayoutInflater.from(this).inflate(vp_viewer[i],null);
            if(i==vp_viewer.length-1){
                startBtn = view.findViewById(R.id.btn_login);
                startBtn.setTag("enter");
                startBtn.setOnClickListener(this);
            }
            aList.add(view);
        }

        adpter = new ViewPagerAdapter(aList);
        viewPager.setAdapter(adpter);
    }

    @Override
    public void onClick(View view) {
        if(view.getTag().equals("enter")){
            Intent intent = new Intent(MainActivity.this, rootActivity.class);
            startActivity(intent);
            return;
        }
    }
}

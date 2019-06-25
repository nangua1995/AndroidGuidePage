package com.example.viewpagertest.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.example.viewpagertest.R;
import com.example.viewpagertest.Adapter.ViewPagerAdapter;

import java.util.ArrayList;
//mainActivity是对于ViewPager使用的
public class MainActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private ArrayList<View> aList;
    private ViewPagerAdapter adpter;

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
            aList.add(view);
        }

        adpter = new ViewPagerAdapter(aList);
        viewPager.setAdapter(adpter);
    }


}

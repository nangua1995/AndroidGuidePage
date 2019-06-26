package com.example.viewpagertest.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

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

    private ImageView[] dots;//底部小点图片
    private int currentIndex;//用于记录当前选中位置

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
                 /*这里使用setTag方法进行标注。在View中的setTag(Onbect)表示给View
                添加一个格外的数据，以后可以用getTag()将这个数据取出来。可以用在
                多个Button添加一个监听器，每个Button都设置不同的setTag。这个监听
                器就通过getTag来分辨是哪个Button 被按下。*/
                startBtn.setOnClickListener(this);
            }
            aList.add(view);
        }

        adpter = new ViewPagerAdapter(aList);
        viewPager.setAdapter(adpter);
        initDots();

        //用于viewPager和Dots配合
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //当前页面被滑动时调用
                // arg0 :当前页面，及你点击滑动的页面
                // arg1:当前页面偏移的百分比
                // arg2:当前页面偏移的像素位置
            }

            @Override
            public void onPageSelected(int position) {
                //当前新的页面被选中时调用
                setCurDot(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                //当滑动状态改变时调用
                /*arg0 ==1的时辰默示正在滑动，arg0==2的时辰默示滑动完毕了，arg0==0的时辰默示什么都没做。*/
            }
        });
    }

    @Override
    public void onClick(View view) {
        //可以把所有控件的onclick事件写在一起
        //setTag,getTag的方法也可以通过：view.getId()==R.id.btn_login的方式来实现
        if(view.getTag().equals("enter")){
            Intent intent = new Intent(MainActivity.this, rootActivity.class);
            startActivity(intent);
            return;
        }
        //TODO：imageView的点击事件存在问题，只能切换到当前imageVIew的时候才能点击
        int pos = (Integer)view.getTag();
       // Toast.makeText(MainActivity.this,"点击了第"+pos+"个按钮",Toast.LENGTH_SHORT);
        setCurrentView(pos);
        setCurDot(pos);
    }

    private void initDots(){
        LinearLayout linearLayout = findViewById(R.id.ll);
        dots = new ImageView[vp_viewer.length];

        for(int i=0;i< vp_viewer.length;i++){
            dots[i] = (ImageView)linearLayout.getChildAt(i);
            dots[i].setOnClickListener(this);
            dots[i].setEnabled(false);
            dots[i].setTag(i);
        }
        currentIndex = 0;
        dots[currentIndex].setEnabled(true);//设置为白色，即选中状态
    }

    //设置当前ViewPager_View
    private void setCurrentView(int pos){
        if(pos<0 || pos>vp_viewer.length){
            return;
        }
        viewPager.setCurrentItem(pos);//通过读取到的TAG号来设置当前的Viewpager的显示
    }

    private void setCurDot(int pos){//通过读取到的viewPager所在页面来设置Dots的状态
        if(pos<0 || pos > vp_viewer.length||currentIndex==pos){return;}
        dots[pos].setEnabled(true);
        dots[currentIndex].setEnabled(false);
        currentIndex = pos;
    }
}

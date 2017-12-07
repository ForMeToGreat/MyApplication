package com.example.administrator.myapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;

import adapter.PagerAdapter;
import fragment.FourFragment;
import fragment.OneFragment;
import fragment.ThreeFragment;
import fragment.TwoFragment;

import static com.example.administrator.myapplication.R.id.four;
import static com.example.administrator.myapplication.R.id.one;
import static com.example.administrator.myapplication.R.id.three;
import static com.example.administrator.myapplication.R.id.two;

public class HomeActivity extends AppCompatActivity {
    private RadioGroup group;
    private ViewPager viewpager;
    private ArrayList<Fragment>fragments;
    private int radios[] = {one, two, three, four};
    private RadioButton rbs[] = new RadioButton[radios.length];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initview();
        groupListener();
        viewpagerListener();
    }

    private void viewpagerListener() {
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i=0;i<rbs.length;i++){
                    if (position==i){
                        rbs[i].setSelected(true);
                        rbs[i].setTextColor(Color.GREEN);
                    }else{
                        rbs[i].setSelected(false);
                        rbs[i].setTextColor(Color.BLACK);
                    }
                }
            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void groupListener() {
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch(checkedId){
                    case R.id.one:
                        changeViewPagerItem(0);
                        break;
                    case R.id.two:
                        changeViewPagerItem(1);
                        break;
                    case R.id.three:
                        changeViewPagerItem(2);
                        break;
                    case R.id.four:
                        changeViewPagerItem(3);
                        break;
                }
            }
        });
    }
    public void changeViewPagerItem(int index){
        viewpager.setCurrentItem(index,false);
    }
    private void initview() {
        viewpager = (ViewPager) findViewById(R.id.viewpager);
        group = (RadioGroup) findViewById(R.id.group);
        for (int i=0;i<radios.length;i++){
            rbs[i] = (RadioButton) findViewById(radios[i]);
        }
        rbs[0].setTextColor(Color.GREEN);
        rbs[0].setSelected(true);
        fragments = new ArrayList<>();
        fragments.add(new OneFragment());
        fragments.add(new TwoFragment());
        fragments.add(new ThreeFragment());
        fragments.add(new FourFragment());
        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(),fragments);
        viewpager.setAdapter(adapter);
    }

    public void setCurrentViewPager() {
        viewpager.setCurrentItem(1);
    }
}

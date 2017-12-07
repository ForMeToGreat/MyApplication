package com.example.demo02;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import java.util.ArrayList;
import fragment.BlankFragment01;
import fragment.BlankFragment02;
import fragment.BlankFragment03;
import fragment.BlankFragment04;

public class MainActivity extends AppCompatActivity {
    private int btnId[] = {R.id.btn1,R.id.btn2,R.id.btn3,R.id.btn4};
    private RadioButton btns[] = new RadioButton[btnId.length];
    private ArrayList<Fragment>fragments = new ArrayList<>();
    private ViewPager viewpager;
    private Myadapter adapter;
    private RadioGroup group;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        changeListener();
    }
    private void initView() {
        viewpager = (ViewPager) findViewById(R.id.viewpager);
        group = (RadioGroup) findViewById(R.id.group);
        for (int i=0;i<btnId.length;i++){
            btns[i] = (RadioButton) findViewById(btnId[i]);
        }
        btns[0].setSelected(true);
        btns[0].setTextColor(Color.GREEN);
        fragments.add(new BlankFragment01());
        fragments.add(new BlankFragment02());
        fragments.add(new BlankFragment03());
        fragments.add(new BlankFragment04());
        adapter = new Myadapter(getSupportFragmentManager(),fragments);
        viewpager.setAdapter(adapter);
    }
    private void changeListener() {
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                for (int i =0;i<btns.length;i++){
                    if (i==position){
                        btns[i].setTextColor(Color.GREEN);
                        btns[i].setSelected(true);
                    }else{
                        btns[i].setTextColor(Color.BLACK);
                        btns[i].setSelected(false);
                    }
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId){
                    case R.id.btn1:
                        viewpagerChanged(0);
                        break;
                    case R.id.btn2:
                        viewpagerChanged(1);
                        break;
                    case R.id.btn3:
                        viewpagerChanged(2);
                        break;
                    case R.id.btn4:
                        viewpagerChanged(3);
                        break;
                }
            }
        });
    }

    private void viewpagerChanged(int index) {
        for (int i =0;i<btns.length;i++){
            if (i==index){
                viewpager.setCurrentItem(i,false);
                btns[i].setTextColor(Color.GREEN);
                btns[i].setSelected(true);
            }else{
                btns[i].setTextColor(Color.BLACK);
                btns[i].setSelected(false);
            }
        }
    }
}

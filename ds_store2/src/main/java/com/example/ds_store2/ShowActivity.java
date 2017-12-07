package com.example.ds_store2;

import android.graphics.Color;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;

import fragment.OneFragment;
import fragment.ThreeFragment;
import fragment.TwoFragment;

public class ShowActivity extends AppCompatActivity {
    private int radioIds[] = {R.id.btn01, R.id.btn02, R.id.btn03};
    private RadioButton[] buttons = new RadioButton[radioIds.length];
    private ArrayList<Fragment> frags;
    private ViewPager pager;
    private RadioGroup group;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        initView();
        groupListener();
        pagerListener();
    }

    private void initView() {
        pager = (ViewPager) findViewById(R.id.pager);
        group = (RadioGroup) findViewById(R.id.group);
        for (int i = 0; i < radioIds.length; i++) {
            buttons[i] = (RadioButton) findViewById(radioIds[i]);
        }
        buttons[0].setSelected(true);
        buttons[0].setTextColor(Color.RED);
        frags = new ArrayList<>();
        frags.add(new OneFragment());
        frags.add(new TwoFragment());
        frags.add(new ThreeFragment());
        PagerAdapter adapter = new adapter.PagerAdapter(getSupportFragmentManager(), frags);
        pager.setAdapter(adapter);

    }

    private void groupListener() {
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.btn01:
                        pager.setCurrentItem(0, false);
                        buttons[0].setSelected(true);
                        buttons[1].setSelected(false);
                        buttons[2].setSelected(false);
                        break;
                    case R.id.btn02:
                        pager.setCurrentItem(1, false);
                        buttons[0].setSelected(false);
                        buttons[1].setSelected(true);
                        buttons[2].setSelected(false);
                        break;
                    case R.id.btn03:
                        pager.setCurrentItem(2, false);
                        buttons[0].setSelected(false);
                        buttons[1].setSelected(false);
                        buttons[2].setSelected(true);
                        break;
                }
            }
        });
    }

    private void pagerListener() {
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < radioIds.length; i++) {
                    if (i == position) {
                        buttons[i].setSelected(true);
                        buttons[i].setTextColor(Color.RED);
                    } else {
                        buttons[i].setSelected(false);
                        buttons[i].setTextColor(Color.BLACK);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}

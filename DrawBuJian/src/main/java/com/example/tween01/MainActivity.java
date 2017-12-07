package com.example.tween01;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img = (ImageView) findViewById(R.id.img1);
    }

    public void translate(View view) {
        //平移动画
        TranslateAnimation animation = new TranslateAnimation(0,780,0,1250);
        //设置动画的时间
        animation.setDuration(3000);
        //设置是否停止在最后的位置
        animation.setFillAfter(true);
        //设置重复轨迹的次数
        animation.setRepeatCount(5);
        //设置重复方式
        //Animation.REVERSE往返来回
        //Animation.RESTART从头开始
        animation.setRepeatMode(Animation.REVERSE);
        //accelerate_interpolator
        //accelerate_decelerate_interpolator
        //accelerate:加速 decelerate:减速 interpolator:篡改者
        animation.setInterpolator(this,android.R.anim.accelerate_interpolator);
        img.startAnimation(animation);
    }

    public void scale(View view) {
        //创建缩放动画
        ScaleAnimation animation = new ScaleAnimation(0,2,0,2);
        animation.setRepeatCount(2);
        animation.setRepeatMode(Animation.REVERSE);
        animation.setDuration(1000);
        animation.setFillAfter(true);
        img.startAnimation(animation);
    }

    public void alpha(View view) {
        //透明
        AlphaAnimation animation = new AlphaAnimation(0,1);
        animation.setDuration(1500);
        animation.setRepeatCount(10);
        animation.setRepeatMode(Animation.REVERSE);
        animation.setFillAfter(true);
        img.startAnimation(animation);
    }

    public void rotate(View view) {
        //旋转
        RotateAnimation animation = new RotateAnimation(0,360,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        animation.setDuration(1000);
        animation.setRepeatCount(100);
        animation.setRepeatMode(Animation.RESTART);
        animation.setFillAfter(true);
        img.startAnimation(animation);
    }

    public void anim(View view) {
        //创建综合动画集合
        AnimationSet set = new AnimationSet(true);
        //旋转
        RotateAnimation ra = new RotateAnimation(0f,10000f,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        set.addAnimation(ra);
        //平移
        TranslateAnimation ta = new TranslateAnimation(0,780,0,1250);
        set.addAnimation(ta);
        //缩放
        ScaleAnimation sa = new ScaleAnimation(1,2,1,2);
        set.addAnimation(sa);
        //透明
        AlphaAnimation aa = new AlphaAnimation(0,1);
        set.addAnimation(aa);
        set.setDuration(2000);
        set.setRepeatCount(5);
        set.setRepeatMode(Animation.REVERSE);
        set.setFillBefore(true);
        img.startAnimation(set);
    }
}

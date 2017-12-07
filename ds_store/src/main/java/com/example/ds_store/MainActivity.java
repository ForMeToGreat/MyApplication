package com.example.ds_store;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView aihaozhe;
    private ImageView meiyeren;
    private TextView chongxin;
    private TextView xuanze;
    private TextView jiesao;
    private ImageView img01;
    private ImageView img02;
    private ImageView img03;
    private ImageView img04;
    private ImageView img05;
    private ImageView img6;
    private ImageView img7;
    private ImageView img8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        aihaozhe = (ImageView) findViewById(R.id.aihaozhe);
        meiyeren = (ImageView) findViewById(R.id.meiyeren);
        chongxin = (TextView) findViewById(R.id.chongxin);
        xuanze = (TextView) findViewById(R.id.xuanze);
        jiesao = (TextView) findViewById(R.id.jiesao);
        img01 = (ImageView) findViewById(R.id.img01);
        img02 = (ImageView) findViewById(R.id.img02);
        img03 = (ImageView) findViewById(R.id.img03);
        img04 = (ImageView) findViewById(R.id.img04);
        img05 = (ImageView) findViewById(R.id.img05);
        img6 = (ImageView) findViewById(R.id.img06);
        img7 = (ImageView) findViewById(R.id.img07);
        img8 = (ImageView) findViewById(R.id.img08);
        img01.setOnClickListener(new ImageListener(1,0x111));
        img02.setOnClickListener(new ImageListener(1,0x112));
        img03.setOnClickListener(new ImageListener(1,0x113));
        img04.setOnClickListener(new ImageListener(1,0x114));
        img6.setOnClickListener(new ImageListener(1,1));
        chongxin.setOnClickListener(this);
        aihaozhe.setOnClickListener(this);
        meiyeren.setOnClickListener(this);
        initAnim();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.chongxin:
                xuanze.setText("身份选择");
                jiesao.setText("请小仙女选择一个在美课堂的身份吧");
                setEnabled(true);
                setGone(1);
                meiyeGone(1);
                initAnim();
                break;
            case R.id.aihaozhe:
                aihaozhe();
                break;
            case R.id.meiyeren:
                meiyeren();
                break;
        }
    }

    private void initAnim() {
        ObjectAnimator aanim = ObjectAnimator.ofFloat(aihaozhe,"translationX",0,-250);
        ObjectAnimator manim = ObjectAnimator.ofFloat(meiyeren,"translationX",0,250);
        ObjectAnimator alpha = ObjectAnimator.ofFloat(aihaozhe,"alpha",1,1);
        ObjectAnimator alpha2 = ObjectAnimator.ofFloat(meiyeren,"alpha",1,1);
        alpha.start();
        alpha2.start();
        aanim.start();
        manim.start();
    }
    private void aihaozhe() {
        ObjectAnimator aihao = ObjectAnimator.ofFloat(aihaozhe,"translationX",-250,0);
        ObjectAnimator meiye = ObjectAnimator.ofFloat(meiyeren,"translationX",250,0);
        ObjectAnimator alpha = ObjectAnimator.ofFloat(meiyeren,"alpha",1,0);
        AnimatorSet set = new AnimatorSet();
        set.play(meiye).with(alpha);
        set.setDuration(2000);
        set.start();
        aihao.setDuration(2000);
        aihao.start();
        aihao.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                aihaoAnimator();
                xuanze.setText("兴趣选择");
                jiesao.setText("请选择感兴趣的魔法~");
            }
        });
        setEnabled(false);
    }
    private void aihaoAnimator() {
        setGone(0);
        PropertyValuesHolder holderY1 = PropertyValuesHolder.ofFloat("translationY",0,-530);
        PropertyValuesHolder holderX1 = PropertyValuesHolder.ofFloat("translationX",0,-150);
        PropertyValuesHolder holderX2 = PropertyValuesHolder.ofFloat("translationX",0,150);
        PropertyValuesHolder holderX3 = PropertyValuesHolder.ofFloat("translationX",0,-300);
        PropertyValuesHolder holderX4 = PropertyValuesHolder.ofFloat("translationX",0,300);
        PropertyValuesHolder holderY2 = PropertyValuesHolder.ofFloat("translationY",0,-260);
        PropertyValuesHolder holderY3 = PropertyValuesHolder.ofFloat("translationY",0,400);
        PropertyValuesHolder alpha = PropertyValuesHolder.ofFloat("alpha",0,1);
        ObjectAnimator ob1 = ObjectAnimator.ofPropertyValuesHolder(img01,holderY1,holderX1,alpha);
        ObjectAnimator ob2 = ObjectAnimator.ofPropertyValuesHolder(img02,holderY1,holderX2,alpha);
        ObjectAnimator ob3 = ObjectAnimator.ofPropertyValuesHolder(img03,holderY2,holderX3,alpha);
        ObjectAnimator ob4 = ObjectAnimator.ofPropertyValuesHolder(img04,holderY2,holderX4,alpha);
        ObjectAnimator ob5 = ObjectAnimator.ofPropertyValuesHolder(img05,holderY3,alpha);
        AnimatorSet set = new AnimatorSet();
        set.play(ob1).with(ob2).with(ob3).with(ob4).with(ob5);
        set.setDuration(2000);
        set.start();
    }
    private void setGone(int i){
        if (i==0){
            img01.setVisibility(View.VISIBLE);
            img02.setVisibility(View.VISIBLE);
            img03.setVisibility(View.VISIBLE);
            img04.setVisibility(View.VISIBLE);
            img05.setVisibility(View.VISIBLE);
        }else if (i==1){
            img01.setVisibility(View.GONE);
            img02.setVisibility(View.GONE);
            img03.setVisibility(View.GONE);
            img04.setVisibility(View.GONE);
            img05.setVisibility(View.GONE);
        }
    }
    private void meiyeren() {
        ObjectAnimator aihao = ObjectAnimator.ofFloat(aihaozhe,"translationX",-250,0);
        ObjectAnimator meiye = ObjectAnimator.ofFloat(meiyeren,"translationX",250,0);
        ObjectAnimator alpha = ObjectAnimator.ofFloat(aihaozhe,"alpha",1,0);
        AnimatorSet set = new AnimatorSet();
        set.play(aihao).with(alpha);
        set.setDuration(2000);
        set.start();
        meiye.setDuration(2000);
        meiye.start();
        meiye.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                meiyeAnimator();
                xuanze.setText("技能等级");
                jiesao.setText("请根据您的实际情况选择技能等级");
            }
        });
        setEnabled(false);
    }

    private void meiyeAnimator() {
        meiyeGone(0);
        PropertyValuesHolder holderX = PropertyValuesHolder.ofFloat("translationX",0,-250);
        PropertyValuesHolder holderX2 = PropertyValuesHolder.ofFloat("translationX",0,250);
        PropertyValuesHolder holderY = PropertyValuesHolder.ofFloat("translationY",0,250);
        PropertyValuesHolder alpha = PropertyValuesHolder.ofFloat("alpha",0,1);
        ObjectAnimator left = ObjectAnimator.ofPropertyValuesHolder(img6,holderX,holderY,alpha);
        ObjectAnimator ob = ObjectAnimator.ofPropertyValuesHolder(img7,holderY,alpha);
        ObjectAnimator right = ObjectAnimator.ofPropertyValuesHolder(img8,holderX2,holderY,alpha);
        AnimatorSet set = new AnimatorSet();
        set.play(left).with(ob).with(right);
        set.setDuration(2000);
        set.start();
    }

    private void meiyeGone(int i) {
        if (i==0){
            img6.setVisibility(View.VISIBLE);
            img7.setVisibility(View.VISIBLE);
            img8.setVisibility(View.VISIBLE);
        }else if (i==1){
            img6.setVisibility(View.GONE);
            img7.setVisibility(View.GONE);
            img8.setVisibility(View.GONE);
        }
    }

    public void setEnabled(boolean flag){
        aihaozhe.setEnabled(flag);
        meiyeren.setEnabled(flag);
        img6.setEnabled(flag);
        img7.setEnabled(flag);
        img8.setEnabled(flag);
    }
    class ImageListener implements View.OnClickListener{
        private int i;
        private int index;
        public ImageListener(int i,int index) {
            this.i = i;
            this.index = index;
        }
        @Override
        public void onClick(View v) {
            i++;
            switch (v.getId()){
                case R.id.img01:
                    if (i%2==0){
                        if (index==0x111){
                            img01.setImageResource(R.mipmap.shenghuoxiuxian_select);
                        }
                    }else{
                        img01.setImageResource(R.mipmap.shenghuoxiuxian);
                    }
                    break;
                case R.id.img02:
                    if (i%2==0){
                        if (index==0x112){
                            img02.setImageResource(R.mipmap.zhutihuodong_select);
                        }
                    }else{
                        img02.setImageResource(R.mipmap.zhutihuodong);
                    }
                    break;
                case R.id.img03:
                    if (i%2==0){
                        if (index==0x113){
                            img03.setImageResource(R.mipmap.zhichangjingying_select);
                        }
                    }else{
                        img03.setImageResource(R.mipmap.zhichangjingying);
                    }
                case R.id.img04:
                    if (i%2==0){
                        if (index==0x114){
                            img04.setImageResource(R.mipmap.suxingyuyangsheng_select);
                        }
                    }else{
                        img04.setImageResource(R.mipmap.suxingyuyangsheng);
                    }
                    break;
                case R.id.img06:
                    chuxue();
                    break;
                case R.id.img07:

                    break;
                case R.id.img08:

                    break;
            }
        }
    }

    private void chuxue() {
        chuxueGone(1);
        ObjectAnimator ob1 = ObjectAnimator.ofFloat(img6,"translationX",-250,0);
        ObjectAnimator ob2 = ObjectAnimator.ofFloat(img6,"translationY",250,0);
        AnimatorSet set = new AnimatorSet();
        set.play(ob1).with(ob2);
        set.setDuration(2000);
        set.start();
    }
    private void chuxueGone(int i) {
        if (i == 1) {
            meiyeren.setVisibility(View.GONE);
            img7.setVisibility(View.GONE);
            img8.setVisibility(View.GONE);
        }else if (i==0){

        }
    }
}

package com.example.propertyanimation;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView text;
    private Animator animator;
    private boolean flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = (TextView) findViewById(R.id.text3);
        flag = true;
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag){
                    flag = false;
                    animator.pause();
                }else{
                    animator.resume();
                    flag = true;
                }
            }
        });
    }

    public void translate(View view) {
        float x = text.getTranslationX();
        float y = text.getTranslationY();
        ObjectAnimator animatorX = ObjectAnimator.ofFloat(text,"translationX",x,-300,x+300,x);
        ObjectAnimator animatorY = ObjectAnimator.ofFloat(text,"translationY",y,-500,y+500,y);
        /*animatorX.setDuration(5000);
        animatorY.setDuration(5000);
        animatorX.setRepeatCount(3);
        animatorY.setRepeatCount(3);
        animatorX.start();
        animatorY.start();*/
        AnimatorSet set = new AnimatorSet();
        set.play(animatorX).with(animatorY);
        set.setDuration(4000);
        set.start();
    }

    public void scale(View view) {
        ObjectAnimator animatorX = ObjectAnimator.ofFloat(text,"scaleX",1f,3f,0f,1f);
        ObjectAnimator animatorY = ObjectAnimator.ofFloat(text,"scaleY",1f,3f,0f,1f);
        animatorX.setDuration(5000);
        animatorY.setDuration(5000);
        animatorX.setRepeatCount(3);
        animatorY.setRepeatCount(3);
        animatorX.start();
        animatorY.start();
    }

    public void alpha(View view) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(text,"alpha",1f,0f,1f);
        animator.setDuration(3000);
        animator.setRepeatCount(3);
        animator.start();
    }

    public void rotate(View view) {
        ObjectAnimator animatorX = ObjectAnimator.ofFloat(text,"rotationX",0f,360f);
        ObjectAnimator animatorY = ObjectAnimator.ofFloat(text,"rotationY",0f,360f);
        animatorX.setDuration(5000);
        animatorY.setDuration(5000);
        animatorX.setRepeatCount(3);
        animatorY.setRepeatCount(3);
        animatorX.start();
        animatorY.start();

        /*PropertyValuesHolder holderXY = PropertyValuesHolder.ofFloat("rotation",0,360);
        PropertyValuesHolder holderX = PropertyValuesHolder.ofFloat("rotationX",0,360);
        PropertyValuesHolder holderY = PropertyValuesHolder.ofFloat("rotationY",0,360);
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(text,holderXY,holderX,holderY);
        animator.setDuration(3000);
        animator.setRepeatCount(10);
        animator.start();*/
    }

    public void all(View view) {
        //第一种组合动画
        ObjectAnimator move = ObjectAnimator.ofFloat(text,"translationX",0f,-500f,0f);
        ObjectAnimator scale = ObjectAnimator.ofFloat(text,"scaleX",1f,2f,1f);
        ObjectAnimator rotate = ObjectAnimator.ofFloat(text,"rotation",0f,360f);
        ObjectAnimator alpha = ObjectAnimator.ofFloat(text,"alpha",1f,0f,1f);
        AnimatorSet set = new AnimatorSet();
        set.play(rotate).with(alpha).after(move).before(scale);
        set.setDuration(5000);
        set.start();

        //第二种组合动画
        /*PropertyValuesHolder holderX = PropertyValuesHolder.ofFloat("scaleX",1.0f,0.5f);
        PropertyValuesHolder holderY = PropertyValuesHolder.ofFloat("scaleY",1.0f,0.5f);
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(text,holderX,holderY);
        animator.setDuration(2000);
        animator.setRepeatCount(5);
        animator.start();*/

        //第三种组合动画
        //第一步通过控件获取View的属性动画对象
        /*ViewPropertyAnimator animate = text.animate();
        animate.translationXBy(0).translationX(300);
        animate.translationYBy(0).translationY(300);
        animate.rotation(360*3);
        animate.rotationXBy(0).rotationX(360*3);
        animate.rotationYBy(0).rotationY(360*3);
        animate.translationZ(300);
        animate.setDuration(2000);
        animate.start();*/

    }

    public void alltwo(View view) {
        animator = AnimatorInflater.loadAnimator(this, R.animator.animator_item);
        animator.setTarget(text);
        animator.start();
    }
}

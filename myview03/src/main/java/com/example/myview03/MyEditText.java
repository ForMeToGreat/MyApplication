package com.example.myview03;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Administrator on 2017/11/9.
 */

public class MyEditText extends android.support.v7.widget.AppCompatEditText{

    private Drawable drawable;
    private Rect rect;

    public MyEditText(Context context) {
        this(context,null);
    }

    public MyEditText(Context context, AttributeSet attrs) {
        this(context, attrs,-1);
    }

    public MyEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        drawable = context.getResources().getDrawable(R.mipmap.remove);
        rect = new Rect();
        TypedArray array = context.getResources().obtainAttributes(attrs, R.styleable.MyEditText);
        String text = array.getString(R.styleable.MyEditText_text);
        if (!TextUtils.isEmpty(text)){
            setText("  "+text);
            setDrawable(true);
        }
        //写EditText的点击事件,监听文本改变时的状态
        this.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(s)){
                    setDrawable(false);
                }else{
                    setDrawable(true);
                }
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void setDrawable(boolean flag) {
        if (flag){
            setCompoundDrawablesRelativeWithIntrinsicBounds(null,null,drawable,null);
        }else{
            setCompoundDrawablesRelativeWithIntrinsicBounds(null,null,null,null);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //获取总的区域放到Rect中
        getGlobalVisibleRect(rect);
        //获取点击的位置
        int w = (int) event.getRawX();
        int h = (int) event.getRawY();
        //获取图片的宽高
        int dw = drawable.getIntrinsicWidth();
        int dh = drawable.getIntrinsicHeight();

        Rect rect1 = new Rect(rect.width()-dw,rect.top,rect.width()+30,rect.bottom);
        if (rect1.contains(w,h)){
            this.setText("");
        }
        return super.onTouchEvent(event);
    }
}

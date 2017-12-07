package com.example.myview02;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.TextUtils;
import android.util.AttributeSet;

/**
 * Created by Administrator on 2017/11/9.
 */

public class MyEditText extends android.support.v7.widget.AppCompatEditText{

    private String text;
    private int color;
    private float awidth;
    private Paint paint;

    public MyEditText(Context context) {
        this(context,null);
    }

    public MyEditText(Context context, AttributeSet attrs) {
        this(context, attrs,-1);
    }

    public MyEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = context.getResources().obtainAttributes(attrs, R.styleable.MyEditText);
        text = array.getString(R.styleable.MyEditText_text);
        if (!TextUtils.isEmpty(text)){
            setText(text);
        }
        color = array.getColor(R.styleable.MyEditText_color, Color.parseColor("#f00f00"));
        awidth = array.getDimension(R.styleable.MyEditText_width, 1);
        initPaint();
        array.recycle();
    }

    private void initPaint() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStrokeWidth(awidth);
        paint.setColor(color);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //获取控件的宽度和高度
        int width = getWidth();
        int height = getHeight();
        //获取每一行的高度
        int hheight = getLineHeight();
        //划线的条数
        int num = height/hheight;
        //通过for循环去划线
        for (int i=1;i<=num;i++){
            canvas.drawLine(10,i*hheight-paint.descent(),width-10,i*hheight-paint.descent(),paint);
        }
    }
}

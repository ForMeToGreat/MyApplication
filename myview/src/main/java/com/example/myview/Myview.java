package com.example.myview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2017/11/6.
 */

public class Myview extends View{

    private Paint paint;
    private Rect rect;

    public Myview(Context context) {
        super(context);
    }

    public Myview(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    private void initPaint() {
        //创建画笔
        paint = new Paint();
        //设置画笔的颜色
        paint.setColor(Color.parseColor("#0f0f0f"));
        //设置画笔的宽度
        paint.setStrokeWidth(3);
        //设置画笔的样式;
        // STROKE：线条，空心的
        //FILL：实体的
        paint.setStyle(Paint.Style.STROKE);
        //设置画笔是否平滑
        paint.setAntiAlias(true);

        //矩形对象
        //rect = new Rect(0,0,600,600);
    }

    /**
     *
     * @param widthMeasureSpec;宽度
     * @param heightMeasureSpec;高度
     *
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //setMeasuredDimension(myMeasure(widthMeasureSpec,1),myMeasure(heightMeasureSpec,2));
    }
    /**
     * 第一个参数用来标识宽度和高度
     * 第二个参数用来标识测量的宽度和高度
     * 1标识宽度，2标识高度
     */
    /*private int myMeasure(int spec, int mode) {
        //MeasureSpec.AT_MOST:最多，之多，不超过       wrap_content
        //MeasureSpec.UNSPECIFIED:未指定
        //MeasureSpec.EXACTLY :精确的，指定了高度和宽度   100dp

        //获取xml文件中设置控件的类型
        int specmode = MeasureSpec.getMode(spec);
        //
        int size = MeasureSpec.getSize(spec);
        if (specmode == MeasureSpec.EXACTLY){
            return size;
        }else{
            if (mode==1){
                size = rect.width();
            }else{
                size = rect.height();
            }
        }
        return size;
    }*/

    /**
     *
     * @param canvas;画布
     * 这个方法是用来绘画的
     */
    /*@Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.drawRect(100,100,500,500,paint);
    }*/


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int mwidth = getWidth()/2;
        int mheight = getHeight()/2;
        canvas.drawRect(mwidth-100,mheight-100,mwidth+100,mheight+100,paint);
        paint.setTextSize(23);
        Rect rect1 = new Rect();
        String str = "你好";
        paint.getTextBounds(str,0,str.length(),rect1);
        canvas.drawText("我擦擦擦擦",mwidth-rect1.width()/2,mheight-rect1.width()/2,paint);

    }
}

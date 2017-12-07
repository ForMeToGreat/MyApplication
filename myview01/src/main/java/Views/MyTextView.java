package Views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.myview01.R;

/**
 * Created by Administrator on 2017/11/8.
 */

public class MyTextView extends View{

    private int color;
    private int size;
    private String str;
    private Paint paint;

    public MyTextView(Context context) {
        this(context,null);
    }

    public MyTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,-1);
    }

    public MyTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //查找声明出来的属性的数组
        TypedArray array = context.getResources().obtainAttributes(attrs, R.styleable.MyTextView);
        color = array.getColor(R.styleable.MyTextView_color, Color.BLACK);
        float density = context.getResources().getDisplayMetrics().density;
        size = array.getInteger(R.styleable.MyTextView_size, (int) density);
        str = array.getString(R.styleable.MyTextView_text);
        //设置属性可以被反复使用
        array.recycle();
        initPaint();
    }

    private void initPaint() {
        paint = new Paint();
        paint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //设置属性画一个矩形
        paint.setColor(Color.parseColor("#50f00f00"));
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(100,100,800,800,paint);
        //修改属性书写文字
        paint.setColor(color);
        paint.setTextSize(size);
        paint.setStyle(Paint.Style.STROKE);

        int w = (int) paint.measureText(str,0,str.length());
        int h = (int) (paint.getFontMetrics().descent-paint.getFontMetrics().ascent);
        canvas.drawText(str,0,str.length(),500-w/2,500-h/2,paint);
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
        invalidate();
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
        invalidate();
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
        invalidate();
    }
}

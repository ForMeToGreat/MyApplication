package Views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2017/11/8.
 */

public class MyButton extends View implements View.OnClickListener{
    private int num = 0;
    private float textSize;
    private Paint paint;

    public MyButton(Context context) {
        super(context);
    }

    public MyButton(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        //获取系统默认的字体大小
        textSize = context.getResources().getDisplayMetrics().density;
        initPaint();
        this.setOnClickListener(this);
    }

    private void initPaint() {
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setTextSize(textSize);
        paint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(Color.parseColor("#60f00f00"));
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(getWidth()/2,getHeight()/2,200,paint);

        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setTextSize(50);
        String ss = String.valueOf(num);
        Rect rect = new Rect();
        paint.getTextBounds(ss,0,ss.length(),rect);
        canvas.drawText(ss,0,ss.length(),getWidth()/2-rect.width()/2,getHeight()/2-rect.height()/2,paint);
    }

    @Override
    public void onClick(View v) {
        num++;
        //通知视图进行重新绘制
        invalidate();
    }
}

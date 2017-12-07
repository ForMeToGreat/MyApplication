package Views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2017/11/7.
 */

public class MyView extends View{

    private Paint paint;

    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    private void initPaint() {
        paint = new Paint();
        paint.setStrokeWidth(5);
        paint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLACK);
        //贝塞尔曲线
        Path path = new Path();
        path.moveTo(200,200);
        path.quadTo(400,600,800,100);
        canvas.drawPath(path,paint);

        paint.setColor(Color.RED);
        Path path1 = new Path();
        path1.moveTo(200,350);
        path1.quadTo(400,750,800,250);
        canvas.drawPath(path1,paint);

        paint.reset();

        paint.setColor(Color.RED);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        paint.setTextSize(40);
        String str = "臧婷我爱你";
        canvas.drawTextOnPath(str,path,0,str.length(),paint);
    }
}

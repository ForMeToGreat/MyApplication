package view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by Administrator on 2017/11/14.
 */

public class CircleView extends ImageView {
    private Bitmap mbitmap;
    private BitmapShader shader;
    //画笔抗锯齿
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    //缩放
    private Matrix matrix = new Matrix();

    public CircleView(Context context) {
        super(context);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {


        //获取ImageView上的图片
        Drawable drawable = getDrawable();
        //将Drawable对象转换为Bitmap
        Bitmap bitmap = getBitmap(drawable);
        if (bitmap != null) {
            //获取图片的宽高
            int width = getWidth();
            int height = getHeight();
            //找出最短的一边确定为圆的直径
            int minSize = Math.min(width, height);

            if (shader == null || !bitmap.equals(mbitmap)) {
                mbitmap = bitmap;
                shader = new BitmapShader(mbitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
            }
            if (shader != null) {
                //测量缩放比
                matrix.setScale(minSize / mbitmap.getWidth(), minSize / mbitmap.getHeight());
                //
                shader.setLocalMatrix(matrix);
            }
            paint.setShader(shader);
            float radiu = minSize / 2;
            canvas.drawCircle(radiu, radiu, radiu, paint);
        } else {
            super.onDraw(canvas);
        }
    }

    private Bitmap getBitmap(Drawable drawable) {
        //BitmapDrawable;可绘制的，位图图片
        //ColorDrawable:可绘制的颜色图片
        //如果drawable对象是BitmapDrawable的子类
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        } else if (drawable instanceof ColorDrawable) {
            //将ColorDrawable转换为Bitmap对象
            //获取绘制颜色图片的宽高
            Rect rect = drawable.getBounds();
            int width = rect.width();
            int height = rect.height();
            //通过创建Bitmap去创建Bitmap对象
            Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            //创建画布
            Canvas canvas = new Canvas(bitmap);
            int color = ((ColorDrawable) drawable).getColor();
            canvas.drawARGB(Color.alpha(color), Color.red(color), Color.green(color), Color.blue(color));
            return bitmap;
        }
        return null;
    }
}

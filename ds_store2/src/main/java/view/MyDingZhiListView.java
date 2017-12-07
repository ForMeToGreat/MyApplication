package view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by Administrator on 2017/12/6.
 */

public class MyDingZhiListView extends ListView {
    public MyDingZhiListView(Context context) {
        super(context);
    }

    public MyDingZhiListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyDingZhiListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int heightMeasute = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, heightMeasute);
    }
}

package adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.example.administrator.myapplication.DetiaActivity;
import java.util.ArrayList;
import Bean.PagerBean;

/**
 * Created by Administrator on 2017/10/16.
 */

public class PagerAdapter01 extends android.support.v4.view.PagerAdapter{
    private ArrayList<ImageView>list;
    private ArrayList<PagerBean.Data>datas;
    private Context context;
    public PagerAdapter01(ArrayList<ImageView> list, ArrayList<PagerBean.Data> datas, Context context) {
        this.list = list;
        this.datas = datas;
        this.context = context;
    }

    @Override
    public int getCount() {
        if (list.size()>0){
            return Integer.MAX_VALUE;
        }
        return 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        ImageView image = list.get(position%list.size());
        ViewGroup group = (ViewGroup) image.getParent();
        if (group!=null){
            group.removeView(image);
        }
        container.addView(image);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PagerBean.Data data = datas.get(position%datas.size());
                long id = data.getId();
                Intent intent = new Intent(context, DetiaActivity.class);
                intent.putExtra("id",id);
                context.startActivity(intent);
            }
        });
        return image;

    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //super.destroyItem(container, position, object);
    }
}

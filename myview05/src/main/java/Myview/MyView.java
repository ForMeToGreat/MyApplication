package Myview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.example.myview05.R;
import java.util.ArrayList;
import DataBean.Bean;

/**
 * Create by Administrator on 2017/11/13.
 */

public class MyView extends LinearLayout{
    private ArrayList<Bean>beans = new ArrayList<>();
    private Context context;
    public MyView(Context context) {
        this(context,null);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,-1);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initView(context);
    }

    private void initView(Context context) {
        beans.add(new Bean(R.mipmap.ic_launcher,"天干物燥,小心火烛","100"));
        beans.add(new Bean(R.mipmap.ic_launcher_round,"初听不知曲中意","308"));
        beans.add(new Bean(R.mipmap.ic_launcher,"再听已是曲终人","120"));
        View view = LayoutInflater.from(context).inflate(R.layout.my_view,null);
        ListView listview = view.findViewById(R.id.listview);
        Myadapter adapter = new Myadapter();
        listview.setAdapter(adapter);
        addView(view);
    }
    class Myadapter extends BaseAdapter{

        @Override
        public int getCount() {
            return beans.size();
        }

        @Override
        public Bean getItem(int position) {
            return beans.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder vh;
            if (convertView == null){
                convertView = LayoutInflater.from(context).inflate(R.layout.list_item,null);
                vh = new ViewHolder(convertView);
                convertView.setTag(vh);
            }else{
                vh = (ViewHolder) convertView.getTag();
            }
            Bean bean = getItem(position);
            vh.img.setImageResource(bean.getImgId());
            vh.name.setText(bean.getName());
            vh.price.setText(bean.getPrice());
            return convertView;
        }
        class ViewHolder{
            private ImageView img;
            private TextView name,price;
            public ViewHolder(View v) {
                img = v.findViewById(R.id.img);
                name = v.findViewById(R.id.name);
                price = v.findViewById(R.id.price);
            }
        }
    }
}

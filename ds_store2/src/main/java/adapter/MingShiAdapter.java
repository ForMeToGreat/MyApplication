package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ds_store2.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import Bean.ShouyeBean;
import view.CircleView;

/**
 * Created by Administrator on 2017/12/5.
 */

public class MingShiAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<ShouyeBean.CBean> bean;

    public MingShiAdapter(Context context, ArrayList<ShouyeBean.CBean> bean) {
        this.context = context;
        this.bean = bean;
    }

    @Override
    public int getCount() {
        return bean.size();
    }

    @Override
    public ShouyeBean.CBean getItem(int position) {
        return bean.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.mingshi_item, null);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        Picasso.with(context).load(bean.get(position).getTeacher_img()).into(vh.mingImage);
        vh.mingName.setText(bean.get(position).getTeacher_name());
        return convertView;
    }

    class ViewHolder {
        private CircleView mingImage;
        private TextView mingName;

        public ViewHolder(View view) {
            mingImage = view.findViewById(R.id.mingImage);
            mingName = view.findViewById(R.id.mingName);
        }
    }
}

package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ds_store2.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import Bean.ShouyeBean;

/**
 * Created by Administrator on 2017/12/5.
 */

public class KeChengAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<ShouyeBean.HBean> bean;

    public KeChengAdapter(Context context, ArrayList<ShouyeBean.HBean> bean) {
        this.context = context;
        this.bean = bean;
    }

    @Override
    public int getCount() {
        return bean.size();
    }

    @Override
    public ShouyeBean.HBean getItem(int position) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.kecheng_item, null);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        Picasso.with(context).load(bean.get(position).getBig_img_url()).into(vh.keImage);
        vh.keText.setText(bean.get(position).getLesson_name());
        return convertView;
    }

    class ViewHolder {
        private ImageView keImage;
        private TextView keText;

        public ViewHolder(View view) {
            keImage = view.findViewById(R.id.keImage);
            keText = view.findViewById(R.id.keText);
        }
    }
}

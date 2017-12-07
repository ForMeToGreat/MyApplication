package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.demo01.R;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import Utils.JsonBean;

/**
 * Created by Administrator on 2017/10/25.
 */

public class MyAdapter extends BaseAdapter{
    private Context context;
    private ArrayList<JsonBean.Paramz.Feeds.Data>list;

    public MyAdapter(Context context, ArrayList<JsonBean.Paramz.Feeds.Data> list) {
        this.context = context;
        this.list = list;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public JsonBean.Paramz.Feeds.Data getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if (convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.listview_item,null);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        }else{
            vh = (ViewHolder) convertView.getTag();
        }
        JsonBean.Paramz.Feeds.Data data = getItem(position);
        vh.subject.setText(data.getSubject());
        vh.summary.setText(data.getSummary());
        vh.changed.setText(data.getChanged());
        Picasso.with(context).load("http://litchiapi.jstv.com"+data.getCover()).into(vh.cover);
        return convertView;
    }
    class ViewHolder{
        private TextView subject,summary,changed;
        private ImageView cover;
        public ViewHolder(View view) {
            subject = view.findViewById(R.id.subject);
            summary = view.findViewById(R.id.summary);
            changed = view.findViewById(R.id.changed);
            cover = view.findViewById(R.id.cover);
        }
    }
}

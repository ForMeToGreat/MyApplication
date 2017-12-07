package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.administrator.myapplication.R;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import Bean.OneBean;
import Utils.IAPI;

/**
 * Created by Administrator on 2017/10/13.
 */

public class OneAdapter extends BaseAdapter{
    private Context context;
    private ArrayList<OneBean.DataBean.ListBean>list;

    public OneAdapter(Context context, ArrayList<OneBean.DataBean.ListBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public OneBean.DataBean.ListBean getItem(int position) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.onelist_item,null);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        }else{
            vh = (ViewHolder) convertView.getTag();
        }
        OneBean.DataBean.ListBean bean = getItem(position);
        Picasso.with(context).load(IAPI.IMAGEPATH+bean.getHead()).into(vh.head);
        Picasso.with(context).load(IAPI.IMAGEPATH+bean.getImage()).into(vh.image);
        vh.contact.setText(bean.getContact()+"");
        vh.title.setText(bean.getTitle()+"");
        vh.time.setText(bean.getIssue_time()+"");
        vh.follow.setText(bean.getFollow()+"");
        return convertView;
    }
    class ViewHolder{
        ImageView head,image;
        TextView contact,time,title,follow;
        public ViewHolder(View view){
            head = (ImageView) view.findViewById(R.id.head);
            image = (ImageView) view.findViewById(R.id.issue_image);
            contact = (TextView) view.findViewById(R.id.contact);
            time = (TextView) view.findViewById(R.id.issue_time);
            title = (TextView) view.findViewById(R.id.title);
            follow = (TextView) view.findViewById(R.id.follow);
        }
    }
}

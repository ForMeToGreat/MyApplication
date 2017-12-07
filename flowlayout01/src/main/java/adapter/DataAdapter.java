package adapter;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.flowlayout01.R;

import java.util.List;

import JsonBean.Bean;
import cn.lankton.flowlayout.FlowLayout;

/**
 * Created by Administrator on 2017/11/14.
 */

public class DataAdapter extends BaseAdapter{
    private Bean bean;
    private Context context;

    public DataAdapter(Bean bean, Context context) {
        this.bean = bean;
        this.context = context;
    }

    @Override
    public int getCount() {
        return bean.getD().size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if (convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item,null);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        }else{
            vh = (ViewHolder) convertView.getTag();
        }
        Bean.DBean dbean = bean.getD().get(position);
        vh.name.setText(dbean.getName());
        List<Bean.MBean>ms = bean.getM().get(position);
        initFlowLayout(vh.flowlayout,ms);
        return convertView;
    }

    private void initFlowLayout(FlowLayout flowlayout, List<Bean.MBean> ms) {
        flowlayout.removeAllViews();
        for (int i=0;i<ms.size();i++){
            Bean.MBean mb = ms.get(i);
            TextView tv = new TextView(context);
            tv.setBackground(context.getResources().getDrawable(R.drawable.text_bolder));
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            ViewGroup.MarginLayoutParams margin = new ViewGroup.MarginLayoutParams(params);
            margin.leftMargin = 10;
            margin.rightMargin = 10;
            margin.bottomMargin = 10;
            tv.setLayoutParams(margin);
            tv.setPadding(7,1,7,3);
            tv.setGravity(Gravity.CENTER);
            tv.setTextSize(20);
            tv.setText(mb.getName());
            flowlayout.addView(tv);
        }
    }

       class ViewHolder{
        private TextView name;
        private FlowLayout flowlayout;
        public ViewHolder(View view) {
            name = view.findViewById(R.id.name);
            flowlayout = view.findViewById(R.id.flowlayout);
        }
    }
}

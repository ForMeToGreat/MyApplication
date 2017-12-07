package adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ds_store2.R;

import java.util.ArrayList;
import java.util.List;

import Bean.DingZhiBean;
import Utils.Dip2PxUtils;
import cn.lankton.flowlayout.FlowLayout;

/**
 * Created by Administrator on 2017/12/6.
 */

public class DingZhiAdapter extends BaseAdapter {
    private DingZhiBean bean;
    private Context context;
    private DingData ding;

    public DingZhiAdapter(DingZhiBean bean, Context context, DingData ding) {
        this.bean = bean;
        this.context = context;
        this.ding = ding;
    }

    @Override
    public int getCount() {
        return bean.getD().size();
    }

    @Override
    public DingZhiBean.DBean getItem(int position) {
        return bean.getD().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.dingzhi_item, null);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        DingZhiBean.DBean dbean = getItem(position);
        vh.name.setText(dbean.getName());
        List<DingZhiBean.MBean> ms = bean.getM().get(position);
        initRan(vh.flowlayout, (ArrayList<DingZhiBean.MBean>) bean.getM().get(position), position);
        return convertView;
    }

    private void initRan(final FlowLayout flowLayout, final ArrayList<DingZhiBean.MBean> ms, final int position) {
        flowLayout.removeAllViews();
        for (int i = 0; i < ms.size(); i++) {
            int ranHeight = Dip2PxUtils.dip2px(context, 30);
            int ranWidth = ViewGroup.LayoutParams.WRAP_CONTENT;
            ViewGroup.MarginLayoutParams lp = new ViewGroup.MarginLayoutParams(ranWidth, ranHeight);
            lp.setMargins(Dip2PxUtils.dip2px(context, 10), 5, Dip2PxUtils.dip2px(context, 10), 5);
            final TextView tv = new TextView(context);
            tv.setPadding(Dip2PxUtils.dip2px(context, 15), 0, Dip2PxUtils.dip2px(context, 15), 0);
            tv.setTextColor(Color.parseColor("#ff3030"));
            tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
            tv.setText(ms.get(i).getName());
            tv.setGravity(Gravity.CENTER_VERTICAL);
            tv.setLines(1);
            tv.setTextColor(Color.BLACK);
            tv.setBackgroundColor(Color.parseColor("#eaf4fc"));
            tv.setTag(i);
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ding.removeData(ms.get((Integer) tv.getTag()), position);
                }
            });
            flowLayout.addView(tv, lp);
        }

    }

    class ViewHolder {
        private TextView name;
        private FlowLayout flowlayout;

        public ViewHolder(View view) {
            name = view.findViewById(R.id.name);
            flowlayout = view.findViewById(R.id.flowlayout);
        }
    }

    public interface DingData {
        void removeData(DingZhiBean.MBean m, int position);
    }
}

package com.example.ds_store2;

import android.content.res.AssetManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

import Bean.DingZhiBean;
import Utils.Dip2PxUtils;
import Utils.JsonUtils;
import adapter.DingZhiAdapter;
import cn.lankton.flowlayout.FlowLayout;

public class DingZhiActivity extends AppCompatActivity implements DingZhiAdapter.DingData {

    private DingZhiBean dingbean;
    private ListView listview;
    private FlowLayout mainflow;
    private DingZhiAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ding_zhi);
        String assets = getAssetsData();
        JsonUtils utils = new JsonUtils();
        dingbean = utils.getDingData(assets);
        initView();
    }

    private void initView() {
        listview = (ListView) findViewById(R.id.listview);
        adapter = new DingZhiAdapter(dingbean, this, this);
        listview.setAdapter(adapter);
        mainflow = (FlowLayout) findViewById(R.id.mainflow);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }

    public String getAssetsData() {
        AssetManager manager = getAssets();
        try {
            InputStream open = manager.open("custom.json");
            byte[] by = new byte[1024];
            int len = 0;
            StringBuffer sb = new StringBuffer();
            while ((len = open.read(by)) != -1) {
                sb.append(new String(by, 0, len));
            }
            return String.valueOf(sb);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public void removeData(DingZhiBean.MBean m, int position) {
        dingbean.getM().get(position).remove(m);
        adapter.notifyDataSetChanged();
        initFloatLayout(m.getName(), position);
    }

    private void initFloatLayout(final String ss, final int position) {
        int ranHeight = Dip2PxUtils.dip2px(this, 30);
        int ranWidth = ViewGroup.LayoutParams.WRAP_CONTENT;
        ViewGroup.MarginLayoutParams lp = new ViewGroup.MarginLayoutParams(ranWidth, ranHeight);
        lp.setMargins(Dip2PxUtils.dip2px(this, 10), 5, Dip2PxUtils.dip2px(this, 10), 5);

        final TextView tv = new TextView(this);
        tv.setPadding(Dip2PxUtils.dip2px(this, 15), 0, Dip2PxUtils.dip2px(this, 15), 0);
        tv.setTextColor(Color.parseColor("#ff3030"));
        tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
        tv.setText(ss);

        tv.setGravity(Gravity.CENTER_VERTICAL);
        tv.setLines(1);
        tv.setTextColor(Color.BLACK);
        tv.setBackgroundColor(Color.parseColor("#eaf4fc"));
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainflow.removeView(tv);
                dingbean.getM().get(position).add(new DingZhiBean().new MBean(ss));
                adapter.notifyDataSetChanged();
            }
        });
        mainflow.addView(tv, lp);
    }
}

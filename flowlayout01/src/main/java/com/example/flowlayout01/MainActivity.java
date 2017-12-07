package com.example.flowlayout01;

import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.io.IOException;
import java.io.InputStream;

import JsonBean.Bean;
import JsonBean.JsonUtils;
import adapter.DataAdapter;
import cn.lankton.flowlayout.FlowLayout;

public class MainActivity extends AppCompatActivity {

    private Bean dataBean;
    private ListView listview;
    private DataAdapter adapter;
    private FlowLayout mainflow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String assetsData = getAssetsData();
        JsonUtils utils = new JsonUtils();
        dataBean = utils.getDataBean(assetsData);
        initView();
    }

    private void initView() {
        listview = (ListView) findViewById(R.id.listview);
        adapter = new DataAdapter(dataBean,this);
        listview.setAdapter(adapter);
        mainflow = (FlowLayout) findViewById(R.id.mainflow);

    }

    public String getAssetsData() {
        AssetManager manager = getAssets();
        try {
            InputStream open = manager.open("custom.json");
            byte[]by = new byte[1024];
            int len = 0;
            StringBuffer sb = new StringBuffer();
            while ((len=open.read(by))!=-1){
                sb.append(new String(by,0,len));
            }
            return String.valueOf(sb);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

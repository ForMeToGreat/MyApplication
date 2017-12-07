package com.example.administrator.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;

import Bean.OneBean;
import Utils.HttpUtils;
import Utils.IAPI;
import Utils.JsonUtils;
import adapter.OneAdapter;

public class SeachDetiaActivity extends Activity implements HttpUtils.DownLoad{
    private ArrayList<OneBean.DataBean.ListBean>datalist = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seach_detia);
        initView();
        Intent intent = getIntent();
        String str = intent.getStringExtra("title");
        HashMap<String,String>map = new HashMap<>();
        map.put("title",str);
        map.put("curPage","1");
        HttpUtils.postHttpRequest(this, IAPI.ADDRESS+ IAPI.SEACH_LIST,map,this,0x114);
    }

    private void initView() {
        ListView list = findViewById(R.id.list01);
        OneAdapter adapter = new OneAdapter(this,datalist);
        list.setAdapter(adapter);
    }
    @Override
    public void load(String data, int tag) {
        //Log.i("tag","=======data========"+data);
        OneBean bean = new JsonUtils().getOneBean(data);
        if (bean.getStatus().equals("200")&&bean.getInfo().equals("成功")){
            datalist.clear();
            ArrayList<OneBean.DataBean.ListBean>list = bean.getData().getList();
            datalist.addAll(list);
        }
    }
}

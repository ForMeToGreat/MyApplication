package com.example.demo01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;

import Utils.HttpUtils;
import Utils.JsonBean;
import Utils.JsonUtils;
import adapter.MyAdapter;

public class MainActivity extends AppCompatActivity implements HttpUtils.DownLoad{
    private int index =1;
    private String path = "http://litchiapi.jstv.com/api/GetFeeds?column=6&PageSize=20&pageIndex="+index+"&val=100511D3BE5301280E0992C73A9DEC41";
    private PullToRefreshListView listview;
    private ArrayList<JsonBean.Paramz.Feeds.Data>lists = new ArrayList<>();
    private MyAdapter adapter;
    private HttpUtils utils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        utils = new HttpUtils(this,this);
        utils.getResult(path);
    }

    private void initView() {
        listview = (PullToRefreshListView) findViewById(R.id.listview);
        adapter = new MyAdapter(this,lists);
        listview.setAdapter(adapter);
        listview.setMode(PullToRefreshBase.Mode.BOTH);
        listview.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                index++;
                utils.getResult(path);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                index++;
                utils.getResult(path);
            }
        });
    }

    @Override
    public void load(String result) {
        if (result==null){
            Toast.makeText(this,"数据不存在", Toast.LENGTH_SHORT).show();
            return;
        }
        JsonBean bean = new JsonUtils().getJson(result);
        if (bean.getStatus().equals("ok")){
            lists.clear();
            ArrayList<JsonBean.Paramz.Feeds>feeds = bean.getParamz().getFeeds();
            for (int i = 0;i<feeds.size();i++){
                JsonBean.Paramz.Feeds.Data data = feeds.get(i).getData();
                lists.add(data);
                adapter.notifyDataSetChanged();
            }
        }
    }
}

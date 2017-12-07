package com.example.administrator.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import Bean.StroreDetialMessageBean;
import Utils.HttpUtils;
import Utils.IAPI;
import Utils.JsonUtils;
import Utils.SharedPreferenceUtils;

public class GoodsDetiaActivity extends AppCompatActivity implements HttpUtils.DownLoad{

    private ImageView heand;
    private TextView content;
    private TextView title;
    private TextView price;
    private TextView goodsId;
    private TextView issueTime;
    private TextView description;
    private ImageView store_image;
    private TextView mobile;
    private TextView qq;
    private TextView owned;
    private TextView state;
    private TextView followed;
    private boolean isFollowed;
    private StroreDetialMessageBean.Data data;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        token = new SharedPreferenceUtils(this).getToken();
        initview();
        Intent intent = getIntent();
        if (intent!=null){
            int id = intent.getIntExtra("id",0);
            HashMap<String,String>map = new HashMap<>();
            map.put("id",id+"");
            map.put("token",token);
            HttpUtils.postRequest(this,IAPI.ADDRESS+IAPI.GOODS_DIL,map,this);
        }
    }
    private void initview() {
        heand = (ImageView) findViewById(R.id.Store_head);
        content = (TextView) findViewById(R.id.Store_contact);
        title = (TextView) findViewById(R.id.Store_title);
        price = (TextView) findViewById(R.id.Store_price);
        goodsId = (TextView) findViewById(R.id.Store_id);
        issueTime = (TextView) findViewById(R.id.Store_issue_time);
        description = (TextView) findViewById(R.id.Store_description);
        store_image = (ImageView) findViewById(R.id.image_Store);
        mobile = (TextView) findViewById(R.id.Store_mobile);
        qq = (TextView) findViewById(R.id.Store_qq);
        owned = (TextView) findViewById(R.id.owned);
        state = (TextView) findViewById(R.id.state);
        followed = (TextView) findViewById(R.id.followed);
        followed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String,String>map = new HashMap<String, String>();
                long id = data.getId();
                map.put("id",id+"");
                if (data!=null){
                    int act;
                    if (isFollowed){
                        act = 1;
                        map.put("act",1+"");
                        map.put("token",token);
                    }else {
                        act = 0;
                        map.put("act",0+"");
                        map.put("token",token);
                    }
                    HttpUtils.postHttpRequest(GoodsDetiaActivity.this, IAPI.ADDRESS+IAPI.FOLLOW,map,GoodsDetiaActivity.this,0x1001);
                }
            }
        });
    }

    @Override
    public void load(String json, int tag) {
        if (tag == 0x1001){
            Log.i("tag", "load: =====>"+json);
            try {
                JSONObject ob = new JSONObject(json);
                if (ob.optString("status").equals("200")&&ob.optString("info").equals("成功")){
                    if (isFollowed){
                        followed.setText("未关注");
                        isFollowed = false;
                    }else{
                        followed.setText("已关注");
                        isFollowed = true;
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }else{
            upload(json);
        }

    }
    public void upload(String json){
        StroreDetialMessageBean bean = JsonUtils.StoreUtils(json);
        data = bean.getData();
        if (data ==null){
            Toast.makeText(this,"网络执行失败",Toast.LENGTH_SHORT).show();
            return;
        }
        Picasso.with(this).load(IAPI.IMAGEPATH+ data.getHead()).into(heand);
        content.setText("产品名："+ data.getContact());
        title.setText("标题："+ data.getTitle());
        issueTime.setText("发布时间："+ data.getIssue_time());
        price.setText("价格："+ data.getPrice());
        goodsId.setText("产品编号："+ data.getId()+"");
        description.setText("描述："+ data.getDescription());
        Picasso.with(this).load(IAPI.IMAGEPATH+ data.getList().get(0).getStrtr()).into(store_image);
        mobile.setText("电话："+ data.getMobile());
        qq.setText("QQ："+ data.getQq());

        if (data.isOwned()){
            owned.setText("有库存");
        }else{
            owned.setText("已售罄");
        }
        state.setText("正常");
        isFollowed = data.isFollowed();
        if (isFollowed){
            followed.setText("已关注");
        }else {
            followed.setText("未关注");
        }
    }

}

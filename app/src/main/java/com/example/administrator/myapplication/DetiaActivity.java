package com.example.administrator.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import Utils.HttpUtils;
import Utils.IAPI;

public class DetiaActivity extends AppCompatActivity implements HttpUtils.DownLoad{

    private ImageView img;
    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detia);
        initView();
        Intent intent = getIntent();
        if (intent!=null){
            long id = intent.getLongExtra("id",0);
            HashMap<String,String>map = new HashMap<>();
            map.put("id",id+"");
            HttpUtils.postRequest(this, IAPI.ADDRESS+IAPI.CIRCLE_DIL,map,this);
        }
    }

    private void initView() {
        img = (ImageView) findViewById(R.id.img);
        text = (TextView) findViewById(R.id.text);
    }

    @Override
    public void load(String data, int tag) {
        try {
            JSONObject object = new JSONObject(data);
            String status = object.optString("status");
            String info = object.optString("info");
            if (status.equals("200")&&info.equals("成功")){
                JSONObject ob = object.optJSONObject("data");
                String image = ob.optString("image");
                String content = ob.optString("content");
                text.setText(content);
                Picasso.with(DetiaActivity.this).load(IAPI.IMAGEPATH+image).into(img);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}

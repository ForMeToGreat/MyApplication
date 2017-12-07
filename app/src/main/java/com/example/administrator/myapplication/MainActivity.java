package com.example.administrator.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

import Bean.LoginBean;
import Utils.HttpUtils;
import Utils.IAPI;
import Utils.JsonUtils;
import Utils.SharedPreferenceUtils;

public class MainActivity extends AppCompatActivity implements HttpUtils.DownLoad{

    private EditText name;
    private EditText pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        initview();
    }

    private void initview() {
        name = (EditText) findViewById(R.id.name);
        pass = (EditText) findViewById(R.id.pass);
    }
    public void click(View view){
        switch (view.getId()){
            case R.id.denglu:
                if(TextUtils.isEmpty(name.getText().toString())){
                    Toast.makeText(this,"请输入用户名", Toast.LENGTH_SHORT).show();
                    return ;
                }
                if(TextUtils.isEmpty(pass.getText().toString())){
                    Toast.makeText(this,"请输入密码", Toast.LENGTH_SHORT).show();
                    return ;
                }
                HashMap<String,String>map = new HashMap<>();
                map.put("Username",name.getText().toString());
                map.put("Password",pass.getText().toString());
                HttpUtils.postRequest(this, IAPI.getLogin(),map,this);
                break;
            case R.id.zhuce:
                startActivity(new Intent(MainActivity.this,LoginActivity.class));
                break;
        }
    }

    @Override
    public void load(String data,int tag) {
        if(data==null){
            Toast.makeText(this,"数据请求失败", Toast.LENGTH_SHORT).show();
            return ;
        }
        JsonUtils utils = new JsonUtils();
        LoginBean bean = utils.getJsonData(data);
        /*if (!bean.getStatus().equals("200")){
            Toast.makeText(this,"输入不正确", Toast.LENGTH_SHORT).show();
            finish();
        }*/
        //判断用户是否登录成功
        if (bean.getStatus().equals("200")&&bean.getInfo().equals("成功")) {
            //将用户信息保存。
            SharedPreferenceUtils su = new SharedPreferenceUtils(this);
            su.writeData(bean);
            //跳转到主界面
            startActivity(new Intent(this,HomeActivity.class));
        }
    }
}

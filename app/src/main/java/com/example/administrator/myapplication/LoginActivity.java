package com.example.administrator.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import Utils.HttpUtils;
import Utils.IAPI;


public class LoginActivity extends AppCompatActivity implements HttpUtils.DownLoad{
    private String path = "http://192.168.190.188/Goods/app/common/register.json";
    private EditText code;
    private EditText mobile;
    private EditText name;
    private EditText gender;
    private EditText pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initview();
    }
    private void initview() {
        code = (EditText) findViewById(R.id.code);
        mobile = (EditText) findViewById(R.id.mobile);
        name = (EditText) findViewById(R.id.name);
        gender = (EditText) findViewById(R.id.gender);
        pass = (EditText) findViewById(R.id.pass);
    }
    public void click(View view){
        HashMap<String,String>map = new HashMap<>();
        if (TextUtils.isEmpty(code.getText().toString())||TextUtils.isEmpty(mobile.getText().toString())||
                TextUtils.isEmpty(name.getText().toString())||TextUtils.isEmpty(gender.getText().toString())||
                TextUtils.isEmpty(pass.getText().toString())){
            Toast.makeText(this,"你输入的信息不完整",Toast.LENGTH_SHORT).show();
        }else{
            map.put("Code",code.getText().toString());
            map.put("Mobile",mobile.getText().toString());
            map.put("Name",name.getText().toString());
            map.put("Gender",gender.getText().toString());
            map.put("Password",pass.getText().toString());
        }
        HttpUtils.postMultRequest(this, IAPI.ADDRESS+IAPI.REGISTER,map,this);
    }

    @Override
    public void load(String data,int tag) {
        try {
            JSONObject object = new JSONObject(data);
            String status = object.optString("status");
            String info = object.optString("info");
            if(status.equals("200")&&info.equals("成功")){
                Toast.makeText(this,"注册成功", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LoginActivity.this,MainActivity.class));
            }else{
                Toast.makeText(this,"注册失败", Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}

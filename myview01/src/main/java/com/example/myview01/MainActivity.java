package com.example.myview01;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import Views.MyTextView;

public class MainActivity extends AppCompatActivity {

    private MyTextView mt;
    private int[]colors = {Color.RED,Color.GREEN,Color.BLUE,Color.BLACK,Color.GRAY,Color.WHITE};
    private String []strs = {"哈哈哈哈","嘿嘿嘿额hi","呵呵呵呵","哦哦哦哦","嗯呢嗯呢","哼哼哼哼"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_main);
        mt = (MyTextView) findViewById(R.id.mytext);
    }

    public void cilck(View view) {
        int num = (int) (Math.random()*colors.length);
        switch (view.getId()){
            case R.id.color:
                mt.setColor(colors[num]);
                break;
            case R.id.size:
                mt.setSize(20*num);
                break;
            case R.id.text2:
                mt.setStr(strs[num]);
                break;
        }
    }
}

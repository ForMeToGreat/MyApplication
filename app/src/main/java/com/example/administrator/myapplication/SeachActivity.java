package com.example.administrator.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import SQLiter.DBUtils;

public class SeachActivity extends AppCompatActivity {

    private ArrayList<String>lists = new ArrayList<>();
    private EditText text;
    private ListView listview;
    private ArrayAdapter<String> adapter;
    private DBUtils utils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seach);
        utils = new DBUtils(this);
        initview();
        listChanged();
    }

    private void initview() {
        ImageView back = (ImageView) findViewById(R.id.back);
        text = (EditText) findViewById(R.id.text1);
        ImageView seach = (ImageView) findViewById(R.id.seach);
        listview = (ListView) findViewById(R.id.listview1);
        TextView clear = (TextView) findViewById(R.id.clear);
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,lists);
        listview.setAdapter(adapter);
        //给listview添加过滤器
        listview.setTextFilterEnabled(true);
        //EditText可以实现输入文本时动作的改变
        text.addTextChangedListener(new TextWatcher() {
            /**
             * @param s :在文本改变之前有哪些字符串
             * @param start  ：开始位置
             * @param count  ：字符数量
             * @param after  ：之后
             */
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //listView.setFilterText(String.valueOf(s));
                adapter.getFilter().filter(String.valueOf(s));
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(s)){
                    listview.clearTextFilter();
                }
            }
        });
        seach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = text.getText().toString();
                utils.writeData(str);
                listChanged();
                Intent intent = new Intent(SeachActivity.this,SeachDetiaActivity.class);
                intent.putExtra("title",str);
                startActivity(intent);
            }
        });
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                utils.clearData();
                listChanged();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void listChanged() {
        lists.clear();
        ArrayList<String> dbList = utils.getDbList();
        lists.addAll(dbList);
        adapter.notifyDataSetChanged();
    }

}
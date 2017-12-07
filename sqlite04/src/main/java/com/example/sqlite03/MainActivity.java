package com.example.sqlite03;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import db.DBHelper;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private ArrayList<String>list = new ArrayList<>();
    private SQLiteDatabase db;
    private ArrayAdapter<String> adapter;
    private EditText name;
    private EditText age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        db = new DBHelper(this).getWritableDatabase();
    }

    private void initView() {
        name = (EditText) findViewById(R.id.inputName);
        age = (EditText) findViewById(R.id.inputAge);
        ListView listview = (ListView) findViewById(R.id.listview);
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list);
        listview.setAdapter(adapter);
        Button insert = (Button) findViewById(R.id.insert);
        Button query = (Button) findViewById(R.id.query);
        Button update = (Button) findViewById(R.id.update);
        Button delete = (Button) findViewById(R.id.delete);
        insert.setOnClickListener(this);
        query.setOnClickListener(this);
        update.setOnClickListener(this);
        delete.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.insert:
                insertData();
                break;
            case R.id.query:
                queryData();
                break;
            case R.id.update:
                UpdateData();
                break;
            case R.id.delete:
                deleteData();
                break;
        }
    }

    private void deleteData() {
        db.delete("user","age > ?",new String[]{"20"});
    }

    private void UpdateData() {
        ContentValues values = new ContentValues();
        values.put("name",name.getText().toString());
        values.put("age",21);
        db.update("user",values,"age < ?",new String[]{"20"});
    }

    private void queryData() {
        Cursor cursor = db.query("user",new String[]{"name","age"}, null, null, null, null, null);
        if (cursor!=null){
            list.clear();
            while (cursor.moveToNext()){
                String sname = cursor.getString(cursor.getColumnIndex("name"));
                int sage = cursor.getInt(cursor.getColumnIndex("age"));
                list.add("姓名: "+sname+"  年龄: "+sage);
            }
        }
        if (list.size()>0){
            adapter.notifyDataSetChanged();
        }

    }

    private void insertData() {
        if (TextUtils.isEmpty(name.getText().toString())&&TextUtils.isEmpty(age.getText().toString())){
            return;
        }else{
            String iname = name.getText().toString();
            String iage = age.getText().toString();
            ContentValues values = new ContentValues();
            values.put("name",iname);
            values.put("age",Integer.parseInt(iage));
            db.insert("user",null,values);
        }
    }
}

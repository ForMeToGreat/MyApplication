package com.example.sqlite03;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText inputName;
    private EditText inputAge;
    private SQLiteDatabase db;
    private ListView listview;
    private ArrayList<String>list = new ArrayList<>();
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        db = new DBHelper(this).getWritableDatabase();
    }

    private void initView() {
        inputName = (EditText) findViewById(R.id.inputName);
        inputAge = (EditText) findViewById(R.id.inputAge);
        listview = (ListView) findViewById(R.id.listview);
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list);
        listview.setAdapter(adapter);
    }
    public void click(View view) {
        switch (view.getId()){
            case R.id.insert:
                insertData();
                break;
            case R.id.query:
                queryData();
                break;
            case R.id.update:
                updateData();
                break;
            case R.id.delete:
                deleteData();
                break;
        }
    }

    private void deleteData() {
        String sql = "delete from person where age >= 17";
        db.execSQL(sql);
    }

    private void updateData() {
        String sql = "update person set name = '大傻逼' where age = '16'";
        db.execSQL(sql);
        toastData("修改成功");
    }

    private void queryData() {
        String sql = "select * from person";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor!=null){
            list.clear();
            while (cursor.moveToNext()){
                String name = cursor.getString(cursor.getColumnIndex("name"));
                int age = cursor.getInt(cursor.getColumnIndex("age"));
                list.add("姓名: "+name+"  年龄: "+age);
                adapter.notifyDataSetChanged();
            }
        }
    }
    private void insertData() {
        if (TextUtils.isEmpty(inputName.getText())||TextUtils.isEmpty(inputAge.getText())){
            toastData("输入的数据不完整");
        }
        String name = inputName.getText().toString();
        int age = Integer.parseInt(inputAge.getText().toString());
        String sql = "insert into person(name,age)values('"+name+"',"+age+")";
        db.execSQL(sql);
        toastData("插入数据成功");
    }
    public void toastData(String str){
        Toast.makeText(this,str,Toast.LENGTH_SHORT).show();
    }

}

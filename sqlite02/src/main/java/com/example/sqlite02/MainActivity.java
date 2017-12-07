package com.example.sqlite02;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private SQLiteDatabase db;
    private EditText inputName;
    private EditText inputAge;
    private ListView listview;
    private ArrayList<String> list = new ArrayList<>();
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DBHelper(this).getWritableDatabase();
        initView();
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
            case R.id.input:
                String name = inputName.getText().toString();
                int age = Integer.parseInt(inputAge.getText().toString());
                String isql = "insert into person(name,age)values('"+name+"',"+age+")";
                db.execSQL(isql);
                break;
            case R.id.query:
                initQuery();
                break;
            case R.id.update:
                String usql = "update person set age ="+16+" where name = '哈哈'";
                db.execSQL(usql);
                break;
            case R.id.delete:
                String dsql = "delete from person where age >= 16";
                db.execSQL(dsql);
                break;
        }
    }

    private void initQuery() {
        String qsql = "select * from person";
        Cursor cursor = db.rawQuery(qsql, null);
        if (cursor!=null){
            list.clear();
            while(cursor.moveToNext()){
                String name = cursor.getString(cursor.getColumnIndex("name"));
                int age = cursor.getInt(cursor.getColumnIndex("age"));
                list.add("姓名: "+name+"  年龄:  "+age);
            }
            if (list.size()>0){
                adapter.notifyDataSetChanged();
            }
        }
    }
}

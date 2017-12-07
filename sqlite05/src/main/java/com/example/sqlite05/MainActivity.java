package com.example.sqlite05;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }
    private void initView() {
        Button save = (Button) findViewById(R.id.save);
        Button get = (Button) findViewById(R.id.get);
        save.setOnClickListener(this);
        get.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.save:
                save();
                break;
            case R.id.get:
                get();
                break;
        }
    }
    private void save() {
        SQLiteDatabase db = new DbHelper(this).getWritableDatabase();
        Student stu = new Student("吉凡凡",18,"未知");
        db.beginTransaction();
        String sql = "insert into person(stu)values(?)";
        //将学生对象转换为Object[]对象
        //创建内存流
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            //将内存流转换为对象流
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            //将对象写入到流中
            oos.writeObject(stu);
            byte[]by = bos.toByteArray();
            oos.flush();
            db.execSQL(sql,new Object[]{ by });
            oos.close();
            db.setTransactionSuccessful();
            db.endTransaction();
            db.close();
            Toast.makeText(this,"存入成功", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void get() {
        SQLiteDatabase db = new DbHelper(this).getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from person", null);
        if (cursor!=null){
            while (cursor.moveToNext()){
                byte[]by = cursor.getBlob(cursor.getColumnIndex("stu"));
                ByteArrayInputStream bis = new ByteArrayInputStream(by);
                try {
                    ObjectInputStream ois = new ObjectInputStream(bis);
                    Student stu = (Student) ois.readObject();
                    ois.close();
                    bis.close();
                    Toast.makeText(this,stu.toString(),Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

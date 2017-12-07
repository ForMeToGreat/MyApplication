package com.example.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2017/10/20.
 */

public class DbHelper extends SQLiteOpenHelper{
    /**
     *
     * @param context  ：上下文
     * @param name     ： 数据库的名字
     * @param factory  ：数据加工厂
     * @param version  ：版本
     */

    public DbHelper(Context context) {
        super(context, "user_db", null, 1);
    }

    //用来创建数据库的
    @Override
    public void onCreate(SQLiteDatabase db) {
        //在数据库中创建数据表
        String sql = "create table if not exists user(" + "_id integer primary key autoincrement," + "str text not null)";
        //要通过db对象去执行sql语句
        db.execSQL(sql);

    }
    //根据数据库的版本去跟新数据库
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

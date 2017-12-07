package com.example.sqlite05;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2017/11/3.
 */

public class DbHelper extends SQLiteOpenHelper{
    public DbHelper(Context context) {
        super(context,"user",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table if not exists person(_id integer primary key autoincrement," +
                "stu text )";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

package com.example.sqlite02;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2017/10/23.
 */

public class DBHelper extends SQLiteOpenHelper{

    public DBHelper(Context context) {
        super(context,"db_user",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table if not exists person (_id integer primary key autoincrement," +
                "name text,age integer)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

package SQLiter;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/10/24.
 */

public class DBUtils {

    private final SQLiteDatabase db;

    public DBUtils(Context context) {
        db = new DBhelper(context).getWritableDatabase();
    }
    public void writeData(String str){
        ArrayList<String>dblist = getDbList();
        if (dblist.contains(str)){
            return;
        }
        String sql = "insert into user(str)values('"+str+"')";
        db.execSQL(sql);
    }
    public ArrayList<String> getDbList(){
        ArrayList<String> list = new ArrayList<>();
        String sql = "select * from user";
        Cursor cursor = db.rawQuery(sql,null);
        if (cursor!=null){
            list.clear();
            while (cursor.moveToNext()){
                String ss = cursor.getString(cursor.getColumnIndex("str"));
                list.add(ss);
            }
        }
        return list;
    }
    public void clearData(){
        String sql = "delete from user where _id>0";
        db.execSQL(sql);
    }
}

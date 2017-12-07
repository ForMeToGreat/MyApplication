package SQLiter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2017/10/24.
 */

public class DBhelper extends SQLiteOpenHelper{
    public DBhelper(Context context) {
        super(context,"user_db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table if not exists user(" +
                "_id integer primary key autoincrement," +
                "str text not null)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

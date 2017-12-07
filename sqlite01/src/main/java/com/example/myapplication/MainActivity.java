package com.example.myapplication;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private SQLiteDatabase db;
    private ArrayList<String>list = new ArrayList<>();
    private ListView listview;
    private ArrayAdapter<String> adapter;
    private SearchView searchview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //创建数据库
        DbHelper helper = new DbHelper(this);
        db = helper.getWritableDatabase();
        initView();
    }

    private void initView() {
        editText = (EditText) findViewById(R.id.edite);
        listview = (ListView) findViewById(R.id.listview);
        searchview = (SearchView) findViewById(R.id.search);
        //设置listview能够过滤文本
        listview.setTextFilterEnabled(true);
        //设置查询的提示语
        searchview.setQueryHint("请输入关键字");
        //是否以小图标显示
        searchview.setIconifiedByDefault(true);
        //设置监听事件
        searchview.setOnQueryTextListener(new SearchListener());
        //搜索的按钮是否显示
        searchview.setSubmitButtonEnabled(true);
        //listview的适配器
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView textview = (TextView) view;
                CharSequence text = textview.getText();
                Toast.makeText(MainActivity.this,text, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onClick(View view) {
        String str = null;
        switch (view.getId()){
            case R.id.insert:
                if (!TextUtils.isEmpty(editText.getText())){
                    str = editText.getText().toString();
                    //像数据库中写入数据
                    String sql = "insert into user(str) values('"+str+"')";
                    db.execSQL(sql);
                }
                break;
            case R.id.query:
                query();
                break;
        }
    }
    public void query(){
        //查询数据库中的所有的数据
        String sql1 = "select * from user";
        //执行查询操作，返回游标对象
        Cursor cursor = db.rawQuery(sql1,null);
        if (cursor!=null){
            list.clear();
            //指向下一个元素，cursor.moveToNext()，当下一个元素不为空则返回true，否则返回false
            while (cursor.moveToNext()){
                //cursot.getString();取出一个String类型的字符串
                //cursor.getColumnIndex：取出列所对应的名字
                String str = cursor.getString(cursor.getColumnIndex("str"));
                //int _id = cursor.getInt(cursor.getColumnIndex("_id"));
                //Log.i("tag","===============str："+str+"  _id："+_id);
                list.add(str);
                }
            }
        if (list.size()>0){
            adapter.notifyDataSetChanged();
        }
    }
    class SearchListener implements SearchView.OnQueryTextListener{
        //点击搜索时查询的文本
        @Override
        public boolean onQueryTextSubmit(String query) {
            query();
            return true;
        }

        //当向控件上输入文本时的状态
        @Override
        public boolean onQueryTextChange(String newText) {
            adapter.getFilter().filter(newText);
            if (TextUtils.isEmpty(newText)){
                listview.clearTextFilter();
            }
            return true;
        }
    }
}

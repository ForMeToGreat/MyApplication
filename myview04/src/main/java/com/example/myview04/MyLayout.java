package com.example.myview04;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/11/10.
 */

public class MyLayout extends LinearLayout{

    private View view;
    private ArrayList<String>list = new ArrayList<>();
    private ArrayAdapter<String> adapter;
    private ImageView image;
    private ListView lview;

    public MyLayout(Context context) {
        this(context,null);
    }

    public MyLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        view = LayoutInflater.from(context).inflate(R.layout.my_layout,null);
        image = view.findViewById(R.id.imageview);
        lview = view.findViewById(R.id.listview);
        adapter = new ArrayAdapter<String>(context,android.R.layout.simple_list_item_1,list);
        lview.setAdapter(adapter);
        this.addView(view);
    }
    public void setListData(int num){
        list.clear();
        for (int i=0;i<num;i++){
            list.add("我是网络的数据"+i);
        }
        adapter.notifyDataSetChanged();
    }
    public void setImageDrawable(Bitmap bitmap){
        image.setImageBitmap(bitmap);
    }
}

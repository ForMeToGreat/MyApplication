package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.administrator.myapplication.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import Bean.Pictures;


/**
 * Created by 黑夜之火 on 2017/11/2.
 */

public class GridViewAdapter extends BaseAdapter {
    private ArrayList<Pictures>pictures = new ArrayList<>();
    private Context context;

    public GridViewAdapter(ArrayList<Pictures> pictures, Context context) {
        this.pictures = pictures;
        this.context = context;
    }

    @Override
    public int getCount() {
        return pictures.size()+1;
    }

    @Override
    public Pictures getItem(int position) {
        return pictures.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.issue_griditem,null);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        }else{
            vh = (ViewHolder) convertView.getTag();
        }
        if (position < pictures.size()){
            Pictures pic = getItem(position);
            Picasso.with(context).load("file://"+pic.getPath()).into(vh.imageView);
        }else{
            vh.imageView.setImageResource(R.mipmap.gridview_addpic);
        }

        return convertView;
    }
    class ViewHolder{
        ImageView imageView;
        public ViewHolder(View view){
            imageView = view.findViewById(R.id.imageview);
        }
    }
}

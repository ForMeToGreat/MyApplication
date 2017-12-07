package fragment;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.SelectingActivity;

import java.util.ArrayList;
import java.util.HashMap;

import Bean.Pictures;
import Utils.FileUtils;
import Utils.HttpUtils;
import Utils.IAPI;
import Utils.SharedPreferenceUtils;
import adapter.GridViewAdapter;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class TwoFragment extends Fragment implements HttpUtils.DownLoad{

    private ArrayList<Pictures>pictures = new ArrayList<>();
    private View view;
    private int width;
    private int height;
    private EditText two_title;
    private EditText two_description;
    private GridView two_grid;
    private EditText two_price;
    private EditText two_phone;
    private EditText two_qq;
    private GridViewAdapter adapter;
    private PopupWindow pw;
    private String filePath;

    public TwoFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_two, container, false);
        DisplayMetrics metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        width = metrics.widthPixels;
        height = metrics.heightPixels/4;
        initView();
        return view;
    }
    private void initView() {
        two_title = view.findViewById(R.id.two_title);
        two_description = view.findViewById(R.id.two_description);
        two_grid = view.findViewById(R.id.two_grid);
        adapter = new GridViewAdapter(pictures,getContext());
        two_grid.setAdapter(adapter);
        gridviewListener();
        two_price = view.findViewById(R.id.two_price);
        two_phone = view.findViewById(R.id.two_phone);
        two_qq = view.findViewById(R.id.two_qq);
        Button two_fabu = view.findViewById(R.id.two_fabu);
        two_fabu.setOnClickListener(new MyOnClickListener());
    }

    @Override
    public void load(String data, int tag) {
        Toast.makeText(getActivity(),"上传成功", Toast.LENGTH_SHORT).show();
    }

    class MyOnClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.openCarame:
                    openCrema();
                    break;
                case R.id.openPhoto:
                    openPhoto();
                case R.id.quxiao:
                    pw.dismiss();
                    break;
                case R.id.two_fabu:
                    fabu();
                    break;
            }
        }
    }
    private void gridviewListener() {
        two_grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position==pictures.size()){
                    popupWindow();
                }
            }
        });
    }

    private void popupWindow() {
        pw = new PopupWindow(getActivity());
        View view1 = LayoutInflater.from(getActivity()).inflate(R.layout.popup_item,null);
        Button openCrema = view1.findViewById(R.id.openCarame);
        Button openphoto = view1.findViewById(R.id.openPhoto);
        Button quxiao = view1.findViewById(R.id.quxiao);
        openCrema.setOnClickListener(new MyOnClickListener());
        openphoto.setOnClickListener(new MyOnClickListener());
        quxiao.setOnClickListener(new MyOnClickListener());
        pw.setContentView(view1);
        pw.setWidth(width);
        pw.setHeight(height);
        pw.setOutsideTouchable(true);
        pw.showAtLocation((View) view.getParent(), Gravity.BOTTOM,10,10);
    }

    private void fabu() {
        HashMap<String,String>map = new HashMap<>();
        map.put("title",two_title.getText().toString());
        map.put("description",two_description.getText().toString());
        map.put("mobile",two_phone.getText().toString());
        map.put("price",two_price.getText().toString());
        map.put("qq",two_qq.getText().toString());
        map.put("token",new SharedPreferenceUtils(getActivity()).getToken());
        if (pictures.size()>0){
            HttpUtils.postHttpFileRequest(getActivity(), IAPI.ADDRESS+IAPI.ISSUE,map,"photo",pictures,this);
        }
    }
    private void openPhoto() {
        Intent intent = new Intent(getActivity(), SelectingActivity.class);
        startActivityForResult(intent, 0x113);
    }

    private void openCrema() {
        Intent intent = new Intent();
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 0x111);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (pw!=null&&pw.isShowing()){
            pw.dismiss();
        }
        if (data != null && resultCode == RESULT_OK){
            if (requestCode==0x111){
                resultCarame(data);
            }else if (requestCode==0x113){
                ArrayList<Pictures> pics = data.getParcelableArrayListExtra("pics");
                //Log.i("tag","====>"+pics.toString());
                pictures.addAll(pics);
                adapter.notifyDataSetChanged();
            }
        }
    }

    private void resultCarame(Intent data) {
        Bitmap bitmap = null;
        Uri uri = data.getData();
        if (uri != null) {
            bitmap = BitmapFactory.decodeFile(uri.getPath());
            filePath = uri.getPath();
        }
        if (bitmap == null) {
            bitmap = (Bitmap) data.getExtras().get("data");
            //创建工具类，向文件中写入图片的工具
            FileUtils utils = new FileUtils(getActivity());
            filePath = utils.writeFile(bitmap);
        }
        pictures.add(new Pictures(filePath, false));
        adapter.notifyDataSetChanged();
    }
}

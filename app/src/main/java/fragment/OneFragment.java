package fragment;


import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.example.administrator.myapplication.GoodsDetiaActivity;
import com.example.administrator.myapplication.HomeActivity;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.SeachActivity;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

import Bean.OneBean;
import Bean.PagerBean;
import Utils.HttpUtils;
import Utils.IAPI;
import Utils.JsonUtils;
import Utils.SharedPreferenceUtils;
import adapter.OneAdapter;
import adapter.PagerAdapter01;
import view.MyListView;

/**
 * A simple {@link Fragment} subclass.
 */
public class OneFragment extends Fragment implements HttpUtils.DownLoad{

    private View view;
    private int index = 1;
    private ArrayList<OneBean.DataBean.ListBean>list = new ArrayList<>();
    private HttpUtils utils;
    private OneAdapter adapter;
    private PullToRefreshScrollView pScrollView;
    private MyListView listview;
    private ViewPager viewpager;
    private ArrayList<PagerBean.Data>datas = new ArrayList<>();
    private ArrayList<ImageView>views= new ArrayList<>();
    private ArrayList<ImageView> dots = new ArrayList<>();
    private PagerAdapter01 adapter1;
    private android.os.Handler handler = new android.os.Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0x114){
                int currentItem = viewpager.getCurrentItem();
                currentItem++;
                sendEmptyMessageDelayed(0x114, 1000);
                viewpager.setCurrentItem(currentItem);
            }
        }
    };
    private LinearLayout layout;

    public OneFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_one, container, false);
        initView();
        initListListener();
        listRefreshAndLoad();
        initPagerListener();
        return view;
    }
    private void initListListener() {
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                OneBean.DataBean.ListBean listBean = list.get(position);
                Intent intent = new Intent(getContext(),GoodsDetiaActivity.class);
                intent.putExtra("id",listBean.getId());
                startActivity(intent);
            }
        });
    }

    private void initPagerListener() {
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for(int i=0;i<dots.size();i++){
                    if (i==position%dots.size()){
                        dots.get(i).setImageResource(R.mipmap.icon_like_red_32);
                    }else{
                        dots.get(i).setImageResource(R.mipmap.icon_like_white_34);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void listRefreshAndLoad() {
        pScrollView.setMode(PullToRefreshBase.Mode.BOTH);
        pScrollView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                index++;
                utils.postRequest(getContext(), IAPI.ADDRESS + IAPI.ISSUE, getMap(),OneFragment.this);

            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                index++;
                utils.postRequest(getContext(), IAPI.ADDRESS + IAPI.ISSUE, getMap(),OneFragment.this);
            }
        });
    }
    private void initView() {
        viewpager = (ViewPager) view.findViewById(R.id.viewpager);
        layout = (LinearLayout) view.findViewById(R.id.layout_dot);
        pScrollView = (PullToRefreshScrollView) view.findViewById(R.id.pScrollView);
        listview = (MyListView) view.findViewById(R.id.listview);
        ImageView fabu = (ImageView) view.findViewById(R.id.fabu);
        ImageView sousuo = (ImageView) view.findViewById(R.id.sousuo);
        fabu.setOnClickListener(new MyClickListener());
        sousuo.setOnClickListener(new MyClickListener());
        adapter1 = new PagerAdapter01(views,datas,getContext());
        viewpager.setAdapter(adapter1);
        adapter = new OneAdapter(getContext(),list);
        listview.setAdapter(adapter);
        HttpUtils.postRequest(getContext(),IAPI.ADDRESS + IAPI.ISSUE, getMap(),this);
        HttpUtils.getRequest(getContext(),IAPI.ADDRESS + IAPI.CIRCLE_LIST,this);

    }
    private HashMap<String,String> getMap() {
        HashMap<String,String>map = new HashMap<>();
        map.put("token",new SharedPreferenceUtils(getContext()).getToken());
        map.put("curPage",String.valueOf(index));
        return map;
    }

    @Override
    public void load(String data,int tag) {
        if (tag == 0x111) {
            postScroll(data);
        } else if (tag == 0x113) {
            getListview(data);
        }
    }
    private void getListview(String data) {
        //Log.i("tag","=====viewpager=======>"+data);
       if (data==null&&data.equals("")){
            return ;
        }
        PagerBean pager = new JsonUtils().getPagerData(data);
        if (pager!=null&&pager.getStatus().equals("200")&&pager.getInfo().equals("成功")){
            ArrayList<PagerBean.Data>datalist = pager.getData();
            datas.clear();
            datas.addAll(datalist);  
            if(datalist!=null&&datalist.size()>0)
                for (int i = 0; i < datalist.size(); i++) {
                    PagerBean.Data pd = datalist.get(i);
                    ImageView imageview = new ImageView(getContext());
                    imageview.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 200));
                    imageview.setScaleType(ImageView.ScaleType.FIT_XY);
                    Picasso.with(getContext()).load("http://192.168.190.188/Goods/uploads/"+pd.getImage()).into(imageview);
                    views.add(imageview);

                    ImageView dot = new ImageView(getContext());
                    dot.setLayoutParams(new ViewGroup.LayoutParams(20, 20));
                    if (i==0){
                        dot.setImageResource(R.mipmap.icon_like_red_32);
                    }else{
                        dot.setImageResource(R.mipmap.icon_like_white_34);
                    }
                    dots.add(dot);
                    layout.addView(dot);
                }
                if (views.size()>0){
                    adapter1.notifyDataSetChanged();
                    pScrollView.scrollTo(0,0);
                    handler.sendEmptyMessage(0x114);
                }
        }
    }

    private void postScroll(String data) {
        Toast.makeText(getActivity(),"加载完成",Toast.LENGTH_SHORT).show();
        pScrollView.onRefreshComplete();
        if (data==null&&data.equals("")){
            return ;
        }
        //Log.i("tag","================>"+data);
        OneBean bean = new JsonUtils().getOneBean(data);
        if(bean!=null&&bean.getStatus().equals("200")&&bean.getInfo().equals("成功")){
            list.addAll(bean.getData().getList());
            adapter.notifyDataSetChanged();
        }
    }
    class MyClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.fabu:
                    ((HomeActivity)getContext()).setCurrentViewPager();
                    break;
                case R.id.sousuo:
                    Intent intent = new Intent(getActivity(), SeachActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    }
}


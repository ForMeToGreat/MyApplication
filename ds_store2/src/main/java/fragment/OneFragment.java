package fragment;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ds_store2.DingZhiActivity;
import com.example.ds_store2.MoreActivity;
import com.example.ds_store2.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

import Bean.ShouyeBean;
import Utils.HttpUtils;
import Utils.JsonUtils;
import adapter.KeChengAdapter;
import adapter.MingShiAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class OneFragment extends Fragment implements HttpUtils.LoadData {

    private String path = "http://api.dameiketang.com/manager.php?m=Admin&c=Threevesion&a=IndexPageData";
    private View view;
    private ArrayList<ImageView> imgs01 = new ArrayList<>();
    private ArrayList<ImageView> imgs02 = new ArrayList<>();
    private ViewPager pager01;
    private ViewPager pager02;
    private MingShiAdapter mingadapter;
    private KeChengAdapter keadapter;
    private ArrayList<ShouyeBean.EBean> lists01 = new ArrayList<>();
    private ArrayList<ShouyeBean.LBean> lists02 = new ArrayList<>();
    private ArrayList<ShouyeBean.CBean> minglist = new ArrayList<>();
    private ArrayList<ShouyeBean.HBean> kelist = new ArrayList<>();
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0x111) {
                int index = pager01.getCurrentItem();
                index++;
                handler.sendEmptyMessageDelayed(0x111, 2000);
                pager01.setCurrentItem(index);
            } else if (msg.what == 0x112) {
                int index = pager02.getCurrentItem();
                index++;
                handler.sendEmptyMessageDelayed(0x112, 2000);
                pager02.setCurrentItem(index);
            }
        }
    };

    public OneFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_one, container, false);
        HttpUtils utils = new HttpUtils(getContext(), this);
        HashMap<String, String> map = new HashMap<>();
        map.put("id", "6894681b-ad8b-47e4-9f17-1cf07324464c");
        utils.postRequest(path, map);
        initView();
        return view;
    }

    private void initView() {
        pager01 = view.findViewById(R.id.pager01);
        pager02 = view.findViewById(R.id.pager02);
        GridView mingshi = view.findViewById(R.id.mingshi);
        GridView kecheng = view.findViewById(R.id.kecheng);
        mingadapter = new MingShiAdapter(getContext(), minglist);
        mingshi.setAdapter(mingadapter);
        keadapter = new KeChengAdapter(getContext(), kelist);
        kecheng.setAdapter(keadapter);
        TextView more = view.findViewById(R.id.more);
        more.setOnClickListener(new MyOnclick());
        TextView dingzhi = view.findViewById(R.id.dingzhi);
        dingzhi.setOnClickListener(new MyOnclick());
    }

    @Override
    public void load(String data) {
        if (data != null) {
            ShouyeBean bean = new JsonUtils().getShouData(data);
            if (bean != null) {
                ArrayList<ShouyeBean.EBean> beans01 = (ArrayList<ShouyeBean.EBean>) bean.getE();
                lists01.addAll(beans01);
                for (int i = 0; i < lists01.size(); i++) {
                    ImageView img = new ImageView(getContext());
                    Picasso.with(getContext()).load(lists01.get(i).getImgurl()).into(img);
                    imgs01.add(img);
                }
                MyPageradapter adapter = new MyPageradapter(imgs01);
                pager01.setAdapter(adapter);
                handler.sendEmptyMessage(0x111);

                //
                ArrayList<ShouyeBean.LBean> beans02 = (ArrayList<ShouyeBean.LBean>) bean.getL();
                lists02.addAll(beans02);
                for (int i = 0; i < lists02.size(); i++) {
                    ImageView img = new ImageView(getContext());
                    Picasso.with(getContext()).load(lists02.get(i).getImg()).into(img);
                    imgs02.add(img);
                }
                MyPageradapter adapter02 = new MyPageradapter(imgs02);
                pager02.setAdapter(adapter02);
                handler.sendEmptyMessage(0x112);

                ArrayList<ShouyeBean.CBean> cbean = (ArrayList<ShouyeBean.CBean>) bean.getC();
                for (int i = 0; i < 4; i++) {
                    minglist.add(cbean.get(i));
                }
                mingadapter.notifyDataSetChanged();

                ArrayList<ShouyeBean.HBean> hbean = (ArrayList<ShouyeBean.HBean>) bean.getH();
                kelist.addAll(hbean);
                keadapter.notifyDataSetChanged();
            }
        }
    }

    class MyPageradapter extends PagerAdapter {
        private ArrayList<ImageView> imgs;

        public MyPageradapter(ArrayList<ImageView> imgs) {
            this.imgs = imgs;
        }

        @Override
        public int getCount() {
            if (imgs.size() > 0) {
                return Integer.MAX_VALUE;
            }
            return 0;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            position %= imgs.size();
            ViewGroup group = (ViewGroup) imgs.get(position).getParent();
            if (group != null) {
                group.removeView(imgs.get(position));
            }
            container.addView(imgs.get(position));
            return imgs.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            //super.destroyItem(container, position, object);
        }
    }

    class MyOnclick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.more:
                    startActivity(new Intent(getContext(), MoreActivity.class));
                    break;
                case R.id.dingzhi:
                    startActivity(new Intent(getContext(), DingZhiActivity.class));
                    break;
            }
        }
    }
}
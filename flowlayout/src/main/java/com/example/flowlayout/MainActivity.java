package com.example.flowlayout;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cn.lankton.flowlayout.FlowLayout;

public class MainActivity extends AppCompatActivity {

    private FlowLayout flowLayout;
    private String strs[] = {"浪奔","浪流","万李涛涛","江水永不休","成功","失败","万里分不清","爱恨情仇","爱你恨你","文君之下","十大将","一发不收","庄先湾","赚钱玩","始终逃不过","池中争斗",
            "把你的心","我的心","串一串","穿一组邢云超","串一颗同心圆","让我们","把","对未来的","互换","和青春","做个伴","向天空大声的呼喊","说声我想你","向那","流浪的白云","说声我爱你","让那天空听得见","让那白云","看得见",
            "谁也擦不掉","我们","许下的诺言"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        flowLayout = (FlowLayout) findViewById(R.id.flowlayout);
        for (int i=0;i<strs.length;i++){
            TextView tv = new TextView(this);
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            ViewGroup.MarginLayoutParams group = new ViewGroup.MarginLayoutParams(params);
            group.leftMargin = 20;
            group.rightMargin = 20;
            group.bottomMargin = 10;
            group.topMargin = 10;
            tv.setGravity(Gravity.CENTER);
            tv.setText(strs[i]);
            tv.setTextSize(20);
            tv.setBackground(getResources().getDrawable(R.drawable.text_bolder));
            tv.setLayoutParams(group);
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    
                }
            });
            flowLayout.addView(tv);
        }
    }
}

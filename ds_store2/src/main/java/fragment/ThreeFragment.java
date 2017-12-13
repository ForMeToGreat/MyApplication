package fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ds_store2.DLZCActivity;
import com.example.ds_store2.DingZhiActivity;
import com.example.ds_store2.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ThreeFragment extends Fragment {

    private View view;

    public ThreeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_three, container, false);
        TextView dlzc = view.findViewById(R.id.dlzc);
        LinearLayout lin01 = view.findViewById(R.id.lin01);
        LinearLayout lin02 = view.findViewById(R.id.lin02);
        LinearLayout lin03 = view.findViewById(R.id.lin03);
        LinearLayout lin04 = view.findViewById(R.id.lin04);
        LinearLayout lin05 = view.findViewById(R.id.lin05);
        LinearLayout lin06 = view.findViewById(R.id.lin06);
        dlzc.setOnClickListener(new MyOnClick());
        lin01.setOnClickListener(new MyOnClick());
        lin02.setOnClickListener(new MyOnClick());
        lin03.setOnClickListener(new MyOnClick());
        lin04.setOnClickListener(new MyOnClick());
        lin05.setOnClickListener(new MyOnClick());
        lin06.setOnClickListener(new MyOnClick());
        return view;
    }
    class MyOnClick implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.dlzc:
                    startActivity(new Intent(getContext(),DLZCActivity.class));
                    break;
                case R.id.lin01:
                    startActivity(new Intent(getActivity(), DingZhiActivity.class));
                    break;
                case R.id.lin02:
                    startActivity(new Intent(getActivity(), DingZhiActivity.class));
                    break;
                case R.id.lin03:
                    startActivity(new Intent(getActivity(), DingZhiActivity.class));
                    break;
                case R.id.lin04:
                    startActivity(new Intent(getActivity(), DingZhiActivity.class));
                    break;
                case R.id.lin05:
                    startActivity(new Intent(getActivity(), DingZhiActivity.class));
                    break;
                case R.id.lin06:
                    startActivity(new Intent(getActivity(), DingZhiActivity.class));
                    break;
            }
        }
    }
}

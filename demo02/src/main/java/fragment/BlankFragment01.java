package fragment;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.demo02.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment01 extends android.support.v4.app.Fragment {


    public BlankFragment01() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank_fragment01, container, false);
    }

}

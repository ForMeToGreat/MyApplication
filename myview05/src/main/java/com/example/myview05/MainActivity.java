package com.example.myview05;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView mainlist = (ListView) findViewById(R.id.mainlist);
        MainAdapter adapter = new MainAdapter(this);
        mainlist.setAdapter(adapter);
    }
}

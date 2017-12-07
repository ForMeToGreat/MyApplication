package com.example.drawableanimation;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView img;
    private AnimationDrawable ad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img = (ImageView) findViewById(R.id.image1);
        ad = (AnimationDrawable) img.getDrawable();
        ad.stop();
    }

    public void start(View view) {
        ad.start();
    }

    public void stop(View view) {
        ad.stop();
    }
}

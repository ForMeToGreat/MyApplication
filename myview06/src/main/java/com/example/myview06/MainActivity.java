package com.example.myview06;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;

import view.CircleView;

public class MainActivity extends AppCompatActivity {

    private CircleView circle;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }
    private void initView() {
        circle = (CircleView) findViewById(R.id.circle);
        imageView = (ImageView) findViewById(R.id.circle111);
        Button btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_PICK);
                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,"image/*");
                startActivityForResult(intent,0x111);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data!=null && resultCode==RESULT_OK){
            if (requestCode==0x111){
                ContentResolver resolver = getContentResolver();
                String [] project = {MediaStore.Images.Media.DATA};
                Cursor cursor = resolver.query(data.getData(),project,null,null,null,null);
                cursor.moveToNext();
                String filePath = cursor.getString(cursor.getColumnIndex(project[0]));
                File file  = new File(filePath);
                Bitmap bitmap = BitmapFactory.decodeFile(file.getPath());
                imageView.setImageBitmap(bitmap);
                circle.setImageBitmap(bitmap);
            }
        }
    }
}
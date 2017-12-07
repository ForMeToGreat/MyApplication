package Utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import Bean.Pictures;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


/**
 * Created by Administrator on 2017/10/11.
 */

public class HttpUtils {
    private static final Handler handler = new Handler();
    private static OkHttpClient instance;
    //创建单利模式，对对象加锁
    public static OkHttpClient getInstance(Context context){
        if (instance==null){
            synchronized (HttpUtils.class){
                if (instance==null){
                    instance = new OkHttpClient.Builder()
                            .connectTimeout(5, TimeUnit.SECONDS)
                            .readTimeout(5,TimeUnit.SECONDS)
                            .build();
                }
            }
        }

        return instance;
    }

    //网络是否连接
    public static boolean isConnected(Context context){
        //获取网络管理者对象
        ConnectivityManager  manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        //通过管理者对象，去获取网络信息
        NetworkInfo info = manager.getActiveNetworkInfo();
        //判断网络是否连接成功
        if(info.getState().equals(NetworkInfo.State.CONNECTED)){
            return true;
        }
        return false;

    }
    //post请求，向服务器端加载数据
    public static void postRequest(Context context, String path, HashMap<String,String>map, final DownLoad load){
        if (!isConnected(context)){
            Log.i("tag","=========网络连接失败========");
            return;
        }
        OkHttpClient client = getInstance(context);
        //设置参数，向服务器端提交参数
        FormBody.Builder builder = new FormBody.Builder();
        //设置向服务器端提交的参数
        for(String  key:map.keySet()){
            builder.add(key,map.get(key));
        }
        final Request request = new Request.Builder()
                .url(path)
                .post(builder.build())
                .build();

        Call call = client.newCall(request);

        //执行请求
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i("tag","====请求失败============>"+e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String result = response.body().string();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        load.load(result,0x111);
                    }
                });
            }
        });
    }

    public static void postMultRequest(Context context, String path, HashMap<String,String>map, final DownLoad load){
        OkHttpClient client = getInstance(context);
        MultipartBody.Builder builder = new MultipartBody.Builder();
        //设置提交参数的类型
        builder.setType(MultipartBody.FORM);
        for(String key:map.keySet()){
            builder.addFormDataPart(key,map.get(key));
        }
        Request request = new Request.Builder()
                .post(builder.build())
                .url(path)
                .build();
        Call call = client.newCall(request);
        //Callback运行在子线程当中
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i("tag","=========数据请求失败======"+e.getMessage());

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String result = response.body().string();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        load.load(result,0x112);
                    }
                });
            }
        });
    }

    public static void getRequest(Context context, String path, final DownLoad load){
        if (!isConnected(context)){
            Log.i("tag","=========网络连接失败========");
            return;
        }
        OkHttpClient client = getInstance(context);
        Request request = new Request.Builder()
                .url(path)
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String result = response.body().string();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        load.load(result,0x113);
                    }
                });
            }
        });


    }

    public static void postHttpRequest(Context context, String path, HashMap<String,String>map, final DownLoad load, final int tag){
        if (!isConnected(context)){
            Log.i("tag","=========网络连接失败========");
            return;
        }
        OkHttpClient client = getInstance(context);
        //设置参数，向服务器端提交参数
        FormBody.Builder builder = new FormBody.Builder();
        //设置向服务器端提交的参数
        for(String  key:map.keySet()){
            builder.add(key,map.get(key));
        }
        final Request request = new Request.Builder()
                .url(path)
                .post(builder.build())
                .build();

        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String str = response.body().string();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        load.load(str,tag);
                    }
                });
            }
        });
    }
    public static void postHttpFileRequest(Context context, String path, HashMap<String,String>map, String fileName, ArrayList<Pictures>pictures, final DownLoad load){
        if (!isConnected(context)){
            return;
        }
        OkHttpClient client = getInstance(context);
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        for (String key:map.keySet()){
            builder.addFormDataPart(key,map.get(key));
        }
        for (Pictures pic:pictures){
            File file = new File(pic.getPath());
            RequestBody body = RequestBody.create(MediaType.parse("image/*"),file);
            builder.addFormDataPart(fileName,file.getName(),body);
        }
        Request request = new Request.Builder().url(path).post(builder.build()).build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i("tag","======上传失败========"+e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String data = response.body().string();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        load.load(data,0x2323);
                    }
                });
            }
        });
    }
    public interface DownLoad{
        void load(String data,int tag);
    }
}

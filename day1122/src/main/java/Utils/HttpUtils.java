package Utils;

import android.content.Context;
import com.example.day1122.MainActivity;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/11/22.
 */

public class HttpUtils {
    private Context context;
    private LoadData load;

    public HttpUtils(Context context, LoadData load) {
        this.context = context;
        this.load = load;
    }
    public void getResult(String path){
        OkHttpClient client = new OkHttpClient.Builder().connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5,TimeUnit.SECONDS).build();
        Request request = new Request.Builder().url(path).build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String data = response.body().string();
                ((MainActivity)context).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        load.load(data);
                    }
                });
            }
        });
    }
    public interface LoadData{
        void load(String data);
    }
}

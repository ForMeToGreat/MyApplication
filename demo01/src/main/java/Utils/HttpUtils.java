package Utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.example.demo01.MainActivity;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/10/25.
 */

public class HttpUtils {
    private Context context;
    private DownLoad load;
    public HttpUtils(Context context,DownLoad load){
        this.context = context;
        this.load = load;
    }
    public boolean isCoonect(){
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo Info = manager.getActiveNetworkInfo();
        if (Info.getState().equals(NetworkInfo.State.CONNECTED)){
            return true;
        }
        return false;
    }
    public void getResult(String path) {
        if (!isCoonect()) {
            return;
        } else {
            OkHttpClient client = new OkHttpClient.Builder().connectTimeout(5, TimeUnit.SECONDS)
                    .readTimeout(5, TimeUnit.SECONDS).build();
            Request request = new Request.Builder().url(path).build();
            Call call = client.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }
                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    final String result = response.body().string();
                    ((MainActivity) context).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            load.load(result);
                        }
                    });
                }
            });
        }
    }
    public interface DownLoad{
        void load(String result);
    }
}

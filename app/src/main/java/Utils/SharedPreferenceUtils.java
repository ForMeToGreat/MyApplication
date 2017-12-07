package Utils;

import android.content.Context;
import android.content.SharedPreferences;

import Bean.LoginBean;

/**
 * Created by Administrator on 2017/10/12.
 */

public class SharedPreferenceUtils {
    private final SharedPreferences sp;

    //存放到
    public SharedPreferenceUtils(Context context) {
        sp = context.getSharedPreferences("user", Context.MODE_PRIVATE);
    }
    //创建方法，想sp对象中写入数据
    public void writeData(LoginBean bean){
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("token",bean.getToken());
        editor.putInt("id",bean.getData().getId());
        editor.putString("name",bean.getData().getName());
        editor.putInt("state",bean.getData().getState());
        editor.putBoolean("normal",bean.getData().isNormal());
        editor.commit();
    }
    //封装一个放啊，获取存放大token值
    public String getToken(){
        String token = sp.getString("token", "0");
        return token;
    }
}

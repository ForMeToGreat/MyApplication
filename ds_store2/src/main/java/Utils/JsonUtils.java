package Utils;

import com.google.gson.Gson;

import Bean.DingZhiBean;
import Bean.JavaBean;
import Bean.ShouyeBean;

/**
 * Created by Administrator on 2017/11/23.
 */

public class JsonUtils {
    public JavaBean getData(String json) {
        //创建Gson对象
        Gson gson = new Gson();
        JavaBean data = gson.fromJson(json, JavaBean.class);
        return data;
    }

    public ShouyeBean getShouData(String json) {
        Gson gson = new Gson();
        ShouyeBean bean = gson.fromJson(json, ShouyeBean.class);
        return bean;
    }

    public DingZhiBean getDingData(String json) {
        Gson gson = new Gson();
        DingZhiBean bean = gson.fromJson(json, DingZhiBean.class);
        return bean;
    }
}

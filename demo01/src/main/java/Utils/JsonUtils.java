package Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/10/25.
 */

public class JsonUtils {
    public JsonBean getJson(String json){
        JsonBean bean = new JsonBean();
        try {
            JSONObject ob1 = new JSONObject(json);
            bean.setStatus(ob1.optString("status"));
            JsonBean.Paramz paramz = bean.new Paramz();
            JSONObject ob2 = ob1.optJSONObject("paramz");
            ArrayList<JsonBean.Paramz.Feeds>feeds = new ArrayList<>();
            JSONArray array = ob2.optJSONArray("feeds");
            for (int i = 0;i<array.length();i++){
                JsonBean.Paramz.Feeds feeds1 = paramz.new Feeds();
                JSONObject ob3 = array.getJSONObject(i);
                JsonBean.Paramz.Feeds.Data data = feeds1.new Data();
                JSONObject ob4 = ob3.optJSONObject("data");
                data.setSubject(ob4.optString("subject"));
                data.setSummary(ob4.optString("summary"));
                data.setCover(ob4.optString("cover"));
                data.setChanged(ob4.optString("changed"));
                feeds1.setData(data);
                feeds.add(feeds1);
            }
            paramz.setFeeds(feeds);
            bean.setParamz(paramz);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return bean;
    }
}

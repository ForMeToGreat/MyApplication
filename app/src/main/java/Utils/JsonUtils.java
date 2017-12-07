package Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import Bean.LoginBean;
import Bean.OneBean;
import Bean.PagerBean;
import Bean.StroreDetialMessageBean;

/**
 * Created by Administrator on 2017/10/12.
 */

public class JsonUtils {
    public LoginBean getJsonData(String json){
        LoginBean bean = new LoginBean();
        try {
            JSONObject object = new JSONObject(json);
            bean.setStatus(object.optString("status"));
            bean.setInfo(object.optString("info"));
            bean.setToken(object.optString("token"));

            LoginBean.DataBean db = bean.new DataBean();
            JSONObject ob = object.optJSONObject("data");
            db.setId(ob.optInt("id"));
            db.setName(ob.optString("name"));
            db.setState(ob.optInt("state"));
            db.setNormal(ob.optBoolean("normal"));
            bean.setData(db);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return bean;
    }
    public OneBean getOneBean(String json){
        OneBean bean = new OneBean();
        try {
            JSONObject object = new JSONObject(json);
            bean.setStatus(object.optString("status"));
            bean.setInfo(object.optString("info"));

            OneBean.DataBean data = bean.new DataBean();
            JSONObject obd = object.optJSONObject("data");
            data.setSize(obd.optInt("size"));

            ArrayList<OneBean.DataBean.ListBean>list = new ArrayList<>();
            JSONArray array = obd.optJSONArray("list");
            for(int i=0;i<array.length();i++){
                OneBean.DataBean.ListBean lb = data.new ListBean();
                JSONObject ob = array.optJSONObject(i);
                lb.setId(ob.optInt("id"));
                lb.setTitle(ob.optString("title"));
                lb.setImage(ob.optString("image"));
                lb.setPrice(ob.optString("price"));
                lb.setContact(ob.optString("contact"));
                lb.setHead(ob.optString("head"));
                lb.setIssue_time(ob.optString("issue_time"));
                lb.setFollow(ob.optInt("follow"));
                lb.setState(ob.optInt("state"));
                lb.setFollowed(ob.optBoolean("followed"));
                list.add(lb);
            }
            data.setList(list);
            bean.setData(data);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return bean;
    }
    public PagerBean getPagerData(String json){
        PagerBean bean = new PagerBean();
        try {
            JSONObject object = new JSONObject(json);
            bean.setStatus(object.optString("status"));
            bean.setInfo(object.optString("info"));
            ArrayList<PagerBean.Data>list = new ArrayList<>();
            JSONArray array = object.optJSONArray("data");
            for (int i=0;i<array.length();i++){
                PagerBean.Data data = bean.new Data();
                JSONObject ob = array.getJSONObject(i);
                data.setImage(ob.optString("image"));
                data.setId(ob.optInt("id"));
                list.add(data);
            }
            bean.setData(list);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return bean;
    }
    public static StroreDetialMessageBean StoreUtils(String json){
        StroreDetialMessageBean sj = new StroreDetialMessageBean();
        try {
            JSONObject object = new JSONObject(json);
            sj.setStatus(object.getString("status"));
            sj.setInfo(object.getString("info"));
            if (object.optString("status").equals("200")&&object.optString("info").equals("成功")){
                JSONObject ob = object.getJSONObject("data");
                StroreDetialMessageBean.Data data = sj.new Data();
                data.setId(ob.optLong("id"));
                data.setUser_id(ob.getLong("user_id"));
                data.setTitle(ob.getString("title"));
                data.setDescription(ob.getString("description"));
                data.setPrice(ob.getString("price"));
                data.setContact(ob.getString("contact"));
                data.setHead(ob.getString("head"));
                data.setMobile(ob.getString("mobile"));
                data.setQq(ob.getString("qq"));
                data.setIssue_time(ob.getString("issue_time"));
                data.setFollowed(ob.getBoolean("followed"));
                data.setOwned(ob.getBoolean("owned"));

                ArrayList<StroreDetialMessageBean.Data.photos>list = new ArrayList<>();
                JSONArray array = ob.getJSONArray("photos");
                for(int i =  0 ; i < array.length() ; i++){
                    JSONObject ob1 = array.optJSONObject(i);
                    StroreDetialMessageBean.Data.photos photos = data.new photos();
                    photos.setStr(array.get(i)+"");
                    list.add(photos);
                }
                data.setList(list);
                sj.setData(data);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return sj;
    }
}

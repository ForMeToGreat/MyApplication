package JsonBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/11/14.
 */

public class JsonUtils {
    public Bean getDataBean(String json){
        Bean bean = new Bean();
        try {
            JSONObject ob = new JSONObject(json);
            ArrayList<Bean.DBean>dbeans = new ArrayList<>();
            JSONArray array = ob.optJSONArray("d");
            for (int i=0;i<array.length();i++){
                JSONObject ob1 = array.getJSONObject(i);
                Bean.DBean dbean = new Bean.DBean();
                dbean.setName(ob1.optString("name"));
                dbean.setId(ob1.optString("id"));
                dbean.setType(ob1.optString("type"));
                dbean.setOriginal(ob1.optString("original"));
                dbean.setImg(ob1.optString("img"));
                dbeans.add(dbean);
            }
            List<List<Bean.MBean>>mbeans = new ArrayList<>();
            JSONArray marray = ob.optJSONArray("m");
            for (int i=0;i<marray.length();i++){
                ArrayList<Bean.MBean>mbs = new ArrayList<>();
                JSONArray jar = marray.optJSONArray(i);
                for (int m=0;m<jar.length();m++){
                    Bean.MBean mb = new Bean.MBean();
                    JSONObject ob2 = jar.optJSONObject(m);
                    mb.setName(ob2.optString("name"));
                    mb.setId(ob2.optString("id"));
                    mb.setType(ob2.optString("type"));
                    mb.setL_type(ob2.optString("l_type"));
                    mbs.add(mb);
                }
                mbeans.add(mbs);
            }
            bean.setD(dbeans);
            bean.setM(mbeans);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return bean;
    }
}

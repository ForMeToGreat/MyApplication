package Bean;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/10/16.
 */

public class PagerBean {
    private String status;
    private String info;
    private ArrayList<Data>data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public ArrayList<Data> getData() {
        return data;
    }

    public void setData(ArrayList<Data> data) {
        this.data = data;
    }

    public class Data{
        private String image;
        private long id;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public long getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}

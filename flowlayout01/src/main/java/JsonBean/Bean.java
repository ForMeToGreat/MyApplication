package JsonBean;

import java.util.List;

/**
 * Created by Administrator on 2017/11/13.
 */

public class Bean {

    private List<DBean> d;
    private List<List<MBean>> m;

    public List<DBean> getD() {
        return d;
    }

    public void setD(List<DBean> d) {
        this.d = d;
    }

    public List<List<MBean>> getM() {
        return m;
    }

    public void setM(List<List<MBean>> m) {
        this.m = m;
    }

    public static class DBean {
        /**
         * name : 精品专区
         * id : 47
         * type : 0
         * original :
         * img : img/jp.jpg
         */

        private String name;
        private String id;
        private String type;
        private String original;
        private String img;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getOriginal() {
            return original;
        }

        public void setOriginal(String original) {
            this.original = original;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }
    }

    public static class MBean {
        /**
         * name : 名师有约
         * id : 49
         * type : 0
         * l_type : 2
         */

        private String name;
        private String id;
        private String type;
        private String l_type;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getL_type() {
            return l_type;
        }

        public void setL_type(String l_type) {
            this.l_type = l_type;
        }
    }
}

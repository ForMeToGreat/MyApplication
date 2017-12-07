package DataBean;

/**
 * Created by Administrator on 2017/11/13.
 */

public class Bean {
    private int imgId;
    private String name;
    private String price;

    public Bean(int imgId, String name, String price) {
        this.imgId = imgId;
        this.name = name;
        this.price = price;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}

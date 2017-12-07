package Utils;

/**
 * Created by Administrator on 2017/10/20.
 */

public class IAPI {
    /**
     * 图片请求接口地址
     */
    public static String IMAGEPATH = "http://192.168.190.188/Goods/uploads/";
    /**
     * 服务器地址
     */
    public static String ADDRESS = "http://192.168.190.188/Goods";
    /**
     * 登录注册
     */
    public static String REGISTER = "/app/common/register.json";
    public static String LOGIN = "/app/common/login.json";
    /**
     * 发布列表
     */
    public static String ISSUE = "/app/user/issue_list.json";
    /**
     * 轮播图查询
     */
    public static String CIRCLE_LIST = "/app/common/circle_list.json";
    /**
     * 轮播图立标详情
     */
    public static String CIRCLE_DIL = "/app/common/circle_dtl.json";

    /**
     * 商品详细查询
     */
    public static String GOODS_DIL = "/app/item/detail.json";

    /**
     * 商品搜查询列表
     */
    public static String SEACH_LIST = "/app/item/list.json";

    /**
     * 关注商品
     * @return
     */
    public static String FOLLOW = "/app/item/follow.json";
    public static String getLogin(){
        return ADDRESS+LOGIN;
    }
}

package cn.ifhu.dongjia.model.get;

import java.util.HashMap;
import java.util.Map;

import cn.ifhu.dongjia.model.post.BaseBean;
import cn.ifhu.dongjia.utils.UserLogic;

public class SubmitPreviewGetBean extends BaseBean {
    private String cart_id_list;
    private String mch_list;
    private String access_token = UserLogic.getUser().getAccess_token();
    private String goods_info;

    public String getCart_id_list() {
        return cart_id_list;
    }

    public void setCart_id_list(String cart_id_list) {
        this.cart_id_list = cart_id_list;
    }

    public String getMch_list() {
        return mch_list;
    }

    public void setMch_list(String mch_list) {
        this.mch_list = mch_list;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getGoods_info() {
        return goods_info;
    }

    public void setGoods_info(String goods_info) {
        this.goods_info = goods_info;
    }

    public Map<String, Object> getPostParam() {
        Map<String, Object> map = new HashMap<>();
        map.put("_uniacid", -1);
        map.put("_acid", -1);
        map.put("store_id", 4);
        map.put("cart_id_list", getCart_id_list());
        map.put("mch_list", getMch_list());
        map.put("access_token", getAccess_token());
        map.put("goods_info", getGoods_info());
        return map;
    }
}

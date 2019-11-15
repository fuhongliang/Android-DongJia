package cn.ifhu.dongjia.model.get;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.ifhu.dongjia.model.post.BaseBean;

public class OrderPayGetBean extends BaseBean {
    private int order_id;
    private String order_id_list;
    private String pay_type;
    private String access_token;
    private String app_key;

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public String getOrder_id_list() {
        return order_id_list;
    }

    public void setOrder_id_list(String order_id_list) {
        this.order_id_list = order_id_list;
    }

    public String getPay_type() {
        return pay_type;
    }

    public void setPay_type(String pay_type) {
        this.pay_type = pay_type;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getApp_key() {
        return app_key;
    }

    public void setApp_key(String app_key) {
        this.app_key = app_key;
    }

    public static class OrderIdList {

    }

    public Map<String, Object> getPostParam() {
        Map<String, Object> map = new HashMap<>();
        map.put("_uniacid", -1);
        map.put("_acid", -1);
        map.put("store_id", 4);
        map.put("order_id",getOrder_id());
        map.put("order_id_list",getOrder_id_list());
        map.put("pay_type",getPay_type());
        map.put("access_token",getAccess_token());
        map.put("app_key",getApp_key());
        return map;
    }
}

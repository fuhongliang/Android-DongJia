package cn.ifhu.dongjia.model.post;

import cn.ifhu.dongjia.utils.UserLogic;

public class SubmitPostBean extends BaseBean {
    private String access_token = UserLogic.getUser().getAccess_token();
    private String address_id;
    private String cart_id_list;
    private String mch_list;
    private String payment;
    private String app_key;
    private String goods_info;

    public String getGoods_info() {
        return goods_info;
    }

    public void setGoods_info(String goods_info) {
        this.goods_info = goods_info;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getAddress_id() {
        return address_id;
    }

    public void setAddress_id(String address_id) {
        this.address_id = address_id;
    }

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

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getApp_key() {
        return app_key;
    }

    public void setApp_key(String app_key) {
        this.app_key = app_key;
    }
}

package cn.ifhu.dongjia.model.post;

import java.util.List;

import cn.ifhu.dongjia.utils.UserLogic;

public class AddCartPostData extends BaseBean {
    private String goods_id;
    private String num;
    private String access_token = UserLogic.getUser().getAccess_token();
    private String attr;

    public String getAttr() {
        return attr;
    }

    public void setAttr(String attr) {
        this.attr = attr;
    }

    public String getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(String goods_id) {
        this.goods_id = goods_id;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }


    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }
}

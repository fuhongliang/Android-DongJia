package cn.ifhu.dongjia.model.post;

import java.util.List;

import cn.ifhu.dongjia.utils.UserLogic;

public class EditCartPostDataBean extends BaseBean{
    private String access_token = UserLogic.getUser().getAccess_token();
    private String cart_id_list;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getCart_id_list() {
        return cart_id_list;
    }

    public void setCart_id_list(String cart_id_list) {
        this.cart_id_list = cart_id_list;
    }
}

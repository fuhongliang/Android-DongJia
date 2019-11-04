package cn.ifhu.dongjia.model.post;

import cn.ifhu.dongjia.utils.UserLogic;

public class FavoriteAddPostBean extends BaseBean {
    private String goods_id;
    private String access_token = UserLogic.getUser().getAccess_token();

    public String getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(String goods_id) {
        this.goods_id = goods_id;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }
}

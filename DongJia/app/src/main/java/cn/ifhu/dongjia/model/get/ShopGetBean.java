package cn.ifhu.dongjia.model.get;

import java.util.HashMap;
import java.util.Map;

import cn.ifhu.dongjia.utils.UserLogic;

public class ShopGetBean {
    public String mch_id;

    public String getMch_id() {
        return mch_id;
    }

    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String page;
    private String access_token = UserLogic.getUser() != null ? UserLogic.getUser().getAccess_token() : "";


    public Map<String,Object> getPostParam() {
        Map<String, Object> map = new HashMap<>();
        map.put("_uniacid",-1);
        map.put("_acid",-1);
        map.put("store_id",4);
        map.put("mch_id",getMch_id());
        map.put("tab",2);
        map.put("sort",1);
        map.put("page",getPage());
        map.put("access_token",getAccess_token());
        return map;
    }
}

package cn.ifhu.dongjia.model.post;

import java.util.HashMap;
import java.util.Map;

import cn.ifhu.dongjia.utils.UserLogic;

public class GoodDetailsGetBean {
    public String id;
    private String access_token = UserLogic.getUser() != null ? UserLogic.getUser().getAccess_token() : "";

    public Map<String,Object> getPostParam() {
        Map<String, Object> map = new HashMap<>();
        map.put("_uniacid",-1);
        map.put("_acid",-1);
        map.put("store_id",4);
        map.put("id",getId());
        map.put("access_token",getAccess_token());
        return map;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }
}

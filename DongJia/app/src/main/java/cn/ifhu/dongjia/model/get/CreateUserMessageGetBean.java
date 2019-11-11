package cn.ifhu.dongjia.model.get;

import java.util.HashMap;
import java.util.Map;

import cn.ifhu.dongjia.model.post.BaseBean;
import cn.ifhu.dongjia.utils.UserLogic;

public class CreateUserMessageGetBean extends BaseBean {
    private String access_token = UserLogic.getUser() != null ? UserLogic.getUser().getAccess_token() : "";
    private String contact;
    private String message;
    private String telephone;
    private String topic_id;

    public Map<String, Object> getPostParam() {
        Map<String, Object> map = new HashMap<>();
        map.put("_uniacid", -1);
        map.put("_acid", -1);
        map.put("store_id", 4);
        map.put("access_token", getAccess_token());
        map.put("contact", getContact());
        map.put("message", getMessage());
        map.put("telephone", getTelephone());
        map.put("topic_id", getTopic_id());
        return map;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getTopic_id() {
        return topic_id;
    }

    public void setTopic_id(String topic_id) {
        this.topic_id = topic_id;
    }
}

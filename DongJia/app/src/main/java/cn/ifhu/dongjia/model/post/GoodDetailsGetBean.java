package cn.ifhu.dongjia.model.post;

public class GoodDetailsGetBean extends BaseBean {
    public int id;
    public String access_token;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }
}

package cn.ifhu.dongjia.model.post;

public class HomeGetBean extends BaseBean {

    private String app_key = "android";
    private String district = "陆丰市";

    public String getApp_key() {
        return app_key;
    }

    public void setApp_key(String app_key) {
        this.app_key = app_key;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }
}

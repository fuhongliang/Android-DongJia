package cn.ifhu.dongjia.model.data;

import com.google.gson.annotations.SerializedName;

import cn.ifhu.dongjia.model.post.BaseBean;

public class OrderPayDataBean {


    /**
     * appid : wx4cb54f0fb9038e2e
     * partnerid : 1552335701
     * prepayid : wx141018267922816d250303701169390800
     * package : Sign=WXPay
     * noncestr : 84769906562a8dd00fd1ff0ddd6b8aa0
     * timestamp : 1573697906
     * sign : 3C70706EA11E633FE8F4F8DD93366673
     */

    private String appid;
    private String partnerid;
    private String prepayid;
    @SerializedName("package")
    private String packageX;
    private String noncestr;
    private int timestamp;
    private String sign;

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getPartnerid() {
        return partnerid;
    }

    public void setPartnerid(String partnerid) {
        this.partnerid = partnerid;
    }

    public String getPrepayid() {
        return prepayid;
    }

    public void setPrepayid(String prepayid) {
        this.prepayid = prepayid;
    }

    public String getPackageX() {
        return packageX;
    }

    public void setPackageX(String packageX) {
        this.packageX = packageX;
    }

    public String getNoncestr() {
        return noncestr;
    }

    public void setNoncestr(String noncestr) {
        this.noncestr = noncestr;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}

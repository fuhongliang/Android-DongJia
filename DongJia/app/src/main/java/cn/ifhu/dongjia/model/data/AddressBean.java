package cn.ifhu.dongjia.model.data;

import java.io.Serializable;

public class AddressBean implements Serializable {
    /**
     * id : 37
     * name : ssss
     * mobile : 18319699581
     * province_id : 2
     * province : 北京市
     * city_id : 3
     * city : 北京市
     * district_id : 4
     * district : 东城区
     * detail : xxxx
     * is_default : 0
     */

    private String id;
    private String name;
    private String mobile;
    private String province_id;
    private String province;
    private String city_id;
    private String city;
    private String district_id;
    private String district;
    private String detail;
    private String is_default;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getProvince_id() {
        return province_id;
    }

    public void setProvince_id(String province_id) {
        this.province_id = province_id;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity_id() {
        return city_id;
    }

    public void setCity_id(String city_id) {
        this.city_id = city_id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict_id() {
        return district_id;
    }

    public void setDistrict_id(String district_id) {
        this.district_id = district_id;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getIs_default() {
        return is_default;
    }

    public void setIs_default(String is_default) {
        this.is_default = is_default;
    }
}
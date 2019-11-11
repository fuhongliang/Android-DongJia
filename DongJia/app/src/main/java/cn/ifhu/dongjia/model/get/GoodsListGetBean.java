package cn.ifhu.dongjia.model.get;

import java.util.HashMap;
import java.util.Map;

import cn.ifhu.dongjia.model.post.BaseBean;

public class GoodsListGetBean extends BaseBean {
    private String district;
    private String cat_id;
    private int page;
    private int sort;
    private int sort_type;
    public Map<String, Object> getPostParam() {
        Map<String, Object> map = new HashMap<>();
        map.put("_uniacid", -1);
        map.put("_acid", -1);
        map.put("store_id", 4);
        map.put("district", getDistrict());
        map.put("cat_id", getCat_id());
        map.put("page", getPage());
        map.put("sort", getSort());
        map.put("sort_type", getSort_type());
        return map;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCat_id() {
        return cat_id;
    }

    public void setCat_id(String cat_id) {
        this.cat_id = cat_id;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public int getSort_type() {
        return sort_type;
    }

    public void setSort_type(int sort_type) {
        this.sort_type = sort_type;
    }
}

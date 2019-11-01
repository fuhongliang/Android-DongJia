package cn.ifhu.dongjia.model.post;

import cn.ifhu.dongjia.utils.UserLogic;

public class AttrBeanPost {

    /**
     * attr_group_id : 5
     * attr_group_name : 颜色
     * attr_id : 72
     * attr_name : 机械锁【左右开向】
     */
    private int attr_group_id;
    private String attr_group_name;
    private int attr_id;
    private String attr_name;

    public int getAttr_group_id() {
        return attr_group_id;
    }

    public void setAttr_group_id(int attr_group_id) {
        this.attr_group_id = attr_group_id;
    }

    public String getAttr_group_name() {
        return attr_group_name;
    }

    public void setAttr_group_name(String attr_group_name) {
        this.attr_group_name = attr_group_name;
    }

    public int getAttr_id() {
        return attr_id;
    }

    public void setAttr_id(int attr_id) {
        this.attr_id = attr_id;
    }

    public String getAttr_name() {
        return attr_name;
    }

    public void setAttr_name(String attr_name) {
        this.attr_name = attr_name;
    }
}

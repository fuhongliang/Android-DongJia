package cn.ifhu.dongjia.model.post;

import java.util.List;

public class MchPost {

    /**
     * id : 4
     * cart_id_list : [143]
     */

    private int id;
    private List<Integer> cart_id_list;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Integer> getCart_id_list() {
        return cart_id_list;
    }

    public void setCart_id_list(List<Integer> cart_id_list) {
        this.cart_id_list = cart_id_list;
    }
}

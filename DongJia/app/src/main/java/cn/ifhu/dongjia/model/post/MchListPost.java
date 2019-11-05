package cn.ifhu.dongjia.model.post;


import java.io.Serializable;
import java.util.List;

public class MchListPost implements Serializable {

    private List<MchListBean> mchListBeans;

    public List<MchListBean> getMchListBeans() {
        return mchListBeans;
    }

    public void setMchListBeans(List<MchListBean> mchListBeans) {
        this.mchListBeans = mchListBeans;
    }

    public static class MchListBean implements Serializable{
        /**
         * id : 11
         * cart_id_list : [152]
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

}

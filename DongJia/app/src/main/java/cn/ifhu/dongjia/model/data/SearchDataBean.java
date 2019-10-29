package cn.ifhu.dongjia.model.data;

import java.util.List;

public class SearchDataBean {

    /**
     * row_count : 3
     * page_count : 1
     * list : [{"id":"58","name":"黑色弧扇形淋浴房整体扇形浴屏卫生间隔断玻璃门简易干湿分离浴室","sort":"1","addtime":"1562035871","price":"1899.00","pic_url":"http://yiwuyimei.oss-cn-beijing.aliyuncs.com/web/uploads/image/30/302c4262c1e7e2d546048b9e77b634effd1907ca.png","store_id":"4","status":"1","is_delete":"0","goods_type":"m","url":"/pages/goods/goods?id=58"},{"id":"57","name":"定制淋浴房卫生间玻璃隔断简易洗澡间干湿分离浴室冲凉房","sort":"4","addtime":"1562035728","price":"899.00","pic_url":"http://yiwuyimei.oss-cn-beijing.aliyuncs.com/web/uploads/image/0e/0e12fd313dba8722bfce9672a0165e760c47afb5.png","store_id":"4","status":"1","is_delete":"0","goods_type":"m","url":"/pages/goods/goods?id=57"},{"id":"102","name":"黑色弧扇形淋浴房整体扇形浴屏卫生间隔断玻璃门简易干湿分离浴室","sort":"1000","addtime":"1564459133","price":"1899.20","pic_url":"http://yiwuyimei.oss-cn-beijing.aliyuncs.com/web/uploads/image/30/302c4262c1e7e2d546048b9e77b634effd1907ca.png","store_id":"4","status":"1","is_delete":"0","goods_type":"m","url":"/pages/goods/goods?id=102"}]
     */

    private String row_count;
    private int page_count;
    private List<ListBean> list;

    public String getRow_count() {
        return row_count;
    }

    public void setRow_count(String row_count) {
        this.row_count = row_count;
    }

    public int getPage_count() {
        return page_count;
    }

    public void setPage_count(int page_count) {
        this.page_count = page_count;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * id : 58
         * name : 黑色弧扇形淋浴房整体扇形浴屏卫生间隔断玻璃门简易干湿分离浴室
         * sort : 1
         * addtime : 1562035871
         * price : 1899.00
         * pic_url : http://yiwuyimei.oss-cn-beijing.aliyuncs.com/web/uploads/image/30/302c4262c1e7e2d546048b9e77b634effd1907ca.png
         * store_id : 4
         * status : 1
         * is_delete : 0
         * goods_type : m
         * url : /pages/goods/goods?id=58
         */

        private String id;
        private String name;
        private String sort;
        private String addtime;
        private String price;
        private String pic_url;
        private String store_id;
        private String status;
        private String is_delete;
        private String goods_type;
        private String url;

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

        public String getSort() {
            return sort;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getPic_url() {
            return pic_url;
        }

        public void setPic_url(String pic_url) {
            this.pic_url = pic_url;
        }

        public String getStore_id() {
            return store_id;
        }

        public void setStore_id(String store_id) {
            this.store_id = store_id;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getIs_delete() {
            return is_delete;
        }

        public void setIs_delete(String is_delete) {
            this.is_delete = is_delete;
        }

        public String getGoods_type() {
            return goods_type;
        }

        public void setGoods_type(String goods_type) {
            this.goods_type = goods_type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}

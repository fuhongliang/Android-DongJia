package cn.ifhu.dongjia.model.data;

import java.util.List;

public class GoodsRecommendDataBean {

    /**
     * row_count : 11
     * page_count : 4
     * list : [{"id":"58","name":"黑色弧扇形淋浴房整体扇形浴屏卫生间隔断玻璃门简易干湿分离浴室","price":"1899.00","original_price":"2899.00","pic_url":"http://yiwuyimei.oss-cn-beijing.aliyuncs.com/web/uploads/image/30/302c4262c1e7e2d546048b9e77b634effd1907ca.png","num":"5","virtual_sales":"0","unit":"件","sales":"5件"},{"id":"59","name":"箭牌卫浴（ARROW） 整体淋浴房弧扇形钢化玻璃简易淋浴房","price":"2649.00","original_price":"3600.00","pic_url":"http://yiwuyimei.oss-cn-beijing.aliyuncs.com/web/uploads/image/23/23282cc6bb9f5ae3d976066146dbdbd8266575fc.png","num":"5","virtual_sales":"0","unit":"件","sales":"5件"},{"id":"57","name":"定制淋浴房卫生间玻璃隔断简易洗澡间干湿分离浴室冲凉房","price":"899.00","original_price":"999.00","pic_url":"http://yiwuyimei.oss-cn-beijing.aliyuncs.com/web/uploads/image/0e/0e12fd313dba8722bfce9672a0165e760c47afb5.png","num":"7","virtual_sales":"0","unit":"件","sales":"7件"}]
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
         * price : 1899.00
         * original_price : 2899.00
         * pic_url : http://yiwuyimei.oss-cn-beijing.aliyuncs.com/web/uploads/image/30/302c4262c1e7e2d546048b9e77b634effd1907ca.png
         * num : 5
         * virtual_sales : 0
         * unit : 件
         * sales : 5件
         */

        private String id;
        private String name;
        private String price;
        private String original_price;
        private String pic_url;
        private String num;
        private String virtual_sales;
        private String unit;
        private String sales;

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

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getOriginal_price() {
            return original_price;
        }

        public void setOriginal_price(String original_price) {
            this.original_price = original_price;
        }

        public String getPic_url() {
            return pic_url;
        }

        public void setPic_url(String pic_url) {
            this.pic_url = pic_url;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public String getVirtual_sales() {
            return virtual_sales;
        }

        public void setVirtual_sales(String virtual_sales) {
            this.virtual_sales = virtual_sales;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public String getSales() {
            return sales;
        }

        public void setSales(String sales) {
            this.sales = sales;
        }
    }
}

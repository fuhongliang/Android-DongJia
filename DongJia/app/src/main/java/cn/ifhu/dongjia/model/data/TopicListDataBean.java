package cn.ifhu.dongjia.model.data;

import java.util.List;

public class TopicListDataBean {

    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * id : 10
         * store_id : 4
         * title : 140方 四居室 幸福湖畔 效果图
         * sub_title :
         * cover_pic : http://yiwuyimei.oss-cn-beijing.aliyuncs.com/web/uploads/image/b9/b933768a4848f024127cd16a82e70a62b7f4d7be.jpg
         * layout : 0
         * sort : 1
         * agree_count : 0
         * virtual_agree_count : 0
         * virtual_favorite_count : 238
         * addtime : 07-04 15:41
         * is_delete : 0
         * is_chosen : 1
         * type : 1
         * mch_id : 0
         * master_id : 0
         * author : 官方平台
         * author_logo : http://yiwuyimei.oss-cn-beijing.aliyuncs.com/web/uploads/image/c6/c6cca29ee69a352a677ae520545a16a368de1472.png
         * read_count : 3489人浏览
         * goods_count : 2件宝贝
         */

        private String id;
        private String store_id;
        private String title;
        private String sub_title;
        private String cover_pic;
        private String layout;
        private String sort;
        private String agree_count;
        private String virtual_agree_count;
        private String virtual_favorite_count;
        private String addtime;
        private String is_delete;
        private String is_chosen;
        private String type;
        private String mch_id;
        private String master_id;
        private String author;
        private String author_logo;
        private String read_count;
        private String goods_count;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getStore_id() {
            return store_id;
        }

        public void setStore_id(String store_id) {
            this.store_id = store_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSub_title() {
            return sub_title;
        }

        public void setSub_title(String sub_title) {
            this.sub_title = sub_title;
        }

        public String getCover_pic() {
            return cover_pic;
        }

        public void setCover_pic(String cover_pic) {
            this.cover_pic = cover_pic;
        }

        public String getLayout() {
            return layout;
        }

        public void setLayout(String layout) {
            this.layout = layout;
        }

        public String getSort() {
            return sort;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }

        public String getAgree_count() {
            return agree_count;
        }

        public void setAgree_count(String agree_count) {
            this.agree_count = agree_count;
        }

        public String getVirtual_agree_count() {
            return virtual_agree_count;
        }

        public void setVirtual_agree_count(String virtual_agree_count) {
            this.virtual_agree_count = virtual_agree_count;
        }

        public String getVirtual_favorite_count() {
            return virtual_favorite_count;
        }

        public void setVirtual_favorite_count(String virtual_favorite_count) {
            this.virtual_favorite_count = virtual_favorite_count;
        }

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        public String getIs_delete() {
            return is_delete;
        }

        public void setIs_delete(String is_delete) {
            this.is_delete = is_delete;
        }

        public String getIs_chosen() {
            return is_chosen;
        }

        public void setIs_chosen(String is_chosen) {
            this.is_chosen = is_chosen;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getMch_id() {
            return mch_id;
        }

        public void setMch_id(String mch_id) {
            this.mch_id = mch_id;
        }

        public String getMaster_id() {
            return master_id;
        }

        public void setMaster_id(String master_id) {
            this.master_id = master_id;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getAuthor_logo() {
            return author_logo;
        }

        public void setAuthor_logo(String author_logo) {
            this.author_logo = author_logo;
        }

        public String getRead_count() {
            return read_count;
        }

        public void setRead_count(String read_count) {
            this.read_count = read_count;
        }

        public String getGoods_count() {
            return goods_count;
        }

        public void setGoods_count(String goods_count) {
            this.goods_count = goods_count;
        }
    }
}

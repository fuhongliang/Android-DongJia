package cn.ifhu.dongjia.model.data;

import java.util.List;

public class DistrictDataBean implements IPickerViewData {

    /**
     * id : 2
     * name : 北京市
     * parent_id : 1
     * level : province
     * list : [{"id":3,"name":"北京市","parent_id":2,"level":"city","list":[{"id":4,"name":"东城区","parent_id":3,"level":"district","list":[]},{"id":5,"name":"西城区","parent_id":3,"level":"district","list":[]},{"id":6,"name":"朝阳区","parent_id":3,"level":"district","list":[]},{"id":7,"name":"丰台区","parent_id":3,"level":"district","list":[]},{"id":8,"name":"石景山区","parent_id":3,"level":"district","list":[]},{"id":9,"name":"海淀区","parent_id":3,"level":"district","list":[]},{"id":10,"name":"门头沟区","parent_id":3,"level":"district","list":[]},{"id":11,"name":"房山区","parent_id":3,"level":"district","list":[]},{"id":12,"name":"通州区","parent_id":3,"level":"district","list":[]},{"id":13,"name":"顺义区","parent_id":3,"level":"district","list":[]},{"id":14,"name":"昌平区","parent_id":3,"level":"district","list":[]},{"id":15,"name":"大兴区","parent_id":3,"level":"district","list":[]},{"id":16,"name":"怀柔区","parent_id":3,"level":"district","list":[]},{"id":17,"name":"平谷区","parent_id":3,"level":"district","list":[]},{"id":18,"name":"密云区","parent_id":3,"level":"district","list":[]},{"id":19,"name":"延庆区","parent_id":3,"level":"district","list":[]}]}]
     */

    private int id;
    private String name;
    private int parent_id;
    private String level;
    private List<ListBeanX> list;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getParent_id() {
        return parent_id;
    }

    public void setParent_id(int parent_id) {
        this.parent_id = parent_id;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public List<ListBeanX> getList() {
        return list;
    }

    public void setList(List<ListBeanX> list) {
        this.list = list;
    }

    @Override
    public String getPickerViewText() {
        return this.name;
    }

    public static class ListBeanX {
        /**
         * id : 3
         * name : 北京市
         * parent_id : 2
         * level : city
         * list : [{"id":4,"name":"东城区","parent_id":3,"level":"district","list":[]},{"id":5,"name":"西城区","parent_id":3,"level":"district","list":[]},{"id":6,"name":"朝阳区","parent_id":3,"level":"district","list":[]},{"id":7,"name":"丰台区","parent_id":3,"level":"district","list":[]},{"id":8,"name":"石景山区","parent_id":3,"level":"district","list":[]},{"id":9,"name":"海淀区","parent_id":3,"level":"district","list":[]},{"id":10,"name":"门头沟区","parent_id":3,"level":"district","list":[]},{"id":11,"name":"房山区","parent_id":3,"level":"district","list":[]},{"id":12,"name":"通州区","parent_id":3,"level":"district","list":[]},{"id":13,"name":"顺义区","parent_id":3,"level":"district","list":[]},{"id":14,"name":"昌平区","parent_id":3,"level":"district","list":[]},{"id":15,"name":"大兴区","parent_id":3,"level":"district","list":[]},{"id":16,"name":"怀柔区","parent_id":3,"level":"district","list":[]},{"id":17,"name":"平谷区","parent_id":3,"level":"district","list":[]},{"id":18,"name":"密云区","parent_id":3,"level":"district","list":[]},{"id":19,"name":"延庆区","parent_id":3,"level":"district","list":[]}]
         */

        private int id;
        private String name;
        private int parent_id;
        private String level;
        private List<ListBean> list;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getParent_id() {
            return parent_id;
        }

        public void setParent_id(int parent_id) {
            this.parent_id = parent_id;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * id : 4
             * name : 东城区
             * parent_id : 3
             * level : district
             * list : []
             */

            private int id;
            private String name;
            private int parent_id;
            private String level;
            private List<?> list;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getParent_id() {
                return parent_id;
            }

            public void setParent_id(int parent_id) {
                this.parent_id = parent_id;
            }

            public String getLevel() {
                return level;
            }

            public void setLevel(String level) {
                this.level = level;
            }

            public List<?> getList() {
                return list;
            }

            public void setList(List<?> list) {
                this.list = list;
            }
        }
    }
}

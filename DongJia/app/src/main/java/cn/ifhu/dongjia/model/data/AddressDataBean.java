package cn.ifhu.dongjia.model.data;

import java.util.List;

public class AddressDataBean {
    private List<AddressListBean> addList;

    public List<AddressListBean> getAddList() {
        return addList;
    }

    public void setAddList(List<AddressListBean> addList) {
        this.addList = addList;
    }

    public static class AddressListBean{
        private String name;
        private String phone;
        private String address;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }


    }


}

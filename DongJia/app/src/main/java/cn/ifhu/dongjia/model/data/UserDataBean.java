package cn.ifhu.dongjia.model.data;

public class UserDataBean {

    /**
     * access_token : EudaZbdWWCiWMVrezMhfWDXeNYDKM5Ui
     * nickname : 测试账号
     * avatar_url : http://thirdwx.qlogo.cn/mmopen/vi_32/AFVJP1c6HQbJib1dOmW1gJKRXqPT7wYoXricQJBnMyibk83HD1hREewuKRTRZpMZfY6RSKYHibVxkPOQJxCeuVVISw/132
     * is_distributor : 0
     * parent : 总店
     * id : 21
     * is_clerk : 0
     * integral : 0
     * money : 0.00
     * res : {"access_token":"26_PPClNoGdo_vO2t62hPIA5AZKXFblqZ3rI8zGxzWclP5aThRcK-3dibRW9XC8dfMCKGp6IX0KfWRJa7l369gHpQh8bg_yhgvoKtP-fN4IVgE","expires_in":7200,"refresh_token":"26_00SxE6n2a7UYKYZWbpa5mT4YYBDXJA0RJpdpubE3Z7IGMxialZ4YRYfcy9A9ToVjHorBH7NHjmsbkK45ZV8zl_Km4aBdagHISQtXFp0aBAU","openid":"oLpaBuPifC3_ciWAyYTtqghOj-ow","scope":"snsapi_userinfo","unionid":"o3M_PtyPafJv8EYYQWzzmPmGN4JY"}
     */

    private String access_token;
    private String nickname;
    private String avatar_url;
    private int is_distributor;
    private String parent;
    private int id;
    private int is_clerk;
    private int integral;
    private String money;
    private ResBean res;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public int getIs_distributor() {
        return is_distributor;
    }

    public void setIs_distributor(int is_distributor) {
        this.is_distributor = is_distributor;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIs_clerk() {
        return is_clerk;
    }

    public void setIs_clerk(int is_clerk) {
        this.is_clerk = is_clerk;
    }

    public int getIntegral() {
        return integral;
    }

    public void setIntegral(int integral) {
        this.integral = integral;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public ResBean getRes() {
        return res;
    }

    public void setRes(ResBean res) {
        this.res = res;
    }

    public static class ResBean {
        /**
         * access_token : 26_PPClNoGdo_vO2t62hPIA5AZKXFblqZ3rI8zGxzWclP5aThRcK-3dibRW9XC8dfMCKGp6IX0KfWRJa7l369gHpQh8bg_yhgvoKtP-fN4IVgE
         * expires_in : 7200
         * refresh_token : 26_00SxE6n2a7UYKYZWbpa5mT4YYBDXJA0RJpdpubE3Z7IGMxialZ4YRYfcy9A9ToVjHorBH7NHjmsbkK45ZV8zl_Km4aBdagHISQtXFp0aBAU
         * openid : oLpaBuPifC3_ciWAyYTtqghOj-ow
         * scope : snsapi_userinfo
         * unionid : o3M_PtyPafJv8EYYQWzzmPmGN4JY
         */

        private String access_token;
        private int expires_in;
        private String refresh_token;
        private String openid;
        private String scope;
        private String unionid;

        public String getAccess_token() {
            return access_token;
        }

        public void setAccess_token(String access_token) {
            this.access_token = access_token;
        }

        public int getExpires_in() {
            return expires_in;
        }

        public void setExpires_in(int expires_in) {
            this.expires_in = expires_in;
        }

        public String getRefresh_token() {
            return refresh_token;
        }

        public void setRefresh_token(String refresh_token) {
            this.refresh_token = refresh_token;
        }

        public String getOpenid() {
            return openid;
        }

        public void setOpenid(String openid) {
            this.openid = openid;
        }

        public String getScope() {
            return scope;
        }

        public void setScope(String scope) {
            this.scope = scope;
        }

        public String getUnionid() {
            return unionid;
        }

        public void setUnionid(String unionid) {
            this.unionid = unionid;
        }
    }
}

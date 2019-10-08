package cn.ifhu.dongjia.model.post;

public class BaseBean {
    private int store_id = 4;
    private int _uniacid = -1;
    private int _acid = -1;

    public int getStore_id() {
        return store_id;
    }

    public void setStore_id(int store_id) {
        this.store_id = store_id;
    }

    public int get_uniacid() {
        return _uniacid;
    }

    public void set_uniacid(int _uniacid) {
        this._uniacid = _uniacid;
    }

    public int get_acid() {
        return _acid;
    }

    public void set_acid(int _acid) {
        this._acid = _acid;
    }
}

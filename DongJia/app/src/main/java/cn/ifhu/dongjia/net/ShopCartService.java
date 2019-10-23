package cn.ifhu.dongjia.net;

import cn.ifhu.dongjia.model.BaseEntity;
import cn.ifhu.dongjia.model.data.CartListDataBean;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ShopCartService {
    /**
     * 购物车列表接口
     */
    @GET("/index.php?r=api/cart/list")
    public Observable<BaseEntity<CartListDataBean>> cartList(@Query("store_id") int store_id, @Query("_uniacid") int uniacid, @Query("_acid") int acid, @Query("access_token") String access_token);

}

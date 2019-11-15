package cn.ifhu.dongjia.net;


import cn.ifhu.dongjia.model.BaseEntity;
import cn.ifhu.dongjia.model.data.OrderDetailDataBean;
import cn.ifhu.dongjia.model.data.OrderListDataBean;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OrderServer {
    /**
     * 订单列表
     */
    @GET("/index.php?r=api/order/list")
    public Observable<BaseEntity<OrderListDataBean>> orderList(@Query("store_id") int store_id, @Query("_uniacid") int uniacid, @Query("_acid") int acid, @Query("access_token") String access_token, @Query("status") int status);

    /**
     * 订单详情
     */
    @GET("/index.php?r=api/order/detail")
    public Observable<BaseEntity<OrderDetailDataBean>> orderDetail(@Query("store_id") int store_id, @Query("_uniacid") int uniacid, @Query("_acid") int acid, @Query("access_token") String access_token, @Query("order_id") int order_id);

    /**
     * 取消订单
     */
    @GET("/index.php?r=api/order/revoke")
    public Observable<BaseEntity<Object>> orderRevoke(@Query("store_id") int store_id, @Query("_uniacid") int uniacid, @Query("_acid") int acid, @Query("access_token") String access_token, @Query("order_id") int order_id);

}

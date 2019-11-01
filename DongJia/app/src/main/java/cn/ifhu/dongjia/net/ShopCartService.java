package cn.ifhu.dongjia.net;

import cn.ifhu.dongjia.model.BaseEntity;
import cn.ifhu.dongjia.model.data.CartListDataBean;
import cn.ifhu.dongjia.model.post.AddCartPostData;
import cn.ifhu.dongjia.model.post.EditCartPostDataBean;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ShopCartService {
    /**
     * 添加购物车接口
     *
     * @return
     */
    @POST("/index.php?r=api/cart/add-cart")
    public Observable<BaseEntity<Object>> addCart(@Body AddCartPostData addCartPostData);

    /**
     * 购物车列表接口
     */
    @GET("/index.php?r=api/cart/list")
    public Observable<BaseEntity<CartListDataBean>> cartList(@Query("store_id") int store_id, @Query("_uniacid") int uniacid, @Query("_acid") int acid, @Query("access_token") String access_token);
    /**
     * 删除购物车商品
     */
    @GET("/index.php?r=api/cart/delete")
    public Observable<BaseEntity<Object>> cartDelete(@Query("store_id") int store_id, @Query("_uniacid") int uniacid, @Query("_acid") int acid, @Query("access_token") String access_token,@Query("cart_id_list") String cart_id_list);

    /**
     * 编辑购物车
     *
     * @return
     */
    @POST("/index.php?r=api/cart/edit-cart")
    public Observable<BaseEntity<Object>> editCart(@Body EditCartPostDataBean editCartPostDataBean);

}

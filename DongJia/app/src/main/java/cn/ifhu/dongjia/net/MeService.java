package cn.ifhu.dongjia.net;

import java.util.List;

import cn.ifhu.dongjia.model.BaseEntity;
import cn.ifhu.dongjia.model.data.AddressListDataBean;
import cn.ifhu.dongjia.model.data.DistrictDataBean;
import cn.ifhu.dongjia.model.data.FavoriteListDataBean;
import cn.ifhu.dongjia.model.data.MeDataBean;
import cn.ifhu.dongjia.model.post.AddressSaveDataBean;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface MeService {
    /**
     * 我的
     */
    @GET("/index.php?r=api/user/index")
    public Observable<BaseEntity<MeDataBean>> Me(@Query("store_id") int store_id, @Query("_uniacid") int uniacid, @Query("_acid") int acid, @Query("access_token") String access_token);

    /**
     * 新增更新地址
     *
     * @return
     */
    @POST("/index.php?r=api/user/address-save")
    public Observable<BaseEntity<Object>> addressSave(@Body AddressSaveDataBean addressSaveDataBean);

    /**
     * 地区列表
     */
    @GET("/index.php?r=api/default/district")
    public Observable<BaseEntity<List<DistrictDataBean>>> district(@Query("store_id") int store_id, @Query("_uniacid") int uniacid, @Query("_acid") int acid, @Query("access_token") String access_token);

    /**
     * 地址列表
     */
    @GET("/index.php?r=api/user/address-list")
    public Observable<BaseEntity<AddressListDataBean>> addressList(@Query("store_id") int store_id, @Query("_uniacid") int uniacid, @Query("_acid") int acid, @Query("access_token") String access_token);

    /**
     * 删除地址
     */
    @GET("/index.php?r=api/user/address-delete")
    public Observable<BaseEntity<Object>> addressDelete(@Query("address_id") int address_id, @Query("store_id") int store_id, @Query("_uniacid") int uniacid, @Query("_acid") int acid, @Query("access_token") String access_token);

    /**
     * 我的收藏商品列表
     */
    @GET("/index.php?r=api/user/favorite-list")
    public Observable<BaseEntity<FavoriteListDataBean>> favoriteList(@Query("store_id") int store_id, @Query("_uniacid") int uniacid, @Query("_acid") int acid, @Query("access_token") String access_token, @Query("page") int page);

}

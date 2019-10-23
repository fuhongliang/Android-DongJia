package cn.ifhu.dongjia.net;

import cn.ifhu.dongjia.model.BaseEntity;
import cn.ifhu.dongjia.model.data.DistrictDataBean;
import cn.ifhu.dongjia.model.data.MeDataBean;
import cn.ifhu.dongjia.model.post.AddressSaveDataBean;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface MeServive {
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
    @POST("/index.php?=api/user/address-save")
    public Observable<BaseEntity<Object>> addressSave(@Body AddressSaveDataBean addressSaveDataBean);

    /**
     * 我的
     */
    @GET("/index.php?r=api/default/district")
    public Observable<BaseEntity<DistrictDataBean>> district(@Query("store_id") int store_id, @Query("_uniacid") int uniacid, @Query("_acid") int acid, @Query("access_token") String access_token);

}

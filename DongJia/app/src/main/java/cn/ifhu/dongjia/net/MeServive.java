package cn.ifhu.dongjia.net;

import cn.ifhu.dongjia.model.BaseEntity;
import cn.ifhu.dongjia.model.data.MeDataBean;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MeServive {
    /**
     * 我的
     */
    @GET("/index.php?r=api/user/index")
    public Observable<BaseEntity<MeDataBean>> Me(@Query("store_id") int store_id, @Query("_uniacid") int uniacid, @Query("_acid") int acid, @Query("access_token") String access_token);

}

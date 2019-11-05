package cn.ifhu.dongjia.net;

import cn.ifhu.dongjia.model.BaseEntity;
import cn.ifhu.dongjia.model.data.GoodsRecommendDataBean;
import cn.ifhu.dongjia.model.data.TopicListDataBean;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface HomeCaseService {
    /**
     * 方案列表
     */
    @GET("/index.php?r=api/default/topic-list")
    public Observable<BaseEntity<TopicListDataBean>> TopicList(@Query("store_id") int store_id, @Query("_uniacid") int uniacid, @Query("_acid") int acid, @Query("page") int page, @Query("limit") int limit, @Query("mch_id") int mch_id);

}

package cn.ifhu.dongjia.net;

import java.util.Map;

import cn.ifhu.dongjia.model.BaseEntity;
import cn.ifhu.dongjia.model.data.GoodsRecommendDataBean;
import cn.ifhu.dongjia.model.data.TopicDataBean;
import cn.ifhu.dongjia.model.data.TopicListDataBean;
import cn.ifhu.dongjia.model.post.CreateUserMessagePostBean;
import cn.ifhu.dongjia.model.post.FavoriteAddPostBean;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface HomeCaseService {
    /**
     * 方案列表
     */
    @GET("/index.php?r=api/default/topic-list")
    public Observable<BaseEntity<TopicListDataBean>> TopicList(@Query("store_id") int store_id, @Query("_uniacid") int uniacid, @Query("_acid") int acid, @Query("page") int page, @Query("limit") int limit, @Query("mch_id") int mch_id);

    /**
     * 方案详情
     */
    @GET("/index.php?r=api/default/topic")
    public Observable<BaseEntity<TopicDataBean>> Topic(@Query("store_id") int store_id, @Query("_uniacid") int uniacid, @Query("_acid") int acid, @Query("id") String id);
//    /**
//     * 联系设计师
//     */
//    @GET("/index.php?r=api/user/create-user-message")
//    public Observable<BaseEntity<Object>> CreateUserMessage(@QueryMap Map<String,Object> urlParam);

    /**
     * 联系设计师
     *
     */
    @POST("/index.php?r=api/user/create-user-message")
    public Observable<BaseEntity<Object>> CreateUserMessagePost(@Body CreateUserMessagePostBean createUserMessagePostBean);

}

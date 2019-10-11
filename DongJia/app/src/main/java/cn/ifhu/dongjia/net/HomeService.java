package cn.ifhu.dongjia.net;

import java.util.Map;

import cn.ifhu.dongjia.model.BaseEntity;
import cn.ifhu.dongjia.model.data.GoodDetailsDataBean;
import cn.ifhu.dongjia.model.data.GoodsRecommendDataBean;
import cn.ifhu.dongjia.model.data.HomeDataBean;
import cn.ifhu.dongjia.model.data.RecommendDataBean;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface HomeService {
    /**
     * 首页接口
     */
    @GET("/index.php?r=api/default/index")
    public Observable<BaseEntity<HomeDataBean>> index(@Query("store_id") int store_id, @Query("_uniacid") int uniacid, @Query("_acid") int acid, @Query("app_key") String app_key, @Query("district") String district);

    /**
     * 懂家臻选
     * @param page
     * @param store_id
     * @param uniacid
     * @param acid
     * @return
     */
    @GET("/index.php?r=api/default/index-recommend")
    public Observable<BaseEntity<RecommendDataBean>> indexRecommend(@Query("page") int page, @Query("store_id") int store_id, @Query("_uniacid") int uniacid, @Query("_acid") int acid);

    /**
     * 商品详情
     */
    @GET("/index.php?r=api/default/goods")
    public Observable<BaseEntity<GoodDetailsDataBean>> Goods(@QueryMap Map<String,Object> urlParam);

    /**
     * 商品详情爆款推荐
     */
    @GET("/index.php?r=api/default/goods-recommend")
    public Observable<BaseEntity<GoodsRecommendDataBean>> GoodsRecommend(@Query("store_id") int store_id, @Query("_uniacid") int uniacid, @Query("_acid") int acid, @Query("goods_id") String goods_id);

}

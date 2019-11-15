package cn.ifhu.dongjia.net;

import java.util.List;
import java.util.Map;

import cn.ifhu.dongjia.model.BaseEntity;
import cn.ifhu.dongjia.model.data.CatListDataBean;
import cn.ifhu.dongjia.model.data.DistrivtListDataBean;
import cn.ifhu.dongjia.model.data.FavoriteListDataBean;
import cn.ifhu.dongjia.model.data.GoodDetailsDataBean;
import cn.ifhu.dongjia.model.data.GoodsAttrInfoDataBean;
import cn.ifhu.dongjia.model.data.GoodsListDataBean;
import cn.ifhu.dongjia.model.data.GoodsRecommendDataBean;
import cn.ifhu.dongjia.model.data.HomeDataBean;
import cn.ifhu.dongjia.model.data.MchArticleDataBean;
import cn.ifhu.dongjia.model.data.MchListDataBean;
import cn.ifhu.dongjia.model.data.OrderPayDataBean;
import cn.ifhu.dongjia.model.data.RecommendDataBean;
import cn.ifhu.dongjia.model.data.SearchDataBean;
import cn.ifhu.dongjia.model.data.ShopDataBean;
import cn.ifhu.dongjia.model.data.SubmitDataBean;
import cn.ifhu.dongjia.model.data.SubmitPreviewDataBean;
import cn.ifhu.dongjia.model.post.FavoriteAddPostBean;
import cn.ifhu.dongjia.model.post.SubmitPostBean;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
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

    /**
     * 商品主页
     */
    @GET("/index.php?r=api/mch/index/shop")
    public Observable<BaseEntity<ShopDataBean>> Shop(@QueryMap Map<String,Object> urlParam);

    /**
     * 商品精品案例
     */
    @GET("/index.php?r=api/default/mch-article")
    public Observable<BaseEntity<List<MchArticleDataBean>>> MchArticle(@Query("store_id") int store_id, @Query("_uniacid") int uniacid, @Query("_acid") int acid, @Query("mch_id") String mch_id);

    /**
     * 商品选择属性
     */
    @GET("/index.php?r=api/default/goods-attr-info")
    public Observable<BaseEntity<GoodsAttrInfoDataBean>> GoodsAttrInfo(@Query("store_id") int store_id, @Query("_uniacid") int uniacid, @Query("_acid") int acid, @Query("goods_id") String goods_id,@Query("attr_list") String attr_list);

    /**
     * 商品搜索
     */
    @GET("/index.php?r=api/default/search")
    public Observable<BaseEntity<SearchDataBean>> search(@Query("store_id") int store_id, @Query("_uniacid") int uniacid, @Query("_acid") int acid, @Query("keyword") String keyword, @Query("page") int page);

    /**
     * 选择城市
     */
    @GET("/index.php?r=api/map/district-list")
    public Observable<BaseEntity<List<DistrivtListDataBean>>> districtList(@Query("store_id") int store_id, @Query("_uniacid") int uniacid, @Query("_acid") int acid);
    /**
     * 分类列表
     */
    @GET("/index.php?r=api/default/cat-list")
    public Observable<BaseEntity<CatListDataBean>> catList(@Query("store_id") int store_id, @Query("_uniacid") int uniacid, @Query("_acid") int acid, @Query("cid") int cid);
    /**
     * 下单预览
     */
    @GET("/index.php?r=api/order/submit-preview")
    public Observable<BaseEntity<SubmitPreviewDataBean>> submitPreview(@QueryMap Map<String,Object> urlParam);
    /**
     * 订单提交
     *
     * @return
     */
    @POST("/index.php?r=api/order/submit")
    public Observable<BaseEntity<SubmitDataBean>> submit(@Body SubmitPostBean submitPostBean);
    /**
     * 添加收藏商品
     *
     */
    @POST("/index.php?r=api/user/favorite-add")
    public Observable<BaseEntity<Object>> favoriteAdd(@Body FavoriteAddPostBean favoriteAddPostBean);

    /**
     * 取消收藏接口
     *
     */
    @POST("/index.php?r=api/user/favorite-remove")
    public Observable<BaseEntity<Object>> favoriteRemove(@Body FavoriteAddPostBean favoriteAddPostBean);
    /**
     * 商品列表
     */
    @GET("/index.php?r=api/default/goods-list")
    public Observable<BaseEntity<GoodsListDataBean>> GoodsList(@QueryMap Map<String,Object> urlParam);

    /**
     * 商家列表
     */
    @GET("/index.php?r=api/default/mch-list")
    public Observable<BaseEntity<MchListDataBean>> MchList(@QueryMap Map<String,Object> urlParam);

    /**
     * 订单支付
     */
    @GET("/index.php?r=api/order/pay-data")
    public Observable<BaseEntity<OrderPayDataBean>> OrderPayData(@QueryMap Map<String,Object> urlParam);


}

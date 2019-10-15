package cn.ifhu.dongjia.net;

import cn.ifhu.dongjia.model.BaseEntity;
import cn.ifhu.dongjia.model.data.UserDataBean;
import cn.ifhu.dongjia.model.get.UserPostBean;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserService {
    @POST("/index.php?r=api/passport/app-login")
    Observable<BaseEntity<UserDataBean>> login(@Body UserPostBean userPostBean);

}

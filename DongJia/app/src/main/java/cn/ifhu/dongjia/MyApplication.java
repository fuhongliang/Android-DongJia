package cn.ifhu.dongjia;

import android.app.Application;
import android.util.Log;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;
import com.tencent.smtt.sdk.QbSdk;

import cn.ifhu.dongjia.base.AppInfo;

import cn.jpush.android.api.JPushInterface;



/**
 * @author fuhongliang
 */
public class MyApplication extends Application {
    public static MyApplication instance;

    public static MyApplication getApplication() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        AppInfo.init(getApplicationContext());
        instance = this;
        initLogger();
//        IrReference.getInstance();
        //在这里初始化
//      Bugtags.start(TAGBUGKEY, this, Bugtags.BTGInvocationEventBubble);
//        ProductLogic.clearDiscountGoods();

        JPushInterface.setDebugMode(true); 	// 设置开启日志,发布时请关闭日志
        JPushInterface.init(this);     		// 初始化 JPush
        Log.e("JPush-registrationID = ", JPushInterface.getRegistrationID(this));

//        initUmeng();
        initXb();



    }

    public void initLogger(){
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                // (Optional) Whether to show thread info or not. Default true
                .showThreadInfo(false)
                // (Optional) How many method line to show. Default 2
                .methodCount(0)
                // (Optional) Hides internal method calls up to offset. Default 5
                .methodOffset(7)
                // (Optional) Global tag for every log. Default PRETTY_LOGGER
                .tag("YIMEI")
                .build();

        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy));
    }

    public void initXb(){
        //搜集本地tbs内核信息并上报服务器，服务器返回结果决定使用哪个内核。

        QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {

            @Override
            public void onViewInitFinished(boolean arg0) {
                //x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。
                Log.d("app", " onViewInitFinished is " + arg0);
            }

            @Override
            public void onCoreInitFinished() {
            }
        };
        //x5内核初始化接口
        QbSdk.initX5Environment(getApplicationContext(),  cb);
    }

}

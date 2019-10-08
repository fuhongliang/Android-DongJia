package cn.ifhu.dongjia.wxapi;

import android.content.Context;
import android.widget.Toast;

import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import cn.ifhu.dongjia.MainActivity;

public class WXLoginUtils {

    private static IWXAPI iwxapi;


    /**
     * 微信登录
     */
    public static void WxLogin(Context context) {
        if (!judgeCanGo(context)){
            return;
        }
        SendAuth.Req req = new SendAuth.Req();
        //授权域 获取用户个人信息则填写snsapi_userinfo
        req.scope = "snsapi_userinfo";
        //用于保持请求和回调的状态 可以任意填写
        req.state = "wechat_sdk_demo_test";
        iwxapi.sendReq(req);
    }

    public static IWXAPI getWXAPI(Context context){
        if (iwxapi == null){
            //通过WXAPIFactory创建IWAPI实例
            iwxapi = WXAPIFactory.createWXAPI(context, MainActivity.APP_ID, false);
            //将应用的appid注册到微信
//            iwxapi.registerApp(MainActivity.APP_ID);
        }
        return iwxapi;
    }

    private static boolean judgeCanGo(Context context){
        getWXAPI(context);
        if (!iwxapi.isWXAppInstalled()) {
            Toast.makeText(context, "请先安装微信应用", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

}

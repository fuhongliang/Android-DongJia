package cn.ifhu.dongjia.wxapi;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.greenrobot.eventbus.EventBus;

import cn.ifhu.dongjia.MainActivity;
import cn.ifhu.dongjia.model.BaseEntity;
import cn.ifhu.dongjia.model.MessageEvent;
import cn.ifhu.dongjia.model.data.UserDataBean;
import cn.ifhu.dongjia.model.post.BaseBean;
import cn.ifhu.dongjia.model.post.UserPostBean;
import cn.ifhu.dongjia.net.BaseObserver;
import cn.ifhu.dongjia.net.RetrofitAPIManager;
import cn.ifhu.dongjia.net.SchedulerUtils;
import cn.ifhu.dongjia.net.UserService;
import cn.ifhu.dongjia.utils.ToastHelper;

import static cn.ifhu.dongjia.utils.Constants.LOGOIN;

public class WXEntryActivity extends Activity implements IWXAPIEventHandler {

    public int WX_LOGIN = 1;

    private IWXAPI iwxapi;

    private SendAuth.Resp resp;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        iwxapi = WXAPIFactory.createWXAPI(this, MainActivity.APP_ID, true);
        //接收到分享以及登录的intent传递handleIntent方法，处理结果
        iwxapi.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        iwxapi.handleIntent(intent, this);//必须调用此句话
    }

    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp baseResp) {
//        Toast.makeText(WXEntryActivity.this, Toast.LENGTH_LONG).show();
        if (baseResp.getType() == WX_LOGIN) {
            //登录回调
            resp = (SendAuth.Resp) baseResp;

            switch (resp.errCode) {
                case BaseResp.ErrCode.ERR_OK:
                    String code = String.valueOf(resp.code);
                    Log.d("微信登录", code);
                    //获取用户信息
                    //TODO：调用登录接口
                    EventBus.getDefault().post(new MessageEvent(LOGOIN,code));
                    break;
                case BaseResp.ErrCode.ERR_AUTH_DENIED://用户拒绝授权
                    break;
                case BaseResp.ErrCode.ERR_USER_CANCEL://用户取消
                    break;
                default:
                    break;
            }
            finish();
        }
    }


}

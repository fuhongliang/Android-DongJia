package cn.ifhu.dongjia.utils;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import cn.ifhu.dongjia.model.UserBean;
import cn.ifhu.dongjia.model.data.UserDataBean;

import static cn.ifhu.dongjia.utils.Constants.MEICHANTINFO;
import static cn.ifhu.dongjia.utils.Constants.USER;

/**
 * UserLogin:保存本地数据
 * saveUser:保存数据
 * getUser:取出数据
 */
public class UserLogic {

    public static void saveUser(UserDataBean dataBean) {
        Logger.d(dataBean);
        if (dataBean != null) {
            Gson gson = new Gson();
            String json = gson.toJson(dataBean);
            IrReference.getInstance().saveString(USER, json);
        }
    }


    public static UserDataBean getUser() {
        String json = IrReference.getInstance().getString(USER, "");
        Log.e("JIGUANG-JPush--", "user String = " + json);
        if (!TextUtils.isEmpty(json)) {
            Gson gson = new Gson();
            UserDataBean mUser = gson.fromJson(json, UserDataBean.class);
            return mUser;
        }
        return null;
    }


    public static void loginOut() {
        IrReference.getInstance().clearSingle(USER);
    }

}

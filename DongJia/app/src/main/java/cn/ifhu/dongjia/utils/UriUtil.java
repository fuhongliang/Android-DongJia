package cn.ifhu.dongjia.utils;

import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import cn.ifhu.dongjia.MyApplication;


public class UriUtil {
    /**
     * 获取uri的文件路径
     *
     * @param contentURI uri内容对象
     * @return 文件路径
     */
    public static String getRealPathFromURI(Uri contentURI) {
        String result;
        Cursor cursor = null;
        try {
            cursor = MyApplication.instance.getContentResolver().query(contentURI, null, null, null, null);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        if (cursor == null) {
            result = contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            result = cursor.getString(idx);
            cursor.close();
        }
        return result;
    }
}

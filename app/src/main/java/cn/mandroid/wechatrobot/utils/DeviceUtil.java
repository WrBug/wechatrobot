package cn.mandroid.wechatrobot.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.os.Environment;
import android.view.Display;
import android.view.WindowManager;

import java.io.File;

import cn.mandroid.wechatrobot.WechatRobotApp;

import static java.security.AccessController.getContext;

/**
 * Created by wrBug on 2017/1/1.
 */

public class DeviceUtil {
    public static String getSDPath() {
        File sdDir = null;
        boolean sdCardExist = Environment.getExternalStorageState()
                .equals(android.os.Environment.MEDIA_MOUNTED); //判断sd卡是否存在
        if (sdCardExist) {
            sdDir = Environment.getExternalStorageDirectory();//获取跟目录
        }
        return sdDir.toString();
    }

    public static Point getScreenInfo(Context c) {
        Point point=new Point();
        WindowManager wm = (WindowManager) c.getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getSize(point);
        return point;
    }
}

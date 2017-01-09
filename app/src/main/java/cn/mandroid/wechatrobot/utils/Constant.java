package cn.mandroid.wechatrobot.utils;

import cn.mandroid.wechatrobot.BuildConfig;

/**
 * Created by wrBug on 2017/1/1.
 */

public class Constant {
    private static class Debug {
        private static String API_SHORT_URL = "http://192.168.1.2/api.php";
    }

    private static class Release {
        private static String API_SHORT_URL = "http://a.mandroid.cn/api.php";
    }

    public static String BAIDU_APPKEY = "4acb1442101e74f6273da3aa8aa4d5a9";

    public static String getApiShortUrl() {
        return BuildConfig.DEBUG ? Debug.API_SHORT_URL : Release.API_SHORT_URL;
    }
}

package cn.mandroid.wechatrobot.utils;

/**
 * Created by wrBug on 2017/1/1.
 */

public class RegexUtil {
    public static String WECHAT_AUTHENTICATION_WAIT_FOR_LOGIN = "window.code=(\\d+);";
    public static String WECHAT_AUTHENTICATION_WAIT_FOR_LOGIN_REDIRECT_UTL ="window.redirect_uri=\"(\\S+?)\";";

}

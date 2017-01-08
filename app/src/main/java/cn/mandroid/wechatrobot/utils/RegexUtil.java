package cn.mandroid.wechatrobot.utils;

/**
 * Created by wrBug on 2017/1/1.
 */

public class RegexUtil {
    public static String WECHAT_AUTHENTICATION_GET_UUID = "(window.QRLogin.code = )([0-9]*); window.QRLogin.uuid = \"(\\S+?)\"";
    public static String WECHAT_AUTHENTICATION_WAIT_FOR_LOGIN = "window.code=(\\d+);";
    public static String WECHAT_AUTHENTICATION_WAIT_FOR_LOGIN_REDIRECT_UTL = "window.redirect_uri=\"(\\S+?)\";";
    public static String WECHAT_NEW_MESSAGE_LOOP = "window.synccheck=\\{retcode:\"(\\d+)\",selector:\"(\\d+)\"\\}";
}

package cn.mandroid.wechatrobot.model.common;

import android.os.Environment;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import cn.mandroid.wechatrobot.WechatRobotApp;
import cn.mandroid.wechatrobot.utils.DeviceUtil;
import cn.mandroid.wechatrobot.utils.FileUtil;
import cn.mandroid.wechatrobot.utils.MLog;
import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by wrBug on 2017/1/1.
 */

public class Api {
    private static OkHttpClient mClinet;
    private static String cookie = "";
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    static {
        System.setProperty("jsse.enableSNIExtension", "false");
        mClinet = new OkHttpClient.Builder().connectTimeout(30000, TimeUnit.SECONDS).readTimeout(20000, TimeUnit.SECONDS).build();
    }

    public static Observable<String> doOtherApiGet(final String url, final Map<String, String> map) {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                ResponseBody body = getResponseBody(url, map);
                try {
                    subscriber.onNext(body.string());
                } catch (IOException e) {
                    MLog.e(e.getMessage());
                }
            }
        });
    }

    public static Observable<File> getFile(final String url, final Map<String, String> params) {
        return Observable.create(new Observable.OnSubscribe<File>() {
            @Override
            public void call(Subscriber<? super File> subscriber) {
                ResponseBody body = getResponseBody(url, params);
                InputStream is = body.byteStream();
                File file = new File(WechatRobotApp.getApplication().getExternalCacheDir(), url.substring(url.lastIndexOf("/")));
                subscriber.onNext(FileUtil.inputstream2File(is, file));
            }
        });

    }

    private static ResponseBody getResponseBody(String url, Map<String, String> map) {
        String params = getParams(map);
        final String finalUrl = url + params;
        try {
            Request request = new Request.Builder().url(finalUrl).addHeader("Cookie", cookie).build();
            Call call = mClinet.newCall(request);
            Response response = call.execute();
            return response.body();
        } catch (IOException e) {
            MLog.e(e.getMessage());
        }
        return null;
    }

    private static String getParams(Map<String, String> params) {
        if (params != null && !params.isEmpty()) {
            StringBuilder builder = new StringBuilder("?");
            for (Map.Entry<String, String> entry : params.entrySet()) {
                builder.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
            }
            builder.deleteCharAt(builder.length() - 1);
            return builder.toString();
        }
        return "";
    }
}

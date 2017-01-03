package cn.mandroid.wechatrobot.model.common;

import android.os.Environment;
import android.support.annotation.Nullable;

import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import cn.mandroid.wechatrobot.WechatRobotApp;
import cn.mandroid.wechatrobot.model.entity.BaseRequestBean;
import cn.mandroid.wechatrobot.utils.DeviceUtil;
import cn.mandroid.wechatrobot.utils.FileUtil;
import cn.mandroid.wechatrobot.utils.MLog;
import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by wrBug on 2017/1/1.
 */

public class Api {
    private static OkHttpClient mClinet;
    private static String sCookie = "";
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    static {
        System.setProperty("jsse.enableSNIExtension", "false");
        mClinet = new OkHttpClient.Builder().connectTimeout(30000, TimeUnit.SECONDS).readTimeout(20000, TimeUnit.SECONDS).build();
    }

    public static Observable<String> doOtherApiGet(final String url, final HttpBody.Query query) {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                ResponseBody body = getResponseBody(url, query);
                if (body == null) {
                    subscriber.onError(new NullPointerException("ResponseBody is null"));
                    return;
                }
                try {
                    subscriber.onNext(body.string());
                } catch (IOException e) {
                    subscriber.onError(e);
                    MLog.e(e.getMessage());
                }
            }
        });
    }

    public static Observable<BaseRequestBean> doOtherApiGet(final String url, @Nullable final HttpBody.Query map, final boolean getSetCookie) {
        return Observable.create(new Observable.OnSubscribe<BaseRequestBean>() {
            @Override
            public void call(Subscriber<? super BaseRequestBean> subscriber) {
                ResponseBody body = getResponseBody(url, map, getSetCookie);
                if (body == null) {
                    subscriber.onError(new NullPointerException("ResponseBody is null"));
                    return;
                }
                try {
                    BaseRequestBean baseRequestBean = new BaseRequestBean();
                    baseRequestBean.setCookie(sCookie);
                    baseRequestBean.setResult(body.string());
                    subscriber.onNext(baseRequestBean);
                } catch (IOException e) {
                    subscriber.onError(e);
                    MLog.e(e.getMessage());
                }
            }
        });
    }

    public static Observable<File> getFile(final String url, final HttpBody.Query params) {
        return Observable.create(new Observable.OnSubscribe<File>() {
            @Override
            public void call(Subscriber<? super File> subscriber) {
                subscriber.onNext(downloadAsFile(url, params));
            }
        });

    }

    public static InputStream downloadAsInputStream(String url, final HttpBody.Query params) {
        ResponseBody body = getResponseBody(url, params);
        InputStream is = body.byteStream();
        return is;
    }

    public static File downloadAsFile(String url, final HttpBody.Query params) {
        File file = new File(WechatRobotApp.getApplication().getExternalCacheDir(), url.substring(url.lastIndexOf("/")));
        return FileUtil.inputstream2File(downloadAsInputStream(url, params), file);
    }

    private static ResponseBody getResponseBody(String url, HttpBody.Query query, boolean getSetCookie) {
        String params = query.getParams();
        final String finalUrl = url + params;
        try {
            Request request = new Request.Builder().url(finalUrl).addHeader("Cookie", sCookie).build();
            Call call = mClinet.newCall(request);
            Response response = call.execute();
            if (getSetCookie) {
                setCookie(response);
            }
            return response.body();
        } catch (IOException e) {
            MLog.e(e.getMessage());
        }
        return null;
    }

    public static void setCookie(String cookie) {
        sCookie = cookie;
    }

    private static void setCookie(Response response) {
        List<String> cookies = response.headers("set-cookie");
        if (cookies != null && !cookies.isEmpty()) {
            StringBuilder builder = new StringBuilder();
            for (String s : cookies) {
                builder.append(s).append(";");
            }
            sCookie = builder.toString();
        }
    }

    private static ResponseBody getResponseBody(String url, HttpBody.Query query) {
        return getResponseBody(url, query, false);
    }


    public static Observable<String> doOtherApiPost(final String url, final HttpBody.Parameter parameter) {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                try {
                    RequestBody body = RequestBody.create(JSON, parameter.toJson());
                    Request request = new Request.Builder()
                            .url(url)
                            .addHeader("Cookie", sCookie)
                            .post(body)
                            .build();
                    Response response = mClinet.newCall(request).execute();
                    subscriber.onNext(response.body().string());
                } catch (IOException e) {
                    MLog.e(e.getMessage());
                    subscriber.onError(e);
                }
            }
        });
    }
}

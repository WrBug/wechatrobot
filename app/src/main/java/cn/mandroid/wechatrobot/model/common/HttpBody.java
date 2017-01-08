package cn.mandroid.wechatrobot.model.common;

import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import cn.mandroid.wechatrobot.model.entity.BaseRequestBean;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by wrBug on 2017/1/3.
 */

public class HttpBody {
    public static class Query extends HashMap<String, String> {
        public Query() {
            super();
        }

        public String getParams() {
            StringBuilder builder = new StringBuilder("?");
            for (Map.Entry<String, String> entry : entrySet()) {
                builder.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
            }
            builder.deleteCharAt(builder.length() - 1);
            return builder.toString();
        }

        @Deprecated
        @Override
        public String put(String key, String value) {
            throw new RuntimeException("put(K,V) method invalid");
        }

        /**
         * 其他api地址请求
         *
         * @param url
         * @param subscriber
         */
        public void doOtherApiGet(String url, Subscriber<String> subscriber) {
            Api.doOtherApiGet(url, this).subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(subscriber);
        }

        /**
         * 其他api地址请求 带cookie返回
         *
         * @param url
         * @param getSetCookie
         * @param subscriber
         */
        public void doOtherApiGet(String url, boolean getSetCookie, Subscriber<BaseRequestBean> subscriber) {
            Api.doOtherApiGet(url, this, getSetCookie).subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(subscriber);
        }

        /**
         * 异步文件下载
         *
         * @param url
         * @param subscriber
         */
        public void getFile(String url, Subscriber<File> subscriber) {
            Api.getFile(url, this).subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(subscriber);
        }

        /**
         * 同步获取数据
         *
         * @param url
         * @return
         */
        public String doGet(String url) {
            return Api.doGet(url, this, false);
        }

        public InputStream downloadAsInputStream(String url) {
            return Api.downloadAsInputStream(url, this);
        }

        public File downloadAsFile(String url) {
            return Api.downloadAsFile(url, this);
        }

        /**
         * 添加get请求参数
         *
         * @param key
         * @param value
         * @return
         */
        public Query addQuery(String key, String value) {
            super.put(key, value);
            return this;
        }
    }

    public static class Parameter extends HashMap<String, Object> {
        public Parameter() {
        }

        public void doOtherApiPost(String url, Subscriber<String> subscriber) {
            Api.doOtherApiPost(url, this).subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(subscriber);
        }

        public String toJson() {
            return new GsonBuilder().create().toJson(this);
        }

        @Deprecated
        @Override
        public Object put(String key, Object value) {
            throw new RuntimeException("put(K,V) method invalid");
        }

        public Parameter addParameter(String key, Object value) {
            super.put(key, value);
            return this;
        }
    }
}

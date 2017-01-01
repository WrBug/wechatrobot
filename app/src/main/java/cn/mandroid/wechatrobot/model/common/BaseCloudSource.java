package cn.mandroid.wechatrobot.model.common;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import cn.mandroid.wechatrobot.model.entity.BaseRequestBean;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by wrBug on 2017/1/1.
 */

public class BaseCloudSource {
    protected Gson mGson;

    public BaseCloudSource() {
        mGson = new GsonBuilder().create();
    }

    protected GetQequestBuilder getQequestBuilder() {
        return new GetQequestBuilder();
    }

    protected PostRequestBuilder postRequestBuilder() {
        return new PostRequestBuilder();
    }

    protected static class GetQequestBuilder {
        private Query mHttpGetQuery;

        public GetQequestBuilder() {
            mHttpGetQuery = new Query();
        }

        public Query addQuery(String key, String value) {
            mHttpGetQuery.addQuery(key, value);
            return mHttpGetQuery;
        }

        public Query noQuery() {
            return mHttpGetQuery;
        }
    }

    public static class Query extends HashMap<String, String> {
        private Query() {
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
         * 文件下载
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

    protected static class PostRequestBuilder {
        private Parameter mHttpPostParameter;

        public PostRequestBuilder() {
            mHttpPostParameter = new Parameter();
        }

        public Parameter addParameter(String key, Object value) {
            mHttpPostParameter.addParameter(key, value);
            return mHttpPostParameter;
        }
    }

    public static class Parameter extends HashMap<String, Object> {
        private Parameter() {
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

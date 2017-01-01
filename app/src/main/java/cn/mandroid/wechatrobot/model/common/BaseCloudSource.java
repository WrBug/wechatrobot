package cn.mandroid.wechatrobot.model.common;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.util.HashMap;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by wrBug on 2017/1/1.
 */

public class BaseCloudSource {
    protected GetQequestBuilder mGetQequestBuilder;
    protected Gson mGson;

    public BaseCloudSource() {
        mGetQequestBuilder = new GetQequestBuilder();
        mGson = new GsonBuilder().create();
    }

    protected class GetQequestBuilder {
        private Query mHttpGetQuery;

        public GetQequestBuilder() {
            mHttpGetQuery = new Query();
        }

        public Query addQuery(String key, String value) {
            mHttpGetQuery.addQuery(key, value);
            return mHttpGetQuery;
        }

    }

    public static class Query extends HashMap<String, String> {
        private Query() {
            super();
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
}

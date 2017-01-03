package cn.mandroid.wechatrobot.model.common;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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
        private HttpBody.Query mHttpGetQuery;

        public GetQequestBuilder() {
            mHttpGetQuery = new HttpBody.Query();
        }

        public HttpBody.Query addQuery(String key, String value) {
            mHttpGetQuery.addQuery(key, value);
            return mHttpGetQuery;
        }

        public HttpBody.Query noQuery() {
            return mHttpGetQuery;
        }
    }


    protected static class PostRequestBuilder {
        private HttpBody.Parameter mHttpPostParameter;

        public PostRequestBuilder() {
            mHttpPostParameter = new HttpBody.Parameter();
        }

        public HttpBody.Parameter addParameter(String key, Object value) {
            mHttpPostParameter.addParameter(key, value);
            return mHttpPostParameter;
        }
    }

}

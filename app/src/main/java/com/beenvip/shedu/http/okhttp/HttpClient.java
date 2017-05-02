package com.beenvip.shedu.http.okhttp;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.beenvip.shedu.http.okhttp.request.ByteRequest;
import com.beenvip.shedu.http.okhttp.request.GsonRequest;
import com.beenvip.shedu.http.okhttp.request.JsonArrayRequest;
import com.beenvip.shedu.http.okhttp.request.JsonObjectRequest;
import com.beenvip.shedu.http.okhttp.stack.OkHttp3Stack;
import com.beenvip.shedu.utils.LalaLog;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import okhttp3.OkHttpClient;

public class HttpClient implements IHttpClient {

    private static HttpClient mInstance;

    private final RequestQueue mRequestQueue;
    private final Context mContext;

    private HttpClient(Context context) {
        mContext = context;
//        使用okhttp请求网络
//        InputStream p12Keystore = context.getResources().openRawResource(R.raw.safepayclient);
//        InputStream trustKeyStore = context.getResources().openRawResource(R.raw.safepayserver);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
//        if (ZZTConfig.isUseHTTPS) {//增加双向认证
//            builder.sslSocketFactory(SSLCustomSocketFactory.getSocketFactory(p12Keystore, "1234567890", trustKeyStore));
//        }
        mRequestQueue = Volley.newRequestQueue(context, new OkHttp3Stack(builder.build()));

    }

    /**
     * 这里使用Application的Context
     *
     * @param context
     * @return
     */
    public static HttpClient getInstance(Context context) {
        if (null == mInstance) {
            synchronized (HttpClient.class) {
                if (null == mInstance) {
                    mInstance = new HttpClient(context);
                }
            }
        }
        return mInstance;
    }

    /**
     * 添加请求
     *
     * @param request
     */
    public void addRequest(Request request, Object tag) {
        if (tag != null) {
            request.setTag(tag);
        }
        mRequestQueue.add(request);
    }

    @Override
    public Request request(HttpRequest httpRequest, HttpListener<String> listener, Object tag) {
        return null;
    }

    /**
     * 取消请求
     *
     * @param tag
     */
    public void cancelRequest(Object tag) {
        mRequestQueue.cancelAll(tag);
    }

    @Override
    public Request byteRequest(HttpRequest httpRequest, HttpListener<byte[]> listener, Object tag) {
        ByteRequest request = new ByteRequest(httpRequest, listener);
        LalaLog.d(LalaLog.SSX_TAG, "url:" + request.getUrl());
        addRequest(request, tag);
        return request;
    }

    @Override
    public Request jsonObjectRequest(HttpRequest httpRequest, HttpListener<JSONObject> listener, Object tag) {
        JsonObjectRequest request = new JsonObjectRequest(httpRequest, listener);
        addRequest(request, tag);
        return request;
    }

    @Override
    public Request jsonArrayRequest(HttpRequest httpRequest, HttpListener<JSONArray> listener, Object tag) {
        JsonArrayRequest request = new JsonArrayRequest(httpRequest, listener);
        addRequest(request, tag);
        return request;
    }

    @Override
    public <T> Request gsonRequest(Class<T> tClass, HttpRequest httpRequest, HttpListener<T> listener, Object tag) {
        GsonRequest<T> request = new GsonRequest<T>(tClass, httpRequest, listener);
        addRequest(request, tag);
        return request;
    }

    @Override
    public <T> Request gsonRequest(TypeToken<T> typeToken, HttpRequest httpRequest, HttpListener<T> listener, Object tag) {
        GsonRequest<T> request = new GsonRequest<T>(typeToken, httpRequest, listener);
        addRequest(request, tag);
        return request;
    }

}

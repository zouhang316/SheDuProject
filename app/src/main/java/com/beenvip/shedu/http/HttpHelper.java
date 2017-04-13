package com.beenvip.shedu.http;

import android.content.Context;
import android.os.AsyncTask;

import com.beenvip.shedu.base.HttpBaseResponseBean;
import com.beenvip.shedu.fastjson.FastJsonHelper;
import com.beenvip.shedu.utils.LalaLog;

import java.util.HashMap;

/**
 * @author sxshi on 2017/4/13.
 *         email:emotiona_xiaoshi@126.com
 *         describe:网络请求业务操作类
 */

public class HttpHelper {
    private Context mContext;

    public HttpHelper(Context context) {
        this.mContext = context;
    }

    public static final int DEFAULT_TIME_OUT = 60 * 1000; //默认网络请求超时时间

    public void asyncRequest(final String action, String url, final HashMap<String, String> paramers, final Class<? extends HttpBaseResponseBean> cls, final HttpListener listener) {
        LalaLog.d(LalaLog.SSX_TAG, "-----------------------------request start------------------------");
        LalaLog.d(LalaLog.SSX_TAG, "url:" + url);
        LalaLog.d(LalaLog.SSX_TAG, paramers.toString());
        LalaLog.d(LalaLog.SSX_TAG, commReqHeader().toString());
        LalaLog.d(LalaLog.SSX_TAG, "-----------------------------request end------------------------");
        new AsyncTask<String, Void, String>() {
            @Override
            protected void onPostExecute(String result) {
                LalaLog.i(LalaLog.SSX_TAG, "-----------------------------reponse start------------------------");
                LalaLog.json(LalaLog.SSX_TAG, result);
                LalaLog.i(LalaLog.SSX_TAG, "-----------------------------response end------------------------");
                HttpBaseResponseBean httpBaseResponseBean = FastJsonHelper.getObject(result, cls);
                if (httpBaseResponseBean.isResult()) {
                    listener.onSuccess(httpBaseResponseBean);
                } else {
                    listener.onFailed(httpBaseResponseBean);
                }
            }

            @Override
            protected String doInBackground(String... params) {
                return HttpUrlconnectionUtil.postJson(mContext, params[0], commReqHeader(), paramers, DEFAULT_TIME_OUT);
            }
        }.execute(url);
    }

    /**
     * 网络请求共同的网络请求头统一封装
     *
     * @return 返回网络请求头
     */
    public HashMap<String, String> commReqHeader() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("Connection","keep-alive");
        hashMap.put("Content-Type","application/json");
        return hashMap;
    }
}

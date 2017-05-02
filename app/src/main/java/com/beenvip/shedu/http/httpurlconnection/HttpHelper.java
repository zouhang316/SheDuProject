package com.beenvip.shedu.http.httpurlconnection;

import android.content.Context;
import android.os.AsyncTask;

import com.beenvip.shedu.base.HttpBaseResponseBean;
import com.beenvip.shedu.fastjson.FastJsonHelper;
import com.beenvip.shedu.utils.LalaLog;
import com.beenvip.shedu.view.WaitDialog;

import java.util.HashMap;
import java.util.Map;

/**
 * @author sxshi on 2017/4/13.
 *         email:emotiona_xiaoshi@126.com
 *         describe:网络请求业务操作类
 */

public class HttpHelper {
    private Context mContext;
    private static WaitDialog mWaitDialog;
    public HttpHelper(Context context) {
        this.mContext = context;
        mWaitDialog=new WaitDialog(context);
    }

    public static final int DEFAULT_TIME_OUT = 20 * 1000; //默认网络请求超时时间

    /**
     * 网络请求、异步操作
     *
     * @param url      请求URL
     * @param paramers 请求参数
     * @param cls      需要解析的Bean
     * @param listener 回调监听
     */
    public void asyncPostRequest(String url, final HashMap<String, String> paramers, final Class<? extends HttpBaseResponseBean> cls, final HttpCallBackListener listener, boolean isLoading) {
        LalaLog.d(LalaLog.SSX_TAG, "-----------------------------request start------------------------");
        LalaLog.d(LalaLog.SSX_TAG, "url:" + url);
        LalaLog.d(LalaLog.SSX_TAG, "requestBody:" + paramers.toString());
        LalaLog.d(LalaLog.SSX_TAG, "requestHeader:" + commReqHeader().toString());
        LalaLog.d(LalaLog.SSX_TAG, "-----------------------------request end------------------------");
        showProgress(isLoading);
        new AsyncTask<String, Void, String>() {
            @Override
            protected void onPostExecute(String result) {
                LalaLog.i(LalaLog.SSX_TAG, "-----------------------------reponse start------------------------");
                LalaLog.json(LalaLog.SSX_TAG, result);
                LalaLog.i(LalaLog.SSX_TAG, "-----------------------------response end------------------------");
                HttpBaseResponseBean httpBaseResponseBean = FastJsonHelper.getObject(result, cls);
                if (httpBaseResponseBean.isResult()) {
                    dismiss();
                    listener.onSuccess(httpBaseResponseBean);
                } else {
                    dismiss();
                    listener.onFailed(httpBaseResponseBean);
                }
            }

            @Override
            protected String doInBackground(String... params) {
                return HttpUrlconnectionUtil.postJson(mContext, HttpMethod.POST, params[0], commReqHeader(), paramers, DEFAULT_TIME_OUT);
            }
        }.execute(url);
    }

    /**
     * 网络请求、异步操作
     *
     * @param url      请求URL
     * @param paramers 请求参数
     * @param cls      需要解析的Bean
     * @param listener 回调监听
     */
    public void asyncGetRequest(String url, final HashMap<String, String> paramers, final Class<? extends HttpBaseResponseBean> cls, final HttpCallBackListener listener, boolean isLoading) {
        LalaLog.d(LalaLog.SSX_TAG, "-----------------------------request start------------------------");
        LalaLog.d(LalaLog.SSX_TAG, "url:" + url);
        LalaLog.d(LalaLog.SSX_TAG, "requestBody:" + paramers.toString());
        LalaLog.d(LalaLog.SSX_TAG, "requestHeader:" + commReqHeader().toString());
        LalaLog.d(LalaLog.SSX_TAG, "-----------------------------request end------------------------");
        showProgress(isLoading);
        new AsyncTask<String, Void, String>() {
            @Override
            protected void onPostExecute(String result) {
                LalaLog.d(LalaLog.SSX_TAG, "-----------------------------reponse start------------------------");
                LalaLog.json(LalaLog.SSX_TAG, result);
                LalaLog.d(LalaLog.SSX_TAG, "-----------------------------response end------------------------");
                HttpBaseResponseBean httpBaseResponseBean = FastJsonHelper.getObject(result, cls);
                if (httpBaseResponseBean.isResult()) {
                    dismiss();
                    listener.onSuccess(httpBaseResponseBean);
                } else {
                    dismiss();
                    listener.onFailed(httpBaseResponseBean);
                }
            }

            @Override
            protected String doInBackground(String... params) {
                return HttpUrlconnectionUtil.postJson(mContext, HttpMethod.GET, params[0], commReqHeader(), null, DEFAULT_TIME_OUT);
            }
        }.execute(getUrl(url, paramers));
    }

    /**
     * get请求
     *
     * @param url 请求地址
     * @param map 请求参数
     * @return 返回拼接后的URL
     */
    public static String getUrl(String url, Map<String, String> map) {
        if (null == map || map.isEmpty()) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        sb.append(url).append("&");
        for (String key : map.keySet()) {
            sb.append(key + "=" + map.get(key) + "&");
        }
        return sb.substring(0, sb.length() - 1);
    }

    /**
     * 网络请求共同的网络请求头统一封装
     *
     * @return 返回网络请求头
     */
    public HashMap<String, String> commReqHeader() {
        // TODO: 2017/4/13 请求头的封装需要后后台联调
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("Connection", "keep-alive");
        hashMap.put("Content-Type", "application/json");
        return hashMap;
    }
    private static void showProgress(boolean isLoading) {
        if (isLoading) {
            if (mWaitDialog.isShowing()) {
                dismiss();
            }
            mWaitDialog.setCanceledOnTouchOutside(false);
            mWaitDialog.show();
        }
    }

    private static void dismiss() {
        if (mWaitDialog != null )
            mWaitDialog.dismiss();
    }


}

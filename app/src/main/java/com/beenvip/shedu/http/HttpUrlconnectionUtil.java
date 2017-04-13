package com.beenvip.shedu.http;

import android.content.Context;
import android.support.annotation.NonNull;

import com.alibaba.fastjson.JSON;
import com.beenvip.shedu.MyApplication;
import com.beenvip.shedu.utils.LalaLog;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

import static java.net.HttpURLConnection.HTTP_BAD_REQUEST;
import static java.net.HttpURLConnection.HTTP_INTERNAL_ERROR;
import static java.net.HttpURLConnection.HTTP_REQ_TOO_LONG;
import static java.net.HttpURLConnection.HTTP_VERSION;

/**
 * @author sxshi on 2017/4/13.
 *         email:emotiona_xiaoshi@126.com
 *         describe:Http请求工具类
 */

public class HttpUrlconnectionUtil {
    /***
     * 向服务器请求数据
     *
     * @param context 上下文
     * @param host    http地址
     * @param headers 请求头
     * @param params  请求参数
     * @param timeout 超时时间
     * @return 返回请求服务器后的数据 数据格式JSON
     */
    public static String postJson(Context context, int method, String host, HashMap<String, String> headers, HashMap<String, String> params, int timeout) {
        HashMap<String, Object> errMsgMap = new HashMap<>();
        HttpURLConnection httpsURLConnection = null;
        int responseCode = -1;
        String response = null;
        try {
            httpsURLConnection = getHttpsURLConnection(context, timeout, host, method);
            if (headers != null && !headers.isEmpty()) {
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    String name = entry.getKey();
                    String value = entry.getValue();
                    httpsURLConnection.setRequestProperty(name, value);
                }
            }
            httpsURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            //获取输入流向服务器写入数据
            DataOutputStream request = new DataOutputStream(httpsURLConnection.getOutputStream());
            if (params != null && !params.isEmpty()) {
                Iterator<Map.Entry<String, String>> p = params.entrySet().iterator();
                StringBuilder sb = new StringBuilder();
                while (p.hasNext()) {
                    Map.Entry<String, String> entry = p.next();
                    String name = entry.getKey();
                    String value = entry.getValue();
                    if (sb.length() > 0) {
                        sb.append("&");
                    }
                    sb.append(name).append("=").append(URLEncoder.encode(value, "UTF-8"));
                }
                byte[] reqData = sb.toString().getBytes("UTF-8");
                request.write(reqData);
            }
            responseCode = httpsURLConnection.getResponseCode();
            if (responseCode == HttpsURLConnection.HTTP_OK || responseCode == HttpsURLConnection.HTTP_PARTIAL) {
                InputStream inputStream = httpsURLConnection.getInputStream();
                response = inputStringToString(inputStream);
            } else if (responseCode >= HttpURLConnection.HTTP_MULT_CHOICE && responseCode <= HttpURLConnection.HTTP_USE_PROXY) {
                errMsgMap.put("result", false);
                errMsgMap.put("ErrorCode", responseCode + "");
                errMsgMap.put("ErrorInfo", "Relocation/Redirection");
                response = JSON.toJSONString(errMsgMap);
            } else if (responseCode >= HTTP_BAD_REQUEST && responseCode <= HTTP_REQ_TOO_LONG) {
                errMsgMap.put("result", false);
                errMsgMap.put("ErrorCode", responseCode + "");
                errMsgMap.put("ErrorInfo", "Client Error");
                response = JSON.toJSONString(errMsgMap);
            } else if (responseCode >= HTTP_INTERNAL_ERROR && responseCode <= HTTP_VERSION) {
                errMsgMap.put("result", false);
                errMsgMap.put("ErrorCode", responseCode + "");
                errMsgMap.put("ErrorInfo", "Server Error");
                response = JSON.toJSONString(errMsgMap);
            } else {
                errMsgMap.put("result", false);
                errMsgMap.put("ErrorCode", responseCode + "");
                errMsgMap.put("ErrorInfo", "unknown exception");
                response = JSON.toJSONString(errMsgMap);
            }
//            LalaLog.d(LalaLog.SSX_TAG, "content:" + httpsURLConnection.getContentLength());
            request.flush();
            request.close();
        } catch (SocketTimeoutException e) {
            errMsgMap.put("result", false);
            errMsgMap.put("ErrorCode", responseCode + "");
            errMsgMap.put("ErrorInfo", "网络连接超时！");
            response = JSON.toJSONString(errMsgMap);
            return response;
        } catch (IOException e) {
            errMsgMap.put("result", false);
            errMsgMap.put("ErrorCode", responseCode + "");
            errMsgMap.put("ErrorInfo", "IOException");
            response = JSON.toJSONString(errMsgMap);
            return response;
        } finally {
            if (httpsURLConnection != null) httpsURLConnection.disconnect();
        }
        return response;
    }

    /**
     * @param context 上下文
     * @param host    主机地址
     * @param headers 请求头信息
     * @param params  请求参数
     * @param timeout 超时时间
     * @param bytes   上传的额图片字节数组
     * @return 上传图片返回数据集合 数据格式json
     */
    public static String uploadByteJson(Context context, int method, String host, Hashtable<String, String> headers, Hashtable<String, String> params, int timeout, byte[] bytes) {
        LalaLog.d(LalaLog.SSX_TAG, "url:" + host);
        LalaLog.d(LalaLog.SSX_TAG, "requestHeaders:" + headers.toString());
        LalaLog.d(LalaLog.SSX_TAG, "paramers:" + params.toString());
        LalaLog.d(LalaLog.SSX_TAG, "byte:" + bytes.length);
        int responseCode = -1;
        String response = null;
        HashMap<String, Object> errMsgMap = new HashMap<>();
        HttpURLConnection httpsURLConnection = null;
        try {
            httpsURLConnection = getHttpsURLConnection(context, timeout, host, method);
            String uuid = UUID.randomUUID().toString();
            //添加请求头信息
            if (!headers.isEmpty()) {
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    String name = entry.getKey();
                    String value = entry.getValue();
                    //避免设置了ContentType导致Content-type不正确。
                    //正确的content-type应该形似这种：
                    // Content-Type: multipart/form-data; boundary=Xd2JVvE5KcpPeWkz9U44KHfa7tOuQHJPcUVL
                    //并且由multipartEntity来设置
                    if ("Content-Type".equals(name)) {
                        value = "multipart/form-data; boundary=" + uuid;
                    }
                    httpsURLConnection.setRequestProperty(name, value);
                }
            }
            //获取输入流向服务器写入数据
            DataOutputStream request = new DataOutputStream(httpsURLConnection.getOutputStream());
            //为了支持二进制上传
            if (!params.isEmpty()) {
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    String name = entry.getKey();
                    String value = entry.getValue();
                    String entity = getStringEntityHeader(uuid, name) + value + "\r\n";
                    request.write(entity.getBytes("UTF-8"));
                }
            }
            request.write(getBytesEntityHeader(uuid, "pic", "byte").getBytes("UTF-8"));
            request.write(bytes);
            request.write(getEnd(uuid).getBytes("UTF-8"));
            responseCode = httpsURLConnection.getResponseCode();
            if (responseCode == HttpsURLConnection.HTTP_OK || responseCode == HttpsURLConnection.HTTP_PARTIAL) {
                InputStream inputStream = httpsURLConnection.getInputStream();
                response = inputStringToString(inputStream);
            } else if (responseCode >= HttpURLConnection.HTTP_MULT_CHOICE && responseCode <= HttpURLConnection.HTTP_USE_PROXY) {
                errMsgMap.put("result", false);
                errMsgMap.put("ErrorCode", responseCode + "");
                errMsgMap.put("ErrorInfo", "Relocation/Redirection");
                response = JSON.toJSONString(errMsgMap);
            } else if (responseCode >= HTTP_BAD_REQUEST && responseCode <= HTTP_REQ_TOO_LONG) {
                errMsgMap.put("result", false);
                errMsgMap.put("ErrorCode", responseCode + "");
                errMsgMap.put("ErrorInfo", "Client Error");
                response = JSON.toJSONString(errMsgMap);
            } else if (responseCode >= HTTP_INTERNAL_ERROR && responseCode <= HTTP_VERSION) {
                errMsgMap.put("result", false);
                errMsgMap.put("ErrorCode", responseCode + "");
                errMsgMap.put("ErrorInfo", "Server Error");
                response = JSON.toJSONString(errMsgMap);
            } else {
                errMsgMap.put("result", false);
                errMsgMap.put("ErrorCode", responseCode + "");
                errMsgMap.put("ErrorInfo", "unknown exception");
                response = JSON.toJSONString(errMsgMap);
            }
//            LalaLog.d(LalaLog.SSX_TAG, "content:" + httpsURLConnection.getContentLength());
            request.flush();
            request.close();
        } catch (SocketTimeoutException e) {
            errMsgMap.put("result", false);
            errMsgMap.put("ErrorCode", responseCode + "");
            errMsgMap.put("ErrorInfo", "网络连接超时！");
            response = JSON.toJSONString(errMsgMap);
            return response;
        } catch (IOException e) {
            errMsgMap.put("result", false);
            errMsgMap.put("ErrorCode", responseCode + "");
            errMsgMap.put("ErrorInfo", "IOException");
            response = JSON.toJSONString(errMsgMap);
            return response;
        } finally {
            if (httpsURLConnection != null) httpsURLConnection.disconnect();
        }
        return response;
    }

    /***
     * 统一封装HttpsURLConnection
     *
     * @param context 上下文
     * @param timeout 超时时间
     * @param host    主机地址
     * @return 根据需求返回双通道认证的HttpURLConnection
     * @throws IOException IO异常
     */
    @NonNull
    private static HttpURLConnection getHttpsURLConnection(Context context, int timeout, String host, int method) throws IOException {
        URL url = new URL(host);
        HttpURLConnection httpURLConnection = null;
        if (MyApplication.isUseHTTPS) {//双向认证，
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
//            InputStream p12Keystore = context.getApplicationContext().getResources().openRawResource(R.raw.safepayclient);
//            InputStream trustKeyStore = context.getApplicationContext().getResources().openRawResource(R.raw.safepayserver);
            httpsURLConnection.setHostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
//            httpsURLConnection.setSSLSocketFactory(SSLCustomSocketFactory.getSocketFactory(p12Keystore, "1234567890", trustKeyStore));//实现双向认证
            httpURLConnection = httpsURLConnection;
        } else {
            httpURLConnection = (HttpURLConnection) url.openConnection();
        }
        httpURLConnection.setReadTimeout(timeout);
        if (HttpMethod.POST == method) httpURLConnection.setRequestMethod("POST");
        else if (HttpMethod.GET == method) httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setDoInput(true);
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setUseCaches(false);
        return httpURLConnection;
    }

    /**
     * @param uuid 随机字符串
     * @param name header value
     * @return 按照一定的格式组织请求头，实现能上传的数据格式
     */
    private static String getStringEntityHeader(String uuid, String name) {
        return "--" + uuid + "\r\n" + "Content-Disposition: form-data; name=\"" + name + "\"\r\n\r\n";
    }

    /**
     * @param uuid     随机字符串
     * @param name     header value
     * @param fileName 文件名
     * @return 按照一定的格式组织请求头，实现能上传的数据格式
     */
    private static String getBytesEntityHeader(String uuid, String name, String fileName) {
        return "--" + uuid + "\r\n" + "Content-Disposition: form-data; name=\"" + name + "\"; filename=\"" + fileName + "\"" + "\r\n" + "Content-Type: application/octet-stream\r\n\r\n";
    }

    /**
     * @param uuid 随机字符串
     * @return 返回结束字符串
     */
    private static String getEnd(String uuid) {
        return "\r\n" + "--" + uuid + "--";
    }

    /***
     * 获取服务器数据 转换为字符串
     *
     * @param inputStream 输入流
     * @return 返回转换后的数据
     */
    public static String inputStringToString(InputStream inputStream) {
        String resultData;      //存储处理结果
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] data = new byte[1024];
        int len = 0;
        try {
            while ((len = inputStream.read(data)) != -1) {
                byteArrayOutputStream.write(data, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        resultData = new String(byteArrayOutputStream.toByteArray());
        LalaLog.d(LalaLog.SSX_TAG, "response:" + resultData);
        return resultData;
    }
}

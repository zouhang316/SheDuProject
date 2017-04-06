package com.beenvip.shedu.httputils;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;

import com.beenvip.shedu.utils.LalaLog;
import com.beenvip.shedu.utils.WaitDialog;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by Administrator on 2017/2/23 0023.
 */

public class EternityHTTPUtil {
    public static final String GET = "GET";
    public static final String POST = "POST";
    public static final int NETWORK_ERROR = 0;
    public static final int NOT_A_JSON = 1;
    private static final int CONN_OK = 200;
    private Thread connThread;
    private OutputStream os;
    private InputStream is;
    private ByteArrayOutputStream baos;
    private HttpURLConnection conn;
    private Handler hdl_callback;
    private static WaitDialog waitDialog;

    public void createConn(final String url, final String method,final HashMap<String, String> params, final IConnCreateCallback iConnCreateCallback) {


        //↓如果需要调试，可以打开输出日志
       LalaLog.i("url", url);

        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    URL connUrl = new URL(url);
                    conn = (HttpURLConnection) connUrl.openConnection();
                    conn.setRequestMethod(method);
                    conn.setConnectTimeout(5000);
                    conn.setReadTimeout(5000);
                    if (conn.getRequestMethod().equals("POST")) {
                        if (params != null && params.size() > 0) {
                            StringBuffer sb_params = new StringBuffer();
                            Iterator<String> itKey = params.keySet().iterator();
                            for (int i = 0; i < params.size(); i++) {
                                if (i != 0) {
                                    sb_params.append("&");
                                }
                                String key = itKey.next();
                                sb_params.append(key);
                                sb_params.append("=");
                                sb_params.append(params.get(key));
                            }
                            os = conn.getOutputStream();
                            os.write(sb_params.toString().getBytes());
                            os.flush();
                        }
                    }
                    iConnCreateCallback.onConnCreated(conn);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }

    public void startConnForJSON( final int what,final IJSONCallback callback) {
        startConn(new IConnCallBack() {
            @Override
            public void onSuccess(String str) {

                    callback.onSuccess(what,str);
            }

            @Override
            public void onError(int reason) {
                callback.onError(reason);
            }

            @Override
            public void onNetworkCrashed() {
                callback.onNetworkCrashed();
            }
        });
    }

    public void startConn( final IConnCallBack callback) {
        hdl_callback = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case CONN_OK:
                        callback.onSuccess(msg.obj.toString());
                        break;
                    case NETWORK_ERROR:
                        callback.onNetworkCrashed();
                        break;
                    default:
                        callback.onError(msg.what);
                        break;
                }
            }
        };
        connThread = new Thread() {
            @Override
            public void run() {
                super.run();
                if (conn == null) {
                    LalaLog.e("EternityHTTP", "您还没有创建连接，请先调用createConn()方法！");
                } else {
                    startConn();
                }
            }
        };
        connThread.start();
    }

    private void startConn() {
        try {
            if (conn.getRequestMethod().equals("POST")) {
                os.flush();
                os.close();
            }
            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                is = conn.getInputStream();
                baos = new ByteArrayOutputStream();
                int len;
                byte[] data = new byte[4096];
                while ((len = is.read(data)) != -1) {
                    baos.write(data, 0, len);
                }
                is.close();
                String result = new String(baos.toByteArray());


                //↓如果需要调试，可以打开输出日志
                LalaLog.json("eternityResult", result);


                baos.close();
                if (hdl_callback != null) {
                    Message msg = new Message();
                    msg.what = CONN_OK;
                    msg.obj = result;
                    hdl_callback.sendMessage(msg);
                }
                conn.disconnect();
            } else {
                if (hdl_callback != null) {
                    hdl_callback.sendEmptyMessage(responseCode);
                }
            }
        }catch (SocketTimeoutException e) {
            if (hdl_callback != null) {
                hdl_callback.sendEmptyMessage(NETWORK_ERROR);
            }
        } catch (UnknownHostException e) {
            if (hdl_callback != null) {
                hdl_callback.sendEmptyMessage(NETWORK_ERROR);
            }
        }catch (IllegalStateException e){
            e.printStackTrace();
            hdl_callback=null;
        }catch (NullPointerException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (os != null) {
                    os.close();
                }
                if (is != null) {
                    is.close();
                }
                if (baos != null) {
                    baos.close();
                }
            }catch (IOException e){

            }
            if (conn!=null){
                conn.disconnect();
            }
        }
    }

    public void conn(Context context, final String url, final String method, @Nullable final HashMap<String, String> params, final IConnCallBack callback) {
        if (connThread != null) {
            shutDown();
        }
        hdl_callback = new Handler(context.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case CONN_OK:
                        callback.onSuccess(msg.obj.toString());
                        break;
                    case NETWORK_ERROR:
                        callback.onNetworkCrashed();
                        break;
                    default:
                        callback.onError(msg.what);
                        break;
                }
            }
        };
        connThread = new Thread() {
            @Override
            public void run() {
                super.run();
                createConn(url, method,params, new IConnCreateCallback() {
                    @Override
                    public void onConnCreated(HttpURLConnection connection) {
                        startConn();
                    }
                });
            }
        };
        connThread.start();
    }

    public void connForJSON(final int what,Context context, final String url, final String method, @Nullable final HashMap<String, String> params, final IJSONCallback callback,boolean isLoading) {
        waitDialog=new WaitDialog(context);
        showProgress(isLoading);
        conn(context, url, method, params, new IConnCallBack() {
            @Override
            public void onSuccess(String str) {
                dismiss();
                try {
                    if (str!=null&&str.length()!=0) {
                        //JSONObject jsonObject=JSON.parseObject(str);
                        callback.onSuccess(what,str);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(int reason) {
                dismiss();
                callback.onError(reason);
            }

            @Override
            public void onNetworkCrashed() {
                dismiss();
                callback.onNetworkCrashed();
            }
        });
    }

    public void shutDown() {
        hdl_callback=null;
    }

    private static void showProgress(boolean isLoading){
        if (isLoading) {
            if (waitDialog.isShowing()) {
                dismiss();
            }
            waitDialog.setCanceledOnTouchOutside(false);
            waitDialog.show();
        }

    }

    private static void dismiss() {
        if (waitDialog != null ) waitDialog.dismiss();
    }


}

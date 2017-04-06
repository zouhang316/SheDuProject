package com.beenvip.shedu.httputils;

import android.Manifest;
import android.app.Activity;
import android.os.Handler;
import android.os.Message;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Administrator on 2017/3/14 0014.
 */

public class EHUDownLoadUtil {
    public static final int DOWNLOADING=0,DOWNLOADED=1,PERMISSION_DENIED=2;
    public void download(final Activity activity, final String urlStr, final String downloadPath, final Handler hdl_download){
        new Thread(){
            @Override
            public void run() {
                super.run();
                String[] permissions=new String[]{
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE
                };
                PermissionMgr.grantPermission(activity,permissions);
                if (PermissionMgr.checkPermission(activity,permissions)) {
                    try {
                        URL url = new URL(urlStr);
                        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                        if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                            InputStream is = conn.getInputStream();
                            BufferedInputStream bis = new BufferedInputStream(is);
                            File file = new File(downloadPath);
                            new File(file.getParent()).mkdirs();
                            OutputStream os = new FileOutputStream(file);
                            BufferedOutputStream bos = new BufferedOutputStream(os);
                            byte[] data = new byte[4096];
                            int len;
                            float currentLen = 0;
                            float totalLen = conn.getContentLength();
                            while ((len = bis.read(data)) != -1) {
                                bos.write(data, 0, len);
                                currentLen += len;
                                Message msg = new Message();
                                msg.what = DOWNLOADING;
                                msg.obj = currentLen / totalLen * 100;
                                hdl_download.sendMessage(msg);
                            }
                            bis.close();
                            bos.close();
                            is.close();
                            os.close();
                            conn.disconnect();
                            hdl_download.sendEmptyMessage(DOWNLOADED);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else {
                    hdl_download.sendEmptyMessage(PERMISSION_DENIED);
                }
            }
        }.start();
    }
}

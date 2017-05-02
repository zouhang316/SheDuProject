package com.beenvip.shedu.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.beenvip.shedu.http.httpurlconnection.HttpCallBackListener;
import com.beenvip.shedu.http.httpurlconnection.HttpHelper;
import com.beenvip.shedu.user.LoginActivity;
import com.beenvip.shedu.user.bean.LoginBean;
import com.beenvip.shedu.utils.LalaLog;
import com.beenvip.shedu.utils.PreferenceManager;

import java.util.HashMap;

/**
 * Created by ZH on 2017/4/14.
 * 497239511@qq.com
 */

public class MessageService extends Service {
    private HttpHelper httpHelper;
    private int mum;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mum=0;
        httpHelper=new HttpHelper(this);
        final Handler handler=new Handler();
        Runnable runnable=new Runnable() {
            @Override
            public void run() {
                sendHttp();
                mum++;
                handler.postDelayed(this,1000);
                if (mum==6){
                    startActivity(new Intent(MessageService.this, LoginActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                }
                PreferenceManager.getInstance(MessageService.this).putInt("cont",mum);
                LalaLog.e(mum+"");
            }
        };
        handler.postDelayed(runnable,1000);

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
    private void sendHttp(){
        HashMap<String, String> map = new HashMap<>();
        map.put("username", "15576310006");
        map.put("password", "qwe123..aa");
        String url = "http://sp.beenvip.net/API/member/login.php?";
        httpHelper.asyncGetRequest(url, map, LoginBean.class, new HttpCallBackListener<LoginBean>() {
            @Override
            public void onSuccess(LoginBean loginBean) {
                LalaLog.i("login", loginBean.toString());

            }

            @Override
            public void onFailed(LoginBean loginBean) {
                LalaLog.i("login", loginBean.toString());
            }
        },true);
    }
}

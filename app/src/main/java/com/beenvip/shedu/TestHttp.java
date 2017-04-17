package com.beenvip.shedu;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.beenvip.shedu.base.BaseActivity;
import com.beenvip.shedu.http.HttpListener;
import com.beenvip.shedu.user.bean.LoginBean;
import com.beenvip.shedu.utils.LalaLog;

import java.util.HashMap;

/**
 * Created by ZH on 2017/4/14.
 * 497239511@qq.com
 */

public class TestHttp extends BaseActivity {
    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected boolean isShowToolBar() {
        return false;
    }

    @Override
    protected void onActivityCreate(Bundle savedInstanceState) {
        final Handler handler=new Handler();
        final Runnable runnable=new Runnable() {
            @Override
            public void run() {
                sendHttp();
                handler.postDelayed(this,2000);

            }
        };
        handler.postDelayed(runnable,2000);

    }
    private void sendHttp(){
        HashMap<String, String> map = new HashMap<>();
        map.put("username", "15576310006");
        map.put("password", "qwe123..aa");
        String url = "http://sp.beenvip.net/API/member/login.php?";
        httpHelper.asyncGetRequest(url, map, LoginBean.class, new HttpListener<LoginBean>() {
            @Override
            public void onSuccess(LoginBean loginBean) {
                LalaLog.i("login", loginBean.toString());
                Toast.makeText(TestHttp.this, "登录成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailed(LoginBean loginBean) {
                Toast.makeText(TestHttp.this, loginBean.getErrorInfo(), Toast.LENGTH_SHORT).show();
                LalaLog.i("login", loginBean.toString());
            }
        });
    }

}

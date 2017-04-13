package com.beenvip.shedu.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.beenvip.shedu.R;
import com.beenvip.shedu.base.BaseActivity;
import com.beenvip.shedu.http.HttpListener;
import com.beenvip.shedu.publics.ChoiceIdentityActivity;
import com.beenvip.shedu.user.bean.LoginBean;
import com.beenvip.shedu.utils.CommUtils;
import com.beenvip.shedu.utils.LalaLog;

import java.util.HashMap;

/**
 * Created by ZH on 2017/3/23.
 * 497239511@qq.com
 */

public class LoginActivity extends BaseActivity implements View.OnClickListener {
    public static LoginActivity instance = null;
    private TextView bt_regiter;
    private TextView bt_forgetpwd;
    private EditText edit_phone;
    private EditText edit_pwd;
    private Button bt_login;

    @Override
    protected void initData() {
    }

    @Override
    protected void initListener() {
        bt_regiter.setOnClickListener(this);
        bt_login.setOnClickListener(this);
        bt_forgetpwd.setOnClickListener(this);
    }

    @Override
    protected boolean isShowToolBar() {
        return false;
    }


    @Override
    protected void onActivityCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_login);
        instance = this;
        setFitsSystemWindows(false);
        bt_regiter = findView(R.id.reg);
        bt_login = findView(R.id.login);
        edit_phone = findView(R.id.edit_phone);
        edit_pwd = findView(R.id.edit_pwd);
        bt_forgetpwd = findView(R.id.forgetpwd);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.reg) {
            startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            finish();
        } else if (v.getId() == R.id.login) {
            doLogin();
        } else if (v.getId() == R.id.forgetpwd) {
            startActivity(new Intent(LoginActivity.this, ForgetPasswordActivity.class));
        }
    }

    private void doLogin() {
        String phone = edit_phone.getText().toString().trim();
        String pwd = edit_pwd.getText().toString().trim();
        if (!CommUtils.isMobile(phone)) {
            showMessageDialog("提示", "请输入正确的手机号码！");
            return;
        }
        if (pwd.isEmpty()) {
            showMessageDialog("提示", "密码不能为空");
            return;
        }
        HashMap<String, String> paramers = new HashMap<>();
        paramers.put("username", phone);
        paramers.put("password", pwd);
        String url = "http://sp.beenvip.net/API/member/login.php?";
        httpHelper.asyncGetRequest(url, paramers, LoginBean.class, new HttpListener<LoginBean>() {
            @Override
            public void onSuccess(LoginBean loginBean) {
                LalaLog.d("login", loginBean.toString());
                Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(LoginActivity.this,ChoiceIdentityActivity.class);
                startActivity(intent);
            }

            @Override
            public void onFailed(LoginBean loginBean) {
                Toast.makeText(LoginActivity.this, loginBean.getErrorInfo(), Toast.LENGTH_SHORT).show();
                LalaLog.d("login", loginBean.toString());
            }
        });
    }
}

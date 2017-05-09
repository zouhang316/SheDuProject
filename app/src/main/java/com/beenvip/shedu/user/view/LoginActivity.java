package com.beenvip.shedu.user.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.beenvip.shedu.R;
import com.beenvip.shedu.base.BaseActivity;
import com.beenvip.shedu.base.bean.BaseErrorInfo;
import com.beenvip.shedu.publics.activity.FirstSelectIdentityActivity;
import com.beenvip.shedu.user.model.bean.LoginBean;
import com.beenvip.shedu.user.presenter.LoginPresenter;
import com.beenvip.shedu.user.presenter.LoginPresenterImpl;
import com.beenvip.shedu.utils.CommUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ZH on 2017/3/23.
 * 497239511@qq.com
 */

public class LoginActivity extends BaseActivity implements LoginView, View.OnClickListener {
    public static LoginActivity instance = null;
    private TextView bt_regiter;
    private TextView bt_forgetpwd;
    private EditText edit_phone;
    private EditText edit_pwd;
    private Button bt_login;
    private LoginPresenter loginPresenter;

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
        loginPresenter = new LoginPresenterImpl(this, this);
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
        Map<String, Object> paramers = new HashMap<>();
        paramers.put("username", phone);
        paramers.put("password", pwd);
        String url = "http://sp.beenvip.net/API/member/login.php?";
        loginPresenter.onLogin(url, paramers, LoginBean.class, true);
    }

    @Override
    public void showProgress() {
        showLoading();
    }

    @Override
    public void hideProgress() {
        dismissLoading();
    }

    @Override
    public void navigateToHome() {
        Intent intent = new Intent(LoginActivity.this, FirstSelectIdentityActivity.class);
        startActivity(intent);
    }

    @Override
    public void showErrorAlert(BaseErrorInfo baseErrorInfo) {
        showToastMsg(baseErrorInfo.ErrorInfo);
    }
}

package com.beenvip.shedu.user.presenter;


import android.content.Context;

import com.beenvip.shedu.base.bean.BaseErrorInfo;
import com.beenvip.shedu.base.model.BaseModel;
import com.beenvip.shedu.user.model.LoginModelImpl;
import com.beenvip.shedu.user.model.bean.LoginBean;
import com.beenvip.shedu.user.view.LoginView;
import com.beenvip.shedu.utils.LalaLog;

import java.util.Map;

/**
 * @author sxshi on 2017/4/26 9:37.
 * @email:emotiona_xiaoshi@126.com
 * @describe:Describe the function  of the current class
 */

public class LoginPresenterImpl implements LoginPresenter<LoginBean>, BaseModel.OnRequestFinishedListener<LoginBean> {
    private BaseModel loginModel;
    private LoginView mLoginView;

    public LoginPresenterImpl(LoginView loginView, Context context) {
        this.loginModel = new LoginModelImpl(context);
        this.mLoginView = loginView;
    }

    @Override
    public void onLogin(String url, Map<String, Object> paramers, Class<LoginBean> clzz, boolean isShowLoading) {
        if (mLoginView != null) {
            if (isShowLoading) mLoginView.showProgress();
        }
        loginModel.request(url, paramers, clzz, this);
    }

    @Override
    public void onDestory() {
        mLoginView = null;
    }

    @Override
    public void onSuccess(LoginBean loginBean) {
        LalaLog.d(LalaLog.SSX_TAG, loginBean.toString());
        if (mLoginView != null) {
            mLoginView.hideProgress();
            mLoginView.navigateToHome();
        }
    }

    @Override
    public void onError(BaseErrorInfo baseErrorInfo) {
        LalaLog.d(LalaLog.SSX_TAG, baseErrorInfo.toString());
        if (mLoginView != null) {
            mLoginView.hideProgress();
            mLoginView.showErrorAlert(baseErrorInfo);
        }
    }
}
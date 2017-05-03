package com.beenvip.shedu.user.view;

import com.beenvip.shedu.base.bean.BaseErrorInfo;

/**
 * Created by sxshi on 2016/12/22
 */

public interface LoginView {
    /***
     * show loading
     */
    void showProgress();

    /**
     * hide loading
     */
    void hideProgress();

    /***
     * go to home
     */
    void navigateToHome();

    /***
     * show errorMsg
     * <code>{@link android.app.AlertDialog}
     * {@link com.beenvip.shedu.base.bean.BaseErrorInfo} This Class about all error message info
     * </code>
     * @param baseErrorInfo
     */
    void showErrorAlert(BaseErrorInfo baseErrorInfo);
}
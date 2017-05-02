package com.beenvip.shedu.base.bean;

/**
 * @author sxshi on 2017/4/25.
 *         email:emotiona_xiaoshi@126.com
 *         describe:统一封装all错误信息
 */

public class BaseErrorInfo {
    public String responseCode;
    public String errMessage;

    public BaseErrorInfo() {
    }

    public BaseErrorInfo(String responseCode, String errMessage) {
        this.responseCode = responseCode;
        this.errMessage = errMessage;
    }

    @Override
    public String toString() {
        return "BaseErrorInfo{" + "responseCode='" + responseCode + '\'' + ", errMessage='" + errMessage + '\'' + '}';
    }
}

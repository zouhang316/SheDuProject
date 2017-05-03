package com.beenvip.shedu.base.bean;

/**
 * @author sxshi on 2017/4/25.
 *         email:emotiona_xiaoshi@126.com
 *         describe:统一封装all错误信息
 */

public class BaseErrorInfo {
    public String ErrorCode;
    public String ErrorInfo;

    public BaseErrorInfo() {
    }

    public BaseErrorInfo(String errorCode, String errorInfo) {
        ErrorCode = errorCode;
        ErrorInfo = errorInfo;
    }

    @Override
    public String toString() {
        return "BaseErrorInfo{" + "ErrorCode='" + ErrorCode + '\'' + ", ErrorInfo='" + ErrorInfo + '\'' + '}';
    }
}

package com.beenvip.shedu.base;

/**
 * @author sxshi on 2017/4/13.
 *         email:emotiona_xiaoshi@126.com
 *         describe:网络请求数据解析基类
 */

public class HttpBaseResponseBean {
    public boolean result = false;
    public String ErrorCode;
    public String ErrorInfo;

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getErrorCode() {
        return ErrorCode;
    }

    public void setErrorCode(String errorCode) {
        ErrorCode = errorCode;
    }

    public String getErrorInfo() {
        return ErrorInfo;
    }

    public void setErrorInfo(String errorInfo) {
        ErrorInfo = errorInfo;
    }

    @Override
    public String toString() {
        return "HttpBaseResponseBean{" + "result=" + result + ", ErrorCode='" + ErrorCode + '\'' + ", ErrorInfo='" + ErrorInfo + '\'' + '}';
    }
}

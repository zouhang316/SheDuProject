package com.beenvip.shedu.user.bean;

import com.beenvip.shedu.base.HttpBaseResponseBean;

/**
 * @author sxshi on 2017/4/13.
 *         email:emotiona_xiaoshi@126.com
 *         describe:Describe the function  of the current class
 */

public class LoginBean extends HttpBaseResponseBean {
    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * uid : 3
         */

        private String uid;

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        @Override
        public String toString() {
            return "DataBean{" + "uid='" + uid + '\'' + '}';
        }
    }

    @Override
    public String toString() {
        return "LoginBean{" + "data=" + data + '}';
    }
}

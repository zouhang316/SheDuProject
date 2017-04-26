package com.beenvip.shedu.publics.bean;

import com.beenvip.shedu.base.HttpBaseResponseBean;

import java.util.List;

/**
 * Created by ZH on 2017/4/25.
 * 497239511@qq.com
 */

public class CityBean extends HttpBaseResponseBean {

    /**
     * result : true
     * data : [{"fid":"1","fup":"0","name":"北京市"},{"fid":"2","fup":"0","name":"上海市"},{"fid":"3","fup":"0","name":"天津市"},{"fid":"4","fup":"0","name":"重庆市"},{"fid":"5","fup":"0","name":"河北省"},{"fid":"6","fup":"0","name":"山西省"},{"fid":"7","fup":"0","name":"内蒙古自治区"},{"fid":"8","fup":"0","name":"辽宁省"},{"fid":"9","fup":"0","name":"吉林省"},{"fid":"10","fup":"0","name":"黑龙江省"},{"fid":"11","fup":"0","name":"江苏省"},{"fid":"12","fup":"0","name":"浙江省"},{"fid":"13","fup":"0","name":"安徽省"},{"fid":"14","fup":"0","name":"福建省"},{"fid":"15","fup":"0","name":"江西省"},{"fid":"16","fup":"0","name":"山东省"},{"fid":"17","fup":"0","name":"河南省"},{"fid":"18","fup":"0","name":"湖北省"},{"fid":"19","fup":"0","name":"湖南省"},{"fid":"20","fup":"0","name":"广东省"},{"fid":"21","fup":"0","name":"广西壮族自治区"},{"fid":"22","fup":"0","name":"海南省"},{"fid":"23","fup":"0","name":"四川省"},{"fid":"24","fup":"0","name":"贵州省"},{"fid":"25","fup":"0","name":"云南省"},{"fid":"26","fup":"0","name":"西藏自治区"},{"fid":"27","fup":"0","name":"陕西省"},{"fid":"28","fup":"0","name":"甘肃省"},{"fid":"29","fup":"0","name":"青海省"},{"fid":"30","fup":"0","name":"宁夏回族自治区"},{"fid":"31","fup":"0","name":"新疆维吾尔自治区"},{"fid":"32","fup":"0","name":"台湾省"},{"fid":"33","fup":"0","name":"香港特别行政区"},{"fid":"34","fup":"0","name":"澳门特别行政区"}]
     */

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * fid : 1
         * fup : 0
         * name : 北京市
         */

        private String fid;
        private String fup;
        private String name;

        public String getFid() {
            return fid;
        }

        public void setFid(String fid) {
            this.fid = fid;
        }

        public String getFup() {
            return fup;
        }

        public void setFup(String fup) {
            this.fup = fup;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}

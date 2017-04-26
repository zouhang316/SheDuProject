package com.beenvip.shedu.fabaofang.bean;

import com.beenvip.shedu.base.HttpBaseResponseBean;

import java.util.List;

/**
 * Created by ZH on 2017/4/18.
 * 497239511@qq.com
 */

public class BanzuBean extends HttpBaseResponseBean {

    /**
     * result : true
     * data : [{"fid":"10","name":"土石方"},{"fid":"11","name":"运输队/弃置"},{"fid":"21","name":"钢筋班"},{"fid":"22","name":"模板班"},{"fid":"23","name":"混凝土班"},{"fid":"24","name":"脚手架班"},{"fid":"25","name":"钢模制安（房建）"},{"fid":"26","name":"铝膜制安（房建）"},{"fid":"27","name":"泥水班"},{"fid":"28","name":"泥木铁总包"}]
     */

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "BanzuBean{" +
                "data=" + data +
                '}';
    }

    public static class DataBean {
        /**
         * fid : 10
         * name : 土石方
         */

        private String fid;
        private String name;

        public String getFid() {
            return fid;
        }

        public void setFid(String fid) {
            this.fid = fid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "fid='" + fid + '\'' +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
}

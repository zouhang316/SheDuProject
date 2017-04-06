package com.beenvip.shedu;

import java.util.List;

/**
 * Created by ZH on 2017/3/23.
 * 497239511@qq.com
 */

public class CityBean {
    /**
     * result : true
     * data : [{"lid":"235","from_city_id":"312","from_city_name":"长沙市","from_city_lng":"","from_city_lat":""},{"lid":"138","from_city_id":"318","from_city_name":"常德市","from_city_lng":"","from_city_lat":""},{"lid":"43","from_city_id":"320","from_city_name":"益阳市","from_city_lng":"","from_city_lat":""}]
     */

    private boolean result;
    private List<DataBean> data;

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * lid : 235
         * from_city_id : 312
         * from_city_name : 长沙市
         * from_city_lng :
         * from_city_lat :
         */

        private String lid;
        private String from_city_id;
        private String from_city_name;
        private String from_city_lng;
        private String from_city_lat;

        public String getLid() {
            return lid;
        }

        public void setLid(String lid) {
            this.lid = lid;
        }

        public String getFrom_city_id() {
            return from_city_id;
        }

        public void setFrom_city_id(String from_city_id) {
            this.from_city_id = from_city_id;
        }

        public String getFrom_city_name() {
            return from_city_name;
        }

        public void setFrom_city_name(String from_city_name) {
            this.from_city_name = from_city_name;
        }

        public String getFrom_city_lng() {
            return from_city_lng;
        }

        public void setFrom_city_lng(String from_city_lng) {
            this.from_city_lng = from_city_lng;
        }

        public String getFrom_city_lat() {
            return from_city_lat;
        }

        public void setFrom_city_lat(String from_city_lat) {
            this.from_city_lat = from_city_lat;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "lid='" + lid + '\'' +
                    ", from_city_id='" + from_city_id + '\'' +
                    ", from_city_name='" + from_city_name + '\'' +
                    ", from_city_lng='" + from_city_lng + '\'' +
                    ", from_city_lat='" + from_city_lat + '\'' +
                    '}';
        }
    }
}

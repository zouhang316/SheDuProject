package com.beenvip.shedu.publics.bean;

import java.util.List;

/**
 * Created by ZH on 2017/5/5.
 * 497239511@qq.com
 */

public class NewCityBean {
    @Override
    public String toString() {
        return "NewCityBean{" +
                "name='" + name + '\'' +
                ", city=" + city +
                '}';
    }

    /**
     * name : 北京市
     * city : [{"name":"北京市","area":["东城区","西城区","崇文区","宣武区","朝阳区","
     * 丰台区","石景山区","海淀区","门头沟区","房山区","通州区","顺义区","昌平区","大兴区","平谷区","怀柔区","密云县","延庆县"]}]
     */

    private String name;
    private List<CityBean> city;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CityBean> getCity() {
        return city;
    }

    public void setCity(List<CityBean> city) {
        this.city = city;
    }

    public static class CityBean {
        @Override
        public String toString() {
            return "CityBean{" +
                    "name='" + name + '\'' +
                    '}';
        }

        /**
         * name : 北京市
         * area : ["东城区","西城区","崇文区","宣武区","朝阳区","丰台区","石景山区",
         * "海淀区","门头沟区","房山区","通州区","顺义区","昌平区","大兴区","平谷区","怀柔区","密云县","延庆县"]
         */

        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}

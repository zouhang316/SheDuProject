package com.beenvip.shedu.event;

import java.util.List;

/**
 * Created by ZH on 2017/5/6.
 * 497239511@qq.com
 */

public class CitySelectBean {
    private List<String> provinceList;
    private List<List<String>> city;

    public List<String> getProvinceList() {
        return provinceList;
    }

    public void setProvinceList(List<String> provinceList) {
        this.provinceList = provinceList;
    }

    public List<List<String>> getCity() {
        return city;
    }

    public void setCity(List<List<String>> city) {
        this.city = city;
    }
}

package com.beenvip.shedu.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ZH on 2017/3/8.
 * 497239511@qq.com
 */

public class FastJsonHelper {
    public static <T> T getObject(String jsonString, Class<T> cls) {
        return JSON.parseObject(jsonString, cls);
    }

    public static <T> List<T> getObjects(String jsonString, Class<T> cls) {
        return JSON.parseArray(jsonString, cls);
    }

    public static List<Map<String, String>> getKeyMapsList(String jsonString) {
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        list = JSON.parseObject(jsonString, new TypeReference<List<Map<String, String>>>() {
        });
        return list;
    }
    public static Map<String, String> getKeyMap(String jsonString) {
        Map<String, String> map=new HashMap<>();
        map = JSON.parseObject(jsonString, new TypeReference<Map<String, String>>() {
        });
        return map;
    }
}

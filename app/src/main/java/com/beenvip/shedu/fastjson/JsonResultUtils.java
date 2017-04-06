package com.beenvip.shedu.fastjson;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by ZH on 2017/3/8.
 * 497239511@qq.com
 */

public class JsonResultUtils {

    /**
     * 生成helper
     * @param json
     * @return
     */
    public final static JsonResultHelper helper(String json){
        if(null == json){
            new JsonResultHelper("");
        }
        return new JsonResultHelper(json);
    }

    public final static JsonResultHelper helper(JSONObject json){
        if(null == json){
            new JsonResultHelper("");
        }
        return new JsonResultHelper(json);
    }

}

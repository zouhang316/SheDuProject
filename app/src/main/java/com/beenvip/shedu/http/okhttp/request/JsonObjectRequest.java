package com.beenvip.shedu.http.okhttp.request;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.beenvip.shedu.http.okhttp.HttpListener;
import com.beenvip.shedu.http.okhttp.HttpRequest;

import org.json.JSONException;
import org.json.JSONObject;
/**
 *
 * @author sxshi on 2017/4/26 16:28.
 * @email:emotiona_xiaoshi@126.com
 * @describe:http request return jsonObject
 */

public class JsonObjectRequest extends RequestWrapper<JSONObject> {

    public JsonObjectRequest(HttpRequest httpRequest, HttpListener<JSONObject> listener) {
        super(httpRequest, listener);
    }

    @Override
    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
        String result = getResponseString(response);
        if (result.equals(PARSEERROR)) {
            return Response.error(new ParseError());
        }
        try {
            return Response.success(new JSONObject(result), HttpHeaderParser.parseCacheHeaders(response));
        } catch (JSONException e) {
            return Response.error(new ParseError(e));
        }
    }
}

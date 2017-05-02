package com.beenvip.shedu.http.okhttp.request;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.beenvip.shedu.http.okhttp.HttpListener;
import com.beenvip.shedu.http.okhttp.HttpRequest;

import org.json.JSONArray;
import org.json.JSONException;
/**
 *
 * @author sxshi on 2017/4/26 16:27.
 * @email:emotiona_xiaoshi@126.com
 * @describe:http request return jsonArray
 */

public class JsonArrayRequest extends RequestWrapper<JSONArray> {

    public JsonArrayRequest(HttpRequest httpRequest, HttpListener<JSONArray> listener) {
        super(httpRequest, listener);
    }

    @Override
    protected Response<JSONArray> parseNetworkResponse(NetworkResponse response) {
        String result = getResponseString(response);
        if (result.equals(PARSEERROR)) {
            return Response.error(new ParseError());
        }
        try {
            return Response.success(new JSONArray(result), HttpHeaderParser.parseCacheHeaders(response));
        } catch (JSONException e) {
            return Response.error(new ParseError(e));
        }
    }
}

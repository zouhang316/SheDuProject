package com.beenvip.shedu.http.okhttp.request;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.beenvip.shedu.http.okhttp.HttpListener;
import com.beenvip.shedu.http.okhttp.HttpRequest;

/**
 *
 * @author sxshi on 2017/4/26 16:29.
 * @email:emotiona_xiaoshi@126.com
 * @describe:http request return String
 */

public class StringRequest extends RequestWrapper<String> {

    public StringRequest(HttpRequest httpRequest, HttpListener<String> listener) {
        super(httpRequest, listener);
    }

    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse response) {
        String result = getResponseString(response);
        if (result.equals(PARSEERROR)) {
            return Response.error(new ParseError());
        }
        return Response.success(result, HttpHeaderParser.parseCacheHeaders(response));
    }
}

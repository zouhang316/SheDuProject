package com.beenvip.shedu.http.okhttp.request;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.beenvip.shedu.http.okhttp.HttpListener;
import com.beenvip.shedu.http.okhttp.HttpRequest;

/**
 * 
 * @author sxshi on 2017/4/26 16:26.
 * @email:emotiona_xiaoshi@126.com
 * @describe:http request return byte[]
 */

public class ByteRequest extends RequestWrapper<byte[]> {

    public ByteRequest(HttpRequest httpRequest, HttpListener<byte[]> listener) {
        super(httpRequest, listener);
    }

    @Override
    protected Response<byte[]> parseNetworkResponse(NetworkResponse response) {
        return Response.success(response.data, HttpHeaderParser.parseCacheHeaders(response));
    }
}

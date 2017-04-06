package com.beenvip.shedu.httputils;

import java.io.OutputStream;
import java.net.HttpURLConnection;

/**
 * Created by Administrator on 2017/3/16 0016.
 */

public interface IConnCreateCallback {
    public void onConnCreated(HttpURLConnection connection);
}

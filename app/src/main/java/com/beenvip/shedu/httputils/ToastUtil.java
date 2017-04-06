package com.beenvip.shedu.httputils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Administrator on 2017/2/23 0023.
 */

public class ToastUtil {
    private static Toast toast;

    public static void show(Context context, String str) {
        if (toast != null) {
            toast.cancel();
        }
        toast = Toast.makeText(context, str, Toast.LENGTH_SHORT);
        toast.show();
    }
}

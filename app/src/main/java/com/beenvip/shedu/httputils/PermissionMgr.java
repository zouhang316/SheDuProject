package com.beenvip.shedu.httputils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/2/24 0024.
 */

public class PermissionMgr {
    public static void grantPermission(Activity activity, String[] permissions){
        ArrayList<String> al_neededPermissions=new ArrayList<>();
        for (int i=0;i<permissions.length;i++){
            if (ActivityCompat.checkSelfPermission(activity,permissions[i])!= PackageManager.PERMISSION_GRANTED){
                al_neededPermissions.add(permissions[i]);
            }
        }
        if (al_neededPermissions.size()>0){
            String []neededPermissions=new String[al_neededPermissions.size()];
            for (int i=0;i<al_neededPermissions.size();i++){
                neededPermissions[i]=al_neededPermissions.get(i);
            }
            ActivityCompat.requestPermissions(activity,neededPermissions,0);
        }
    }
    public static boolean checkPermission(Context context, String[] permissions){
        for (int i=0;i<permissions.length;i++){
            if (ActivityCompat.checkSelfPermission(context,permissions[i])!= PackageManager.PERMISSION_GRANTED){
                return false;
            }
        }
        return true;
    }
}

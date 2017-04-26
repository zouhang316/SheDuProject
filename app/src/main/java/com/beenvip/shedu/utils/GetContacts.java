package com.beenvip.shedu.utils;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.ContactsContract;

import com.beenvip.shedu.R;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by ZH on 2017/4/14.
 * 497239511@qq.com
 */

public class GetContacts {
    private static final String[] CONTACTOR_ION = new String[]{
            ContactsContract.CommonDataKinds.Phone.CONTACT_ID,
            ContactsContract.Contacts.DISPLAY_NAME,
            ContactsContract.CommonDataKinds.Phone.NUMBER
    };

    public static Bitmap get_people_image(String x_number,Context context) {
        Cursor cursorCantacts=null;
        // 获得Uri
        Uri uriNumber2Contacts = Uri.parse("content://com.android.contacts/"
                + "data/phones/filter/" + x_number);
        // 查询Uri，返回数据集
        try {
            cursorCantacts = context.getContentResolver().query(
                    uriNumber2Contacts, null, null, null, null);
            // 如果该联系人存在
            if (cursorCantacts.getCount() > 0) {
                // 移动到第一条数据
                cursorCantacts.moveToFirst();
                // 获得该联系人的contact_id
                Long contactID = cursorCantacts.getLong(cursorCantacts.getColumnIndex("contact_id"));
                // 获得contact_id的Uri
                Uri uri = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, contactID);
                // 打开头像图片的InputStream
                InputStream input = ContactsContract.Contacts.openContactPhotoInputStream(context.getContentResolver(), uri);
                //设置默认显示头像
                if (input==null){
                    return BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher);
                }
                // 从InputStream获得bitmap
                Bitmap bitmap = BitmapFactory.decodeStream(input);
                return bitmap;
            }
        }catch (Exception e){
        }finally {
            cursorCantacts.close();
        }
        return null;
    }
    public static ArrayList<MyContacts> getCon(Context context){
        ArrayList<MyContacts> myContactses=new ArrayList<>();
        Cursor phones = null;
        ContentResolver cr = context.getContentResolver();
        try {
            phones = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI
                            , CONTACTOR_ION, null, null, "sort_key");

            if (phones != null) {
                final int contactIdIndex = phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.CONTACT_ID);
                final int displayNameIndex = phones.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
                final int phoneIndex = phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                String phoneString="";
                String  displayNameString, contactIdString;
                while (phones.moveToNext()) {
                    MyContacts contacts=new MyContacts();
                    phoneString = phones.getString(phoneIndex);
                    displayNameString = phones.getString(displayNameIndex);
                    contactIdString = phones.getString(contactIdIndex);
                    phoneString = phoneString.replace("-","");
                    phoneString = phoneString.replace(" ","");
                    contacts.setName(displayNameString);
                    contacts.setPhoneNumber(phoneString);
                    contacts.setHead(get_people_image(phoneString,context));
                    myContactses.add(contacts);
                }
            }
                return  myContactses;
        } catch (Exception e) {
            LalaLog.e( e.getMessage());
        } finally {
            if (phones != null)
                phones.close();
        }
        return null;
    }
}

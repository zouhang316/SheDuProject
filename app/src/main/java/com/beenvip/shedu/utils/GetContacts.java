package com.beenvip.shedu.utils;

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
    /**
     * 获取联系人名字和号码
     *
     * @param context
     * @return
     */

    public static ArrayList<MyContacts> getAllContacts(Context context) {
        ArrayList<MyContacts> contacts = new ArrayList<MyContacts>();
        Cursor cursor = context.getContentResolver().query(
                ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        while (cursor.moveToNext()) {

            //新建一个联系人实例
            MyContacts temp = new MyContacts();
            String contactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
            String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            temp.setName(name);
            //获取联系人所有电话号
            Cursor phone = context.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "="
                    + contactId, null, null);
            while(phone.moveToNext())
            {
                String PhoneNumber = phone.getString(phone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                //格式化手机号
                PhoneNumber = PhoneNumber.replace("-","");
                PhoneNumber = PhoneNumber.replace(" ","");
                temp.getPhoneNumbers().add(PhoneNumber);
                //根基号码获取联系人头像
                temp.setHead(get_people_image(PhoneNumber,context));
            }
            phone.close();
            //添加单个联系人到集合
            contacts.add(temp);
        }
        return contacts;
    }


    public static Bitmap get_people_image(String x_number,Context context) {
        // 获得Uri
        Uri uriNumber2Contacts = Uri.parse("content://com.android.contacts/"
                + "data/phones/filter/" + x_number);
        // 查询Uri，返回数据集
        Cursor cursorCantacts = context.getContentResolver().query(
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
        cursorCantacts.close();
        return null;
    }
}

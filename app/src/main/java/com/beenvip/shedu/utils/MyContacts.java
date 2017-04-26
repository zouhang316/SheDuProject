package com.beenvip.shedu.utils;

import android.graphics.Bitmap;

import java.util.List;

/**
 * Created by ZH on 2017/4/14.
 * 497239511@qq.com
 */

public class MyContacts {
    private String name;
    private String phoneNumber;
    private Bitmap head;
    private List<String> emails;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Bitmap getHead() {
        return head;
    }

    public void setHead(Bitmap head) {
        this.head = head;
    }

    public List<String> getEmails() {
        return emails;
    }

    public void setEmails(List<String> emails) {
        this.emails = emails;
    }
}

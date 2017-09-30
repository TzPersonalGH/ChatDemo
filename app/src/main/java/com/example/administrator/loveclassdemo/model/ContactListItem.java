package com.example.administrator.loveclassdemo.model;

/**
 * Created by Tz on 2017/9/30.
 */

public class ContactListItem {
    public String url; //头像url
    public String contact; //联系人

    public String getFirstLetter() {
        return String.valueOf(contact.charAt(0)).toUpperCase();
    }
}

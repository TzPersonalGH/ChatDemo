package com.example.administrator.loveclassdemo.utils;

/**
 * Created by Tz on 2017/9/26.
 */

public class StringUtils {
    public static final String REGEX_USER_NAME = "^[a-zA-Z]\\w{2,19}$";
    public static final String REGEX_PASSWORD = "^[0-9]{3,20}$";

    public static boolean isValidUserName(String userName) {
        return userName.matches(REGEX_USER_NAME);
    }

    public static boolean isValidPassword(String password) {
        return password.matches(REGEX_PASSWORD);
    }

}

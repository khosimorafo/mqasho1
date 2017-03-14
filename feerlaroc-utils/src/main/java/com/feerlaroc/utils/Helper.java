package com.feerlaroc.utils;

/**
 * Created by root on 2016/03/23.
 */
public class Helper {

    public static String returnString(String str){

        return str == null ?  "" : str;

    }

    public static String returnString(Object o) {

        String str = (String.valueOf(o));

        return str == null ?  "" : str;

    }
}

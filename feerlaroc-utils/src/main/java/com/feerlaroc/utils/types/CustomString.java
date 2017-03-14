package com.feerlaroc.utils.types;

/**
 * Created by root on 2016/03/14.
 *
 * Deligate class for strings
 */
public class CustomString {

    String mValue;

    public CustomString(String value){

        mValue = value;

    }

    public String getValue() {
        return mValue;
    }

    public boolean compare (CustomString other){

        return this.mValue.equals(other.mValue) ? true : false;

    }

}

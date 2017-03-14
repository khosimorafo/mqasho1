package com.feerlaroc.zoho.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by root on 2017/01/16.
 */

public abstract class PostData {

    protected Map<String, Object> mData = new HashMap<>();

    public void add(String key, Object value){

        mData.put(key, value);
        mData = mData;
    }

    public void delete(String key){

        mData.remove(key);
    }

    public Map<String, Object> getData() {
        return mData;
    }

    public void setData(Map<String, Object> mData) {
        this.mData = mData;
    }
}

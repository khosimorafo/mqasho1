package com.feerlaroc.utils.json;

import com.google.gson.JsonParser;

import com.feerlaroc.utils.types.CustomString;

import java.util.Map;

/**
 * Created by root on 2016/03/15.
 */
public class FeerlarocJsonQuiries {

    static JsonParser mParser = new JsonParser();

    public static CustomString getCustomString(Map<String, Object> jsonMap, String memberName){

        //String str = new Gson().toJson(jsonMap);
        //JsonObject jsonObj = (JsonObject) mParser.parse(str);

        CustomString result = new CustomString(jsonMap.get(memberName).toString());

        return result;
    }



}

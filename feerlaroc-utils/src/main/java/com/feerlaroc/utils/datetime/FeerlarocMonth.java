package com.feerlaroc.utils.datetime;

import com.feerlaroc.utils.datetime.utils.FeerlarocDateUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by root on 2016/03/08.
 */
public abstract class FeerlarocMonth{

    protected Calendar mDate = Calendar.getInstance();
    protected String mTag = "";

    public FeerlarocMonth(String date){

        mDate.setTime(FeerlarocDateUtils.parseTimestamp(date));

    }

    public FeerlarocMonth(Date date){

        mDate.setTime(date);

    }

    public FeerlarocMonth(Calendar calendar){

        mDate = calendar;

    }

    public String getMonthStringLong(){

        return mDate.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH);

    }

    public String getMonthStringShort(){

        return mDate.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.ENGLISH);

    }

    public int getYear(){

        return mDate.get(Calendar.YEAR);

    }

    public boolean isBefore(Calendar calendarToCompare){

        return this.isBefore(calendarToCompare);

    }


    public String getTagValue(){

        return mTag;

    }

    public abstract void setTag(String tag);

}

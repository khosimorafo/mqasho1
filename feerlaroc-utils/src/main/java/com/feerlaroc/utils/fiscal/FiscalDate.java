package com.feerlaroc.utils.fiscal;

import android.text.format.DateUtils;

import com.feerlaroc.utils.datetime.utils.FeerlarocDateUtils;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by root on 2016/06/30.
 */
public class FiscalDate {

    private static final int FIRST_FISCAL_MONTH  = Calendar.JANUARY;

    private Calendar calendarDate = Calendar.getInstance();

    public FiscalDate(Calendar calendarDate) {

        this.calendarDate = calendarDate;
    }

    public FiscalDate(Long dateInMilliseconds){

        this.calendarDate.setTimeInMillis(dateInMilliseconds);
    }

    public FiscalDate(Date date) {

        this.calendarDate.setTime(date);
    }

    public long getActualDate(){

        return  calendarDate.getTimeInMillis();
    }

    public int getFiscalMonth(){

        int month = calendarDate.get(Calendar.MONTH);
        int result = ((month - FIRST_FISCAL_MONTH - 1) % 12) + 1;
        if (result < 0) {
            result += 12;
        }
        return result;
    }

    public int getFiscalYear(){

        int month = calendarDate.get(Calendar.MONTH);
        int year = calendarDate.get(Calendar.YEAR);
        return (month >= FIRST_FISCAL_MONTH) ? year : year - 1;
    }

    public int getCalendarMonth() {

        return calendarDate.get(Calendar.MONTH);
    }

    public int getCalendarYear() {

        return calendarDate.get(Calendar.YEAR);
    }

    public String getPeriod(){

        String _month = FeerlarocDateUtils.getMonthString(getFiscalMonth(), DateUtils.LENGTH_LONG);
        return String.valueOf(_month + " " + getFiscalYear());
    }

    public String getPeriodShort(){

        String _month = FeerlarocDateUtils.getMonthString(getFiscalMonth(), DateUtils.LENGTH_SHORT);
        return String.valueOf(_month + " " + getFiscalYear());
    }
}

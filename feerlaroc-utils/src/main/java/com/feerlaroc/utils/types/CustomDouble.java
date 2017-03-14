package com.feerlaroc.utils.types;

/**
 * Created by root on 2016/03/14.
 *
 * Deligate class for strings
 */
public abstract class CustomDouble {

    Double mValue = 0.0;

    public CustomDouble(Double value){

        mValue = value;

    }

    public Double getValue() {
        return mValue;
    }

    public void increment(CustomDouble value){

        if(value.getValue()!=null){

            mValue += value.getValue();

        }

    }

    public void decrement(CustomDouble value){

        if(value.getValue()!=null){

            mValue -= value.getValue();

        }

    }


}

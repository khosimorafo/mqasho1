package com.feerlaroc.utils.csv;

import com.opencsv.CSVReader;

import java.io.InputStreamReader;

/**
 * Created by root on 2016/06/15.
 */
public class FeerCSV {

    InputStreamReader mInputStreamReader;

    public FeerCSV(InputStreamReader streamReader){

        mInputStreamReader = streamReader;

    }

    public CSVReader get(){

        return new CSVReader(mInputStreamReader);
    }

}

package com.feerlaroc.zoho.rx;

/**
 * Created by root on 2016/12/14.
 */

public class RxZoho {

    private static final String TAG = "RxZoho";

    private static RxZoho instance;

    /**
     * Singleton
     *
     * @return {@link RxZoho}
     */
    public static synchronized RxZoho getInstance() {
        if (instance == null) {
            instance = new RxZoho();
        }
        return instance;
    }

    //Prevent constructor initialisation
    private RxZoho() {}



}

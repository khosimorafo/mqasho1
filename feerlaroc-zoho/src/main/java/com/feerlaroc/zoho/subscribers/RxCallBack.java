package com.feerlaroc.zoho.subscribers;

import android.content.Context;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by root on 2017/01/19.
 */

public class RxCallBack<T> implements Callback<T> {

    public RxCallBack(Context context) {
        this.mContext = context;
    }
    private static final String TAG = RxCallBack.class.getSimpleName();
    private Context mContext;


    @Override
    public void onResponse(Call<T> call, Response<T> response) {


    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {

    }
}

package com.feerlaroc.zoho.subscribers;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.feerlaroc.zoho.retrofit.exception.ApiException;
import com.feerlaroc.zoho.utils.DialogHelper;

/**
 * @author YorkYu
 * @version V1.0
 * @Project: Retrofit2RxjavaDemo
 * @Package york.com.retrofit2rxjavademo.subscribers
 * @Description:
 * @time 2016/8/11 10:54
 */
public abstract class RxSubscriber<T> extends BaseSubscriber<T> {


    public RxSubscriber(Context context) {
        this.mContext = context;
    }
    private static final String TAG = RxSubscriber.class.getSimpleName();
    private Context mContext;

    @Override
    public void onStart() {
        super.onStart();

        DialogHelper.showProgressDlg(mContext, "Kancane nje!");
        /*
        // if  NetworkAvailable no !   must to call onCompleted
        if (!NetworkUtil.isNetworkAvailable(mContext)) {
            Toast.makeText(mContext, "当No Network", Toast.LENGTH_SHORT).show();
            onCompleted();
        } else {
            DialogHelper.showProgressDlg(mContext, "正在加载数据");
        }
        */
    }

    @Override
    public void onCompleted() {

        DialogHelper.stopProgressDlg();
    }

    @Override
    protected void onError(ApiException ex) {

        DialogHelper.stopProgressDlg();
        Log.d(TAG, "onError: " + ex.message + "code: " + ex.code);
        Toast.makeText(mContext, ex.message , Toast.LENGTH_SHORT).show();
    }

    @Override
    public abstract void onNext(T t);
}

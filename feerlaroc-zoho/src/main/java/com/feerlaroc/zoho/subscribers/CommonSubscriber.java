package com.feerlaroc.zoho.subscribers;

import android.content.Context;
import android.widget.Toast;

import com.feerlaroc.zoho.retrofit.exception.ApiException;
import com.feerlaroc.zoho.utils.DialogHelper;
import com.feerlaroc.zoho.utils.NetworkUtil;

import timber.log.Timber;

/**
 * @author YorkYu
 * @version V1.0
 * @Project: Retrofit2RxjavaDemo
 * @Package york.com.retrofit2rxjavademo.subscribers
 * @Description:
 * @time 2016/8/11 10:54
 */
public abstract class CommonSubscriber<T> extends BaseSubscriber<T> {
    public CommonSubscriber(Context context) {
        this.mContext = context;
    }
    private static final String TAG = CommonSubscriber.class.getSimpleName();
    private Context mContext;
    @Override
    public void onStart() {
        super.onStart();
        // if  NetworkAvailable no !   must to call onCompleted
        if (!NetworkUtil.isNetworkAvailable(mContext)) {
            Toast.makeText(mContext, "Network is not available!", Toast.LENGTH_SHORT).show();
            onCompleted();
        } else {
            Timber.i(TAG, "network available");
        }
    }

    @Override
    public void onCompleted() {
        Timber.d(TAG, "onCompleted~ ");
        DialogHelper.stopProgressDlg();
    }

    @Override
    protected void onError(ApiException ex) {

        DialogHelper.stopProgressDlg();
        Timber.e(TAG, "onError: " + ex.message + "code: " + ex.code);
        Toast.makeText(mContext, ex.message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public abstract void onNext(T t);
}

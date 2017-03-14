package com.feerlaroc.zoho.rx;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.List;
import java.util.Map;

import rx.Observable;

/**
 * Created by root on 2017/01/14.
 */

public abstract class RxZohoListAdapter<T> extends ArrayAdapter {

    public RxZohoListAdapter(Context context, int resource) {

        super(context, resource);
    }

    public abstract Observable<List<Map<String, Object>>> getObservable(String entityID);

    public void updateDataset(List<Map<String, Object>> mapList) {

        clear();

        Observable
                .from(mapList)
                .forEach(this::add);

        notifyDataSetChanged();
    }
}

package com.feerlaroc.zoho.rx;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.functions.Action1;

//import com.feerlaroc.invoices.schema2.customers.view.CustomerDisplayView2;
//
//import org.four4labs.widget.DataPinView;


public class RxZohoDataSource<T> {

    private static final String TAG = "RxZohoDataSource";

    private RxZohoListAdapter mListAdapter;
    private RxZohoRecyclerAdapter mRecyclerAdapter;

    private List<T> mDataObjects = new ArrayList<>();

    private Context mContext;
    private View mView;

    public RxZohoDataSource(RxZohoRecyclerAdapter adapter){

        mRecyclerAdapter = adapter;
    }

    public RxZohoDataSource(Context context, RxZohoListAdapter adapter){

        mContext = context;
        mListAdapter = adapter;
    }

    /***
     * For updating Adapter
     */
    public void updateAdapter() {

        if (mRecyclerAdapter != null) {
            //update the update
            mRecyclerAdapter.updateDataset(mDataObjects);

        }  else if(mListAdapter != null){

            mListAdapter.clear();
            mListAdapter.updateDataset(mDataObjects);
            mView.requestLayout();
        }
    }

    public RxZohoDataSource bindRecycleView(RecyclerView recyclerView){

        mView = recyclerView;
        if (mRecyclerAdapter != null) {

            recyclerView.setAdapter(mRecyclerAdapter);
        }
        return this;
    }

   /* public RxZohoDataSource bindPinView(DataPinView view){

        mView = view;
        if (mListAdapter != null) {

            view.setAdapter(mListAdapter);
        }
        return this;
    }*/

    public RxZohoDataSource bindAdapterView(AdapterView view){

        mView = view;
        if (mListAdapter != null) {

            view.setAdapter(mListAdapter);
        }
        return this;
    }

    public void subscribeTo(Observable<List<Map<String, Object>>> observable){

        observable.subscribe(new Action1<List<Map<String, Object>>>() {
            @Override
            public void call(List<Map<String, Object>> data) {

                mDataObjects.clear();
                mDataObjects = (List<T>) data;
                updateAdapter();
            }
        });
    }

    /*public void viewToReset(CustomerDisplayView2 displayView2) {

        mViewToReset = displayView2;
    }*/
}

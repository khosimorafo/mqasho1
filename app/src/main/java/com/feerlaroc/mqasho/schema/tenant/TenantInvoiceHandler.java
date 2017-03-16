package com.feerlaroc.mqasho.schema.tenant;

import com.feerlaroc.mqasho.schema.invoice.InvoiceFactory;
import com.feerlaroc.mqasho.schema.items.ItemObservableFactory;
import com.feerlaroc.mqasho.schema.items.LineItemCreator;
import com.feerlaroc.utils.datetime.utils.FeerlarocDateUtils;
import com.feerlaroc.zoho.entity.HttpResult;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subjects.BehaviorSubject;

/**
 * Created by root on 2017/03/15.
 */

public class TenantInvoiceHandler {

    private InvoiceFactory mInvoiceFactory = new InvoiceFactory().initialize();

    private static BehaviorSubject<String> mInvoiceNumberSubject = BehaviorSubject.create();
    private static BehaviorSubject<String> mInvoiceDateSubject = BehaviorSubject.create();
    private static BehaviorSubject<String> mInvoiceDueDateSubject = BehaviorSubject.create();

    private static BehaviorSubject<List<Map<String, Object>>> mLineItemSubject = BehaviorSubject.create();

    public TenantInvoiceHandler(){

        configureObservables();
        setItem();
        initializeInvoice();
    }

    public void setItem(){

        ItemObservableFactory.getItemByName("Monthly Rental")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .forEach(itemSubscribe());
    }


    private Action1<HttpResult<List<Map<String, Object>>>> itemSubscribe() {

        return result -> {

            if(result.getCode() == 0){

                List<Map<String,Object>> list = new ArrayList<>();
                list.add(LineItemCreator.get(result.getData().get(0), 1d));
                mLineItemSubject
                        .onNext(list);
            }
        };
    }

    public Observable<HttpResult<Map<String, Object>>> saveInvoice(){

        return mInvoiceFactory.post();
    }

    private void configureObservables(){

        mInvoiceFactory.captureCustomerID(CustomerObservable.getCustomerIDSubject());
        mInvoiceFactory.captureInvoiceNumber(mInvoiceNumberSubject);
        mInvoiceFactory.captureInvoiceDate(mInvoiceDateSubject);
        mInvoiceFactory.captureDueDate(mInvoiceDueDateSubject);
        mInvoiceFactory.captureLineItem(mLineItemSubject);
    }

    private void initializeInvoice(){

        mInvoiceDateSubject
                .onNext(FeerlarocDateUtils.getSimpleDate(new DateTime().getMillis()));

        DateTime firstDayOfMonth = new DateTime().plusMonths(1).dayOfMonth().withMinimumValue();
        mInvoiceDueDateSubject
                .onNext(FeerlarocDateUtils.getSimpleDate(firstDayOfMonth.getMillis()));

        CustomerObservable.getCustomerIDSubject().subscribe(new Action1<String>() {
            @Override
            public void call(String s) {

                mInvoiceNumberSubject
                        .onNext(s + "-" + FeerlarocDateUtils.getMonthAndYear(firstDayOfMonth));
            }
        });



    }
}

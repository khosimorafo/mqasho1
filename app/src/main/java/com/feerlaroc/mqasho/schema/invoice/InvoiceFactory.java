package com.feerlaroc.mqasho.schema.invoice;

import com.feerlaroc.mqasho.http.ServiceFactory;
import com.feerlaroc.mqasho.schema.BaseEntityFactory;
import com.feerlaroc.mqasho.schema.Constants;
import com.feerlaroc.zoho.entity.HttpResult;

import org.json.JSONObject;

import java.util.List;
import java.util.Map;

import rx.Observable;

public class InvoiceFactory extends BaseEntityFactory<InvoiceFactory> {

    @Override
    public Observable<HttpResult<Map<String, Object>>> post() {

        JSONObject _json = new JSONObject(mData);
        return ServiceFactory.invoiceApi()
                .createInvoice(Constants.ZOHO.TOKEN, Constants.ZOHO.ORG_ID, _json);
    }

    @Override
    public InvoiceFactory initialize() {
        return this;
    }

    @Override
    public void clearData() {}

    public void captureCustomerID(Observable<String> observable){

        observable.subscribe(s -> {
            mData.put(Constants.INVOICESCHEMA.CUSTOMER_ID, s);
        });
    }

    public void captureInvoiceNumber(Observable<String> observable){

        observable.subscribe(s -> {
            mData.put(Constants.INVOICESCHEMA.INVOICE_NUMBER, s);
        });
    }

    public void captureInvoiceDate(Observable<String> observable){

        observable.subscribe(s -> {
            mData.put(Constants.INVOICESCHEMA.INVOICE_DATE, s);
        });
    }

    public void captureDueDate(Observable<String> observable){

        observable.subscribe(s -> {
            mData.put(Constants.INVOICESCHEMA.DUE_DATE, s);
        });
    }

    public void captureLineItem(Observable<List<Map<String,Object>>> observable){

        observable.subscribe(list -> {
            mData.put(Constants.INVOICESCHEMA.LINEITEMS, list);
        });
    }
}


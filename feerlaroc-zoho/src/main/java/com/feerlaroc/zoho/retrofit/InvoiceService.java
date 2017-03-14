package com.feerlaroc.zoho.retrofit;

import com.feerlaroc.zoho.entity.HttpResult;

import org.json.JSONObject;

import java.util.List;
import java.util.Map;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface InvoiceService {

    @GET("invoices")
    Observable<HttpResult<List<Map<String, Object>>>>
    get(@Query("authtoken") String token,
        @Query("organization_id") String org_id);

    @GET("invoices")
    Observable<HttpResult<List<Map<String, Object>>>>
    getInvoiceByCustomerID(@Query("authtoken") String token,
                           @Query("organization_id") String org_id,
                           @Query("customer_id") String customer_id);

    @GET("/api/v3/invoices/{invoice_id}")
    public Observable<HttpResult<Map<String, Object>>>
    getSingleInvoice(@Path("invoice_id") String invoice_id,
                     @Query("authtoken") String token,
                     @Query("organization_id") String org_id);


    @FormUrlEncoded
    @POST("/api/v3/invoices")
    public Observable<HttpResult<Map<String, Object>>>
    createInvoice(@Query("authtoken") String token,
                  @Query("organization_id") String org_id,
                  @Field("JSONString") JSONObject json);


    @FormUrlEncoded
    @POST("/api/v3/recurringinvoices")
    public Observable<HttpResult<Map<String, Object>>>
    createRecurringInvoice(@Query("authtoken") String token,
                           @Query("organization_id") String org_id,
                           @Field("JSONString") JSONObject json);
}

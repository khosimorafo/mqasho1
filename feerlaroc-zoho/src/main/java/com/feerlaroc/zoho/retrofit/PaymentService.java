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

public interface PaymentService{

    @GET("customerpayments")
    Observable<HttpResult<List<Map<String, Object>>>>
    get(@Query("authtoken") String token,
        @Query("organization_id") String org_id);


    @GET("/api/v3/customerpayments/{payment_id}")
    public Observable<HttpResult<Map<String, Object>>>
    getInvoicePayment(@Path("payment_id") String payment_id,
                      @Query("authtoken") String token,
                      @Query("organization_id") String org_id);

    @GET("customerpayments")
    Observable<HttpResult<List<Map<String, Object>>>>
    getPaymentsByCustomerID(@Query("authtoken") String token,
                            @Query("organization_id") String org_id,
                            @Query("customer_id") String customer_id);

    @FormUrlEncoded
    @POST("/api/v3/customerpayments")
    public Observable<HttpResult<Map<String, Object>>>
    createPaymentObservable(@Query("authtoken") String token,
                            @Query("organization_id") String org_id,
                            @Field("JSONString") JSONObject json);
}

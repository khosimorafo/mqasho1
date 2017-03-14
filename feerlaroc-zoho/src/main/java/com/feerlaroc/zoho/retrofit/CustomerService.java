package com.feerlaroc.zoho.retrofit;

import com.feerlaroc.zoho.entity.HttpResult;

import org.json.JSONObject;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;


public interface CustomerService {

    @GET("contacts")
    Observable<HttpResult<List<Map<String, Object>>>>
    get(@Query("authtoken") String token,
        @Query("organization_id") String org_id);

    @GET("contacts")
    Call<HttpResult<List<Map<String, Object>>>>
    getCustomers(@Query("authtoken") String token,
                 @Query("organization_id") String org_id);

    @GET("/api/v3/contacts/{contact_id}")
    public Observable<HttpResult<Map<String, Object>>>
    getSingleCustomer(@Path("contact_id") String contact_id,
                      @Query("authtoken") String token,
                      @Query("organization_id") String org_id);

    @FormUrlEncoded
    @POST("/api/v3/contacts")
    public Observable<HttpResult<Map<String, Object>>>
    createCustomer(@Query("authtoken") String token,
                   @Query("organization_id") String org_id,
                   @Field("JSONString") JSONObject json);
}

package com.feerlaroc.zoho.retrofit;


import com.feerlaroc.zoho.entity.HttpResult;

import java.util.List;
import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by root on 2016/12/07.
 */

public interface ItemService {

    @GET("items")
    Observable<HttpResult<List<Map<String, Object>>>>
    get(@Query("authtoken") String token,
        @Query("organization_id") String org_id);

    @GET("items")
    Observable<HttpResult<List<Map<String, Object>>>>
    getItemByName(@Query("authtoken") String token,
                  @Query("organization_id") String org_id,
                  @Query("name") String name);
}

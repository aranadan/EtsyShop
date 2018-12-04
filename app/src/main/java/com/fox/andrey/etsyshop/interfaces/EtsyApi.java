package com.fox.andrey.etsyshop.interfaces;


import com.fox.andrey.etsyshop.ActiveObject;
import com.fox.andrey.etsyshop.PageTitle;


import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface EtsyApi {

    @GET("/v2/taxonomy/categories")
    Observable<PageTitle> getCategories(@Query("api_key") String key);

    @GET("/v2/listings/active")
    Observable<ActiveObject> getActive(@Query("api_key") String key, @Query("category") String category, @Query("keywords") String keywords);


}

package com.fox.andrey.etsyshop.interfaces;


import com.fox.andrey.etsyshop.PageTitle;


import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface EtsyApi {
    @GET("/v2/taxonomy/categories")
    Observable<PageTitle> getMessages(@Query("api_key") String key);
}

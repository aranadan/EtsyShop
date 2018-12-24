package com.fox.andrey.etsyshop.interfaces;


import com.fox.andrey.etsyshop.ActiveObject;
import com.fox.andrey.etsyshop.ImagesResult;
import com.fox.andrey.etsyshop.PageTitle;


import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface EtsyApi {

    //запрос на категории
    @GET("taxonomy/categories")
    Observable<PageTitle> getCategories(@Query("api_key") String key);

    //запрос по ключевому слову
    @GET("listings/active")
    Observable<ActiveObject> getActive(@Query("api_key") String key, @Query("category") String category, @Query("keywords") String keywords);

    //запрос на фотографию
    @GET("listings/{listing_id}/images/{listing_image_id}")
    Observable<Object> getImage(@Path("listing_id") int listingId, @Path("listing_image_id") int imageId, @Query("api_key") String key);

    //запрос на все фотографии
    @GET("listings/{listing_id}/images")
    Observable<ImagesResult> getAllImages(@Path("listing_id") int listingId, @Query("api_key") String key);

}

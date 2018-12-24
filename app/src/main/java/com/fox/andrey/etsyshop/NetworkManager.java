package com.fox.andrey.etsyshop;

import android.util.Log;

import com.fox.andrey.etsyshop.interfaces.EtsyApi;


import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

class NetworkManager {
    private static final String BASE_URL = "https://openapi.etsy.com/v2/";
    private static final String KEY = "3wqphzh72t03m3pkyr8tle86";
    private static final String TAG = "NetworkManager";
    private EtsyApi etsyApi;

    NetworkManager() {
        getApi();
    }

    void getApi() {
        //создаем HttpLoggingInterceptor. В нем настраиваем уровень логирования. Если у нас Debug билд, то выставляем максимальный уровень (BODY), иначе - ничего не логируем, чтобы не палить в логах релизные запросы.
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);

        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder().
                baseUrl(BASE_URL).
                addCallAdapterFactory(RxJava2CallAdapterFactory.create()).
                addConverterFactory(GsonConverterFactory.create()).
                client(client).
                build();

        etsyApi = retrofit.create(EtsyApi.class);
    }

    //загрузка данных по запросу
    Observable<ActiveObject> getActive(String category, String keywords) {
        return etsyApi.getActive(KEY, category, keywords);
    }

    //Отправляю запрос на список категорий
    Observable<PageTitle> getCategory() {
        return etsyApi.getCategories(KEY);
    }

    //Запрос на фотографию
    Observable<Object>getImage(int listId, int imageId){
        return etsyApi.getImage(listId, imageId, KEY);
    }

    ArrayList<ImageItem>getAllImages(int listId){
        ArrayList<ImageItem> list = new ArrayList<>();
        etsyApi.getAllImages(listId, KEY)
                .observeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(imagesResult -> Observable.just(imagesResult.getResults()))
                .subscribe(results -> list.addAll(results),throwable -> Log.d(TAG, throwable.getMessage()));

        return list;
    }



}

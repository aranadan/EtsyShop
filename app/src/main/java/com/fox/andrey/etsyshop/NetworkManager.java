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

public class NetworkManager {
    private static final String TAG = "NetworkManager";
    private static final String BASE_URL = "https://openapi.etsy.com";
    private static final String KEY = "3wqphzh72t03m3pkyr8tle86";
    private EtsyApi etsyApi;

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
    protected ArrayList<ActiveResult> getActive(String category, String keywords) {
        Log.d(TAG, "getActive");

        ArrayList<ActiveResult> resultsActiveArray = new ArrayList<>();

        Observable<ActiveObject> active = etsyApi.getActive(KEY, category, keywords);
        active.
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                flatMap(activeObject -> Observable.just(activeObject.getResults())).
                subscribe(result -> resultsActiveArray.addAll(result), throwable -> Log.d(TAG, throwable.getMessage()));

        return resultsActiveArray;
    }

    //Отправляю запрос на список категорий
    protected Observable<PageTitle> getCategory() {
        Observable<PageTitle> pageTitle = etsyApi.getCategories(KEY);
        return pageTitle;
    }


}

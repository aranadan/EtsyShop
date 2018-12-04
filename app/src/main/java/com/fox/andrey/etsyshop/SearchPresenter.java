package com.fox.andrey.etsyshop;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import com.fox.andrey.etsyshop.interfaces.EtsyApi;
import com.fox.andrey.etsyshop.interfaces.ListView;
import com.fox.andrey.etsyshop.interfaces.MvpPresenter;
import com.fox.andrey.etsyshop.interfaces.SearchView;

import java.util.ArrayList;
import java.util.HashMap;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchPresenter implements MvpPresenter {
    private static final String TAG = "SearchPresenter";
    private static final String BASE_URL = "https://openapi.etsy.com";
    private static final String KEY = "3wqphzh72t03m3pkyr8tle86";
    private String mSearchText;

    private HashMap<String,String> resultCategoryHashMap;
    private ArrayList<ActiveResult> resultsActiveArray;
    private EtsyApi etsyApi;


    //Компоненты MVP приложения
    private SearchView mSearchView;
    private ListView mListView;
    private Activity mSearchActivity;
    private String categoryName;

    //SearchView конструктор
    public SearchPresenter(SearchView view, Activity activity) {
        mSearchView = view;
        mSearchActivity = activity;

        resultCategoryHashMap = new HashMap<>();


        Log.d(TAG, "SearchView Constructor");

        getApi();
        getCategory();
    }

    //ListView конструктор
    public SearchPresenter(ListView view){
        Log.d(TAG, "ListView Constructor" + view.getClass());
        mListView = view;


    }

    //View сообщает, что спинер был нажат
    @Override
    public void onSpinnerClick() {
        Log.d(TAG, "onSpinnerClick");
       createDialog();
    }

    //View сообщает, что кнопка была нажата
    @Override
    public void onSubmitClick(String searchText) {
        this.mSearchText = searchText;
        mSearchActivity.startActivity(new Intent(mSearchActivity, ListActivity.class));
        resultsActiveArray = new ArrayList<>();
        Log.d(TAG, "onSubmitClick");
        getActive();

    }
    //загрузка данных по запросу
    private void getActive() {
        Log.d(TAG, "getActive");

        Observable<ActiveObject> active = etsyApi.getActive(KEY, categoryName, mSearchText);
        active.
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                flatMap(activeObject -> Observable.just(activeObject.getResults())).
                subscribe(result -> resultsActiveArray.addAll(result) , throwable ->Log.d(TAG, throwable.getMessage()),() ->  mListView.showData(resultsActiveArray));


    }

    //загрузка списка категорий товаров
    private void getCategory(){

        Observable<PageTitle> pageTitle = etsyApi.getCategories(KEY);
        pageTitle.
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                // из PageTitle в Result
                flatMap(pageTitle1 -> Observable.just(pageTitle1.getResults())).
                // прохожу по каждому элементу списка
                flatMapIterable(list -> list).
                subscribe(titleItem -> resultCategoryHashMap.put(titleItem.getShortName(),titleItem.getCategoryName()), throwable -> Log.d(TAG, throwable.getMessage()));
    }

        //
    void createDialog() {
        // конвертиция в массив для отображения в диалоговом окне
        CharSequence[] sequences = resultCategoryHashMap.keySet().toArray(new CharSequence[0]);

        // строю диалог
        AlertDialog.Builder builder = new AlertDialog.Builder(mSearchActivity);
        builder.
                setTitle(R.string.set_category).
                setItems(sequences, (dialog, which) -> {
                    String category = (String) sequences[which];
                    mSearchView.showData(category);
                    categoryName = resultCategoryHashMap.get(category);
                    Log.d(TAG, categoryName);
                });
        builder.create().show();
    }

    void getApi() {
        //создаем HttpLoggingInterceptor. В нем настраиваем уровень логирования. Если у нас Debug билд, то выставляем максимальный уровень (BODY), иначе - ничего не логируем, чтобы не палить в логах релизные запросы.
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder().
                baseUrl(BASE_URL).
                addCallAdapterFactory(RxJava2CallAdapterFactory.create()).
                addConverterFactory(GsonConverterFactory.create()).
               // client(client).
                build();

        etsyApi = retrofit.create(EtsyApi.class);
    }

    void printItems(){
        mListView.showData(resultsActiveArray);
    }
}

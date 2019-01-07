package com.fox.andrey.etsyshop;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkRequest;
import android.support.design.widget.Snackbar;
import android.util.Log;

import com.fox.andrey.etsyshop.interfaces.MvpPresenter;
import com.fox.andrey.etsyshop.interfaces.MvpView;

import java.util.HashMap;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class SearchPresenter implements MvpPresenter {
    private static final String TAG = "SearchPresenter";
    private HashMap<String, String> listCategory;


    //Компоненты MVP приложения
    private SearchActivity searchView;

    //View сообщает, что кнопка категорий нажата
    @Override
    public void onCategoryClick() {
        Log.d(TAG, "onCategoryClick"  );
        // вызываю диалог выбора категории по завершению
        searchView.createDialog(listCategory);
    }

   /* //View сообщает, что кнопка была нажата
    @Override
    public void onSubmitClick(String category, String searchText) {
        Log.d(TAG, "onSubmitClick " + category);

        //networkManager.getActive(category,searchText);
    }*/

    @Override
    public void attachView(MvpView view){
        searchView = (SearchActivity) view;
        isOnlineNew();
        getCategorySequences();
    }

    @Override
    public void detachView(){
        searchView = null;
    }

    //получаю список категория
    private void getCategorySequences() {
            listCategory = new HashMap<>();
        NetworkManager networkManager = new NetworkManager();

            Observable<PageTitle> titleObservable = networkManager.getCategory();
            titleObservable.
                    subscribeOn(Schedulers.io()).
                    observeOn(AndroidSchedulers.mainThread()).
                    // из PageTitle в Result
                            flatMap(pageTitle1 -> Observable.just(pageTitle1.getResults())).
                    // прохожу по каждому элементу списка
                            flatMapIterable(list -> list).
                    subscribe(titleItem -> listCategory.put(titleItem.getShortName(),titleItem.getCategoryName()), throwable -> Log.d(TAG, throwable.getMessage()), () -> Log.d(TAG,"list download"));

    }

    // TODO: 07.01.2019 реализовать проверку интернет соединения при старте приложения 
    //увдеомления о состоянии сети
    void isOnlineNew(){
        ConnectivityManager connectivityManager = (ConnectivityManager) searchView.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkRequest.Builder builder = new NetworkRequest.Builder();

        connectivityManager.registerNetworkCallback(builder.build(),
                new ConnectivityManager.NetworkCallback(){
                    @Override
                    public void onLost(Network network) {
                        Snackbar.make(searchView.findViewById(R.id.frameLayout), "Lost Internet connection!", Snackbar.LENGTH_LONG).show();
                    }

                    @Override
                    public void onAvailable(Network network) {
                        Log.d(TAG,"onAvailable");
                    }

                    @Override
                    public void onUnavailable() {
                        Log.d(TAG,"onUnavailable");
                    }
                });
    }

}

package com.fox.andrey.etsyshop;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkRequest;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import com.fox.andrey.etsyshop.interfaces.MvpPresenter;
import com.fox.andrey.etsyshop.interfaces.MvpView;

import java.util.HashMap;
import java.util.Objects;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class SearchPresenter implements MvpPresenter {
    private static final String TAG = "SearchPresenter";
    private HashMap<String, String> listCategory;
    private String selectedCategoryName;
    private AlertDialog alertDialog;
    //Компоненты MVP приложения
    private SearchActivity searchView;

    @Override
    public void attachView(MvpView view) {
        searchView = (SearchActivity) view;
        isOnlineNew();
        createFragment();

        //отображаю выбранную категорию
        //showData(selectedCategoryName);
    }

    @Override
    public void detachView() {
        //отключаю диалог выбора категории если он отображается в момент поворота экрана
        if (alertDialog != null) {
            alertDialog.dismiss();
        }
        searchView = null;
    }

    //получаю список категория
    private void getCategorySequences() {
        if (listCategory == null) {
            Log.d(TAG, "download list from internet");
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
                    subscribe(titleItem -> listCategory.put(titleItem.getShortName(), titleItem.getCategoryName()), throwable -> Log.d(TAG, throwable.getMessage()), () -> createDialog(listCategory));
        }else {
            Log.d(TAG, "download list from storage");
            createDialog(listCategory);
        }
    }

    // TODO: 07.01.2019 реализовать проверку интернет соединения при старте приложения 
    //увдеомления о состоянии сети
    private void isOnlineNew(){
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

    // строю диалог
    private void createDialog(HashMap<String, String> map) {
        CharSequence[] sequences = map.keySet().toArray(new CharSequence[0]);

        AlertDialog.Builder builder = new AlertDialog.Builder(searchView);
        builder.
                setTitle(R.string.set_category).
                setItems(sequences, (dialog, which) -> {
                    //имя категории для запроса
                    selectedCategoryName = map.get(sequences[which]);
                    //отображаю выбраную категорию
                    showData(selectedCategoryName);
                });
        alertDialog = builder.create();
        alertDialog.show();
    }

    private void showData(String category) {
        if (category != null) {
            SearchTabFragment fragment = (SearchTabFragment) searchView.getSupportFragmentManager().findFragmentById(R.id.frameLayout);
            Objects.requireNonNull(fragment).showCategory(category);
        }
    }

    private void createFragment(){
        SearchTabFragment fragment = new SearchTabFragment();
        FragmentTransaction fragmentTransaction = searchView.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.frameLayout, fragment);
        fragmentTransaction.commit();

        // TODO: 09.01.2019 присвоить имя категории при повороте экрана
        if (selectedCategoryName != null){
            fragment.showCategory(selectedCategoryName);
            Log.d(TAG,selectedCategoryName);
        }
    }

    @Override
    public void onClick() {
        getCategorySequences();
    }

}

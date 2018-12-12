package com.fox.andrey.etsyshop;

import android.util.Log;

import com.fox.andrey.etsyshop.interfaces.MvpPresenter;
import com.fox.andrey.etsyshop.interfaces.MvpView;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class SearchPresenter implements MvpPresenter {
    private static final String TAG = "SearchPresenter";
    private NetworkManager networkManager;
    private ArrayList<String> listCategory;


    //Компоненты MVP приложения
    private SearchActivity searchView;

    //View сообщает, что кнопка категорий нажата
    @Override
    public void onCategoryClick() {
        Log.d(TAG, "onCategoryClick");
        getCategorySequences();

    }

    //View сообщает, что кнопка была нажата
    @Override
    public void onSubmitClick(String category, String searchText) {
        Log.d(TAG, "onSubmitClick");
        networkManager.getActive(category,searchText);
    }

    @Override
    public void attachView(MvpView view){
        searchView = (SearchActivity) view;
        networkManager = new NetworkManager();
        networkManager.getApi();
    }

    @Override
    public void detachView(){
        searchView = null;
    }

    //получаю список категория и вызываю диалог выбора категории по завершению
    private void getCategorySequences() {
        if (listCategory == null) {

            listCategory = new ArrayList<>();

            Observable<PageTitle> titleObservable = networkManager.getCategory();
            titleObservable.
                    subscribeOn(Schedulers.io()).
                    observeOn(AndroidSchedulers.mainThread()).
                    // из PageTitle в Result
                            flatMap(pageTitle1 -> Observable.just(pageTitle1.getResults())).
                    // прохожу по каждому элементу списка
                            flatMapIterable(list -> list).
                    subscribe(titleItem -> listCategory.add(titleItem.getShortName()), throwable -> Log.d(TAG, throwable.getMessage()), () -> searchView.createDialog(listCategory.toArray(new CharSequence[0])));
        }else{
            searchView.createDialog(listCategory.toArray(new CharSequence[0]));
        }
    }


}

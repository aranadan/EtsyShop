package com.fox.andrey.etsyshop;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkRequest;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AlertDialog;
import android.util.Log;

import com.fox.andrey.etsyshop.data.DbHelper;
import com.fox.andrey.etsyshop.interfaces.MvpSearchPresenter;
import com.fox.andrey.etsyshop.interfaces.MvpView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static com.fox.andrey.etsyshop.data.DbContract.*;


public class SearchPresenter implements MvpSearchPresenter {
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
        } else {
            Log.d(TAG, "download list from storage");
            createDialog(listCategory);
        }
    }

    // TODO: 07.01.2019 реализовать проверку интернет соединения при старте приложения 
    //увдеомления о состоянии сети
    private void isOnlineNew() {
        ConnectivityManager connectivityManager = (ConnectivityManager) searchView.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkRequest.Builder builder = new NetworkRequest.Builder();

        connectivityManager.registerNetworkCallback(builder.build(), new ConnectivityManager.NetworkCallback() {
            @Override
            public void onLost(Network network) {
                Snackbar.make(searchView.findViewById(R.id.frameLayout), "Lost Internet connection!", Snackbar.LENGTH_LONG).show();
            }

            @Override
            public void onAvailable(Network network) {
                Log.d(TAG, "onAvailable");
            }

            @Override
            public void onUnavailable() {
                Log.d(TAG, "onUnavailable");
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

    @Override
    public void onClick() {
        getCategorySequences();
    }

    @Override
    public ArrayList<ActiveResult> getSavedList() {
        ArrayList<ActiveResult> list = new ArrayList<>();
        DbHelper mDbHelper = new DbHelper(searchView);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        String[] columns = {ItemsEntry._ID, ItemsEntry.COLUMN_CURRENT_CODE, ItemsEntry.COLUMN_DESCRIPTION, ItemsEntry.COLUMN_PRICE, ItemsEntry.COLUMN_TITLE, ItemsEntry.COLUMN_LISTING_ID};

        Cursor cursor = db.query(ItemsEntry.TABLE_NAME,   // таблица
                columns,                        // столбцы
                null,                  // столбцы для условия WHERE
                null,               // значения для условия WHERE
                null,                   // Don't group the rows
                null,                    // Don't filter by row groups
                null);                  // порядок сортировки

// Узнаем индекс каждого столбца
        int idIndex = cursor.getColumnIndex(ItemsEntry._ID);
        int priceIndex = cursor.getColumnIndex(ItemsEntry.COLUMN_PRICE);
        int codeIndex = cursor.getColumnIndex(ItemsEntry.COLUMN_CURRENT_CODE);
        int titleIndex = cursor.getColumnIndex(ItemsEntry.COLUMN_TITLE);
        int descriptionIndex = cursor.getColumnIndex(ItemsEntry.COLUMN_DESCRIPTION);
        int listingIdIndex = cursor.getColumnIndex(ItemsEntry.COLUMN_LISTING_ID);

// Проходим через все ряды
        while (cursor.moveToNext()) {

            ActiveResult activeResult = new ActiveResult();
            activeResult.setCategoryId(cursor.getInt(idIndex));
            activeResult.setPrice(cursor.getString(priceIndex));
            activeResult.setCurrencyCode(cursor.getString(codeIndex));
            activeResult.setTitle(cursor.getString(titleIndex));
            activeResult.setDescription(cursor.getString(descriptionIndex));
            activeResult.setListingId(cursor.getInt(listingIdIndex));

            list.add(activeResult);
        }
        return list;
    }
}

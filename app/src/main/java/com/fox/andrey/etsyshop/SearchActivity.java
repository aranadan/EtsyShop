package com.fox.andrey.etsyshop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;

import com.fox.andrey.etsyshop.interfaces.CallBackSearchActivity;
import com.fox.andrey.etsyshop.interfaces.MvpPresenter;
import com.fox.andrey.etsyshop.interfaces.MvpView;

import java.util.HashMap;


public class SearchActivity extends AbstractView implements  MvpView, CallBackSearchActivity {
    private static final String TAG = "SearchActivity";
    private MvpPresenter presenter;
    private AlertDialog alertDialog;
    private String categoryName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        createFragment();
        super.attachPresenter();
        presenter = super.getPresenter();
    }

    @Override
    public void onCategoryClick() {
        presenter.onCategoryClick();
    }

    @Override
    public void onSubmitClick(String searchText) {
        Intent intent = new Intent(getApplicationContext(), ListActivity.class);
        intent.putExtra("category", categoryName);
        intent.putExtra("searchText", searchText);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        //отключаю диалог выбора категории если он отображается в момент поворота экрана
        if (alertDialog != null) {
            alertDialog.dismiss();
        }
    }

    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        return presenter;
    }

    // строю диалог
    void createDialog(HashMap<String, String> map) {
        CharSequence[] sequences = map.keySet().toArray(new CharSequence[0]);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.
                setTitle(R.string.set_category).
                setItems(sequences, (dialog, which) -> {
                    //имя категории для запроса
                    categoryName = map.get(sequences[which]);
                    //отображаю выбраную категорию
                    showData(sequences[which].toString());
                });
        alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    void showData(String category) {
        super.showData(category);
        SearchTabFragment fragment = (SearchTabFragment) getSupportFragmentManager().findFragmentById(R.id.frameLayout);
        fragment.showCategory(category);
    }

    void createFragment(){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.frameLayout, new SearchTabFragment());
        fragmentTransaction.commit();
    }

}

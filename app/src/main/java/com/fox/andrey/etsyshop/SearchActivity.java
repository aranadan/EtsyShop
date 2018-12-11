package com.fox.andrey.etsyshop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;

import com.fox.andrey.etsyshop.interfaces.CallBackSearchActivity;
import com.fox.andrey.etsyshop.interfaces.MvpPresenter;
import com.fox.andrey.etsyshop.interfaces.MvpView;


public class SearchActivity extends AbstractView implements  MvpView, CallBackSearchActivity {
    private MvpPresenter presenter;
    private FragmentTransaction fragmentTransaction;
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
    public void onSubmitClick(String category, String searchText) {
        Intent intent = new Intent(getApplicationContext(), ListActivity.class);
        startActivity(intent);

        presenter.onSubmitClick(category, searchText);
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
    void createDialog(CharSequence[] sequences) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.
                setTitle(R.string.set_category).
                setItems(sequences, (dialog, which) -> {
                    categoryName = (String) sequences[which];
                    showData(categoryName);
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
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.frameLayout, new SearchTabFragment());
        fragmentTransaction.commit();
    }
}

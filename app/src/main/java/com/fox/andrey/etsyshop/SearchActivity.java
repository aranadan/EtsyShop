package com.fox.andrey.etsyshop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.fox.andrey.etsyshop.interfaces.CallBackSearchActivity;
import com.fox.andrey.etsyshop.interfaces.MvpPresenter;
import com.fox.andrey.etsyshop.interfaces.MvpView;


public class SearchActivity extends AppCompatActivity implements  MvpView, CallBackSearchActivity {
    private static final String TAG = "SearchActivity";
    private MvpPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        attachPresenter();
    }

    private void attachPresenter() {
        //получаю ранее сохраненный экземпляр презентера
        presenter = (MvpPresenter) getLastCustomNonConfigurationInstance();
        if (presenter == null) {
            presenter = new SearchPresenter();
        }
        presenter.attachView(this);
    }

    @Override
    protected void onDestroy() {
        presenter.detachView();
        super.onDestroy();
    }

    //сохраняю презентер
    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        return presenter;
    }

    @Override
    public void onCategoryClick() {
        presenter.onClick();
    }

    @Override
    public void onSubmitClick(String searchText, String category) {
        Intent intent = new Intent(this, ListActivity.class);
        intent.putExtra("category", category);
        intent.putExtra("searchText", searchText);
        startActivity(intent);
    }



}

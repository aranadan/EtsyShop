package com.fox.andrey.etsyshop;

import android.support.v7.app.AppCompatActivity;

import com.fox.andrey.etsyshop.interfaces.MvpPresenter;
import com.fox.andrey.etsyshop.interfaces.MvpView;

import java.util.ArrayList;

public abstract class AbstractView extends AppCompatActivity implements MvpView{
    MvpPresenter presenter;

    void showData(String category) {}

    void attachPresenter() {
        presenter = (MvpPresenter) getLastCustomNonConfigurationInstance();
        if (presenter == null) {
            presenter = new SearchPresenter();
        }
        presenter.attachView(this);
    }

    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    MvpPresenter getPresenter(){
        return presenter;
    }

}

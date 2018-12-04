package com.fox.andrey.etsyshop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.fox.andrey.etsyshop.interfaces.ListView;
import com.fox.andrey.etsyshop.interfaces.MvpPresenter;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity implements ListView {

    private static final String TAG = "ListActivity";

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG,"onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        SearchPresenter presenter = new SearchPresenter(ListActivity.this);



    }


    @Override
    public void showData(ArrayList<ActiveResult> list) {
        Log.d(TAG,"showData" + list.size());
        //list.forEach(item ->Log.d(TAG, item.getTitle()));
    }
}

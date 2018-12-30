package com.fox.andrey.etsyshop;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.fox.andrey.etsyshop.interfaces.MvpView;

import java.util.ArrayList;


public class ListActivity extends AppCompatActivity implements MvpView {
    private static final String TAG = "ListActivity";

    protected RecyclerView mRecyclerView;
    protected RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    ArrayList<ActiveResult> activeResults;


    ListPresenter presenter;

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG,"onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        String category = getIntent().getStringExtra("category");
        String searchText = getIntent().getStringExtra("searchText");

        presenter = new ListPresenter(this);


        mRecyclerView = findViewById(R.id.my_recycler_view);

        //Если вы уверены, что размер RecyclerView не будет изменяться, вы можете добавить этот код для улучшения производительности:
       // mRecyclerView.setHasFixedSize(true);

        // менеджер компоновки для управления позиционированием своих элементов
        mLayoutManager = new GridLayoutManager(this,2);
        mRecyclerView.setLayoutManager(mLayoutManager);



        // specify an adapter (see also next example)
        presenter.getActiveList(category, searchText);
        mAdapter = new ListAdapter(this, activeResults = new ArrayList<>());
        mRecyclerView.setAdapter(mAdapter);


    }


}

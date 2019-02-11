package com.fox.andrey.etsyshop;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fox.andrey.etsyshop.interfaces.CallBackSavedItemsTab;

public class SavedListTabFragment extends Fragment {
    CallBackSavedItemsTab callBack;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        callBack = (CallBackSavedItemsTab) context;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_list, container, false);

        RecyclerView mRecyclerView = view.findViewById(R.id.my_recycler_view);

        //Если вы уверены, что размер RecyclerView не будет изменяться, вы можете добавить этот код для улучшения производительности:
        mRecyclerView.setHasFixedSize(true);

        // менеджер компоновки для управления позиционированием своих элементов
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(new ListAdapter(getActivity(),callBack.getSavedList()));

        return view;
    }
}

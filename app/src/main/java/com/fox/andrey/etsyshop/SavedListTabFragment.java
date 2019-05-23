package com.fox.andrey.etsyshop;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fox.andrey.etsyshop.interfaces.CallBackSavedItemsTab;

public class SavedListTabFragment extends Fragment {
    CallBackSavedItemsTab callBack;
    RecyclerView mRecyclerView;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        callBack = (CallBackSavedItemsTab) context;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_list, container, false);

        SwipeRefreshLayout swipeRefreshLayout = view.findViewById(R.id.swipeContainer);
        swipeRefreshLayout.setEnabled(false);
        swipeRefreshLayout.setRefreshing(false);

        mRecyclerView = view.findViewById(R.id.my_recycler_view);

        //Если вы уверены, что размер RecyclerView не будет изменяться, вы можете добавить этот код для улучшения производительности:
        mRecyclerView.setHasFixedSize(true);

        // менеджер компоновки для управления позиционированием своих элементов
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        //mRecyclerView.setAdapter(new ListAdapter(getActivity(),callBack.getSavedList()));

        return view;
    }

    // TODO: 16.02.2019 модернизировать обновление списка позиций в адаптере путем обновления адаптер, вместо пересозания адаптера 
    @Override
    public void onResume() {
        super.onResume();
        Log.d("SavedListTabFragment", "onResume");
        //каждый раз когда возвращаюсь к фрагменту пересоздаю адаптер, что б обновлять список удаленных продуктов
        mRecyclerView.setAdapter(new ListAdapter(getActivity(),callBack.getSavedList()));
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("SavedListTabFragment", "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("SavedListTabFragment", "onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("SavedListTabFragment", "onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("SavedListTabFragment", "onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d("SavedListTabFragment", "onDetach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("SavedListTabFragment", "onCreate");
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("SavedListTabFragment", "onViewCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("SavedListTabFragment", "onStart");
    }
}

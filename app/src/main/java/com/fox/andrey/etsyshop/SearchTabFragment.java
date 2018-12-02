package com.fox.andrey.etsyshop;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.fox.andrey.etsyshop.interfaces.MvpPresenter;
import com.fox.andrey.etsyshop.interfaces.MvpView;

import java.util.List;


public class SearchTabFragment extends Fragment implements MvpView {
    private static final String TAG = "SearchTabFragment";

    private MvpPresenter presenter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search_tab, container, false);

        presenter = new MainPresenter(this);

        Button spinnerButton = view.findViewById(R.id.categorySpinner);
        spinnerButton.setOnClickListener(view1 -> presenter.onSpinnerClick());

        Button submitButton = view.findViewById(R.id.submitButton);
        submitButton.setOnClickListener(view2 -> presenter.onSubmitClick());

        return view;
    }

    @Override
    public void showCategory(List<Result> list) {
        Log.d(TAG, "showCategory");
        for (Result pageTitle : list) {
            Log.d(TAG, pageTitle.getPageTitle());
        }
    }
}

package com.fox.andrey.etsyshop;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.fox.andrey.etsyshop.interfaces.MvpPresenter;
import com.fox.andrey.etsyshop.interfaces.SearchView;




public class SearchTabFragment extends Fragment implements SearchView {
    private static final String TAG = "SearchTabFragment";

    private MvpPresenter presenter;

    private Button categoryButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search_tab, container, false);

        presenter = new SearchPresenter(this, getActivity());

        EditText searchText = view.findViewById(R.id.editText);

        categoryButton = view.findViewById(R.id.categorySpinner);
        categoryButton.setOnClickListener(view1 -> presenter.onSpinnerClick());

        Button submitButton = view.findViewById(R.id.submitButton);
        submitButton.setOnClickListener(view2 -> presenter.onSubmitClick(searchText.getText().toString()));

        return view;
    }

    @Override
    public void showData(String category) {
        categoryButton.setText(category);
    }
}

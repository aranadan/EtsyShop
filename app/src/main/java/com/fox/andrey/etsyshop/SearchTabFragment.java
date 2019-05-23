package com.fox.andrey.etsyshop;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.fox.andrey.etsyshop.interfaces.CallBackSearchTab;
import com.fox.andrey.etsyshop.interfaces.MvpView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class SearchTabFragment extends Fragment implements MvpView {

    private Unbinder unbinder;

    private CallBackSearchTab callBackSearchActivity;

    @BindView(R.id.categorySpinner)
    Button categoryButton;
    @BindView(R.id.editText)
    EditText searchText;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        callBackSearchActivity = (CallBackSearchTab) context;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search_tab, container, false);
        unbinder = ButterKnife.bind(this,view);
        return view;
    }

    void showCategory(String category) {
        if (categoryButton != null) {
            categoryButton.setText(category);
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("category", categoryButton.getText().toString());
        outState.putString("inputText", searchText.getText().toString());
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);

        if (savedInstanceState != null) {
            categoryButton.setText(savedInstanceState.getString("category"));
            searchText.setText(savedInstanceState.getString("inputText"));
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.categorySpinner)
    void onClick(){
        callBackSearchActivity.onCategoryClick();
    }

    @OnClick(R.id.submitButton)
    void onSubmitClick(){
        callBackSearchActivity.onSubmitClick(searchText.getText().toString(), categoryButton.getText().toString());
    }
}

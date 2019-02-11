package com.fox.andrey.etsyshop;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.fox.andrey.etsyshop.interfaces.CallBackSearchTab;
import com.fox.andrey.etsyshop.interfaces.MvpView;


public class SearchTabFragment extends Fragment implements MvpView {
    private CallBackSearchTab callBackSearchActivity;
    private Button categoryButton;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        callBackSearchActivity = (CallBackSearchTab) context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search_tab, container, false);

        EditText searchText = view.findViewById(R.id.editText);

        categoryButton = view.findViewById(R.id.categorySpinner);
        categoryButton.setOnClickListener(view1 -> callBackSearchActivity.onCategoryClick());

        Button submitButton = view.findViewById(R.id.submitButton);
        submitButton.setOnClickListener(view2 -> callBackSearchActivity.onSubmitClick(searchText.getText().toString(), categoryButton.getText().toString()));

        return view;
    }

    public void showCategory(String category) {
        if (categoryButton != null) {
            categoryButton.setText(category);
        }
    }

}

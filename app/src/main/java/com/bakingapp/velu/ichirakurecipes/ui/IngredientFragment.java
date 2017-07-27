package com.bakingapp.velu.ichirakurecipes.ui;


import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bakingapp.velu.ichirakurecipes.R;
import com.bakingapp.velu.ichirakurecipes.adpater.IngredientListAdapter;
import com.bakingapp.velu.ichirakurecipes.modal.Ingredient;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class IngredientFragment extends Fragment {

    @BindView(R.id.no_of_servings)
    TextView mNoOfServingsText;
    @BindView(R.id.ingredient_recyclerView)
    RecyclerView mRecyclerView;

    private static final String TAG = IngredientFragment.class.getSimpleName();
    public static final String TAB_NAME = "Ingredient";
    public static final String INGREDIENT_LIST = "ingredientList";
    public static final String NO_OF_SERVINGS = "noOfServings";

    private Context mContext;
    private List<Ingredient> mIngredientList;
    private IngredientListAdapter mIngredientListAdapter;
    private int mServingNo;
    private LinearLayoutManager mLinearLayoutManager;
    public static final String LIST_STATE_KEY = "listState";
    private Parcelable mListState;

    public IngredientFragment() {
        // Required empty public constructor
    }

    public static IngredientFragment newInstance(List<Ingredient> ingredientList, int servingsNo) {

        Bundle args = new Bundle();
        args.putSerializable(INGREDIENT_LIST, (Serializable) ingredientList);
        args.putInt(NO_OF_SERVINGS, servingsNo);
        IngredientFragment fragment = new IngredientFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
        if(getArguments() != null){
            mIngredientList = (List<Ingredient>) getArguments().getSerializable(INGREDIENT_LIST);
            mServingNo = getArguments().getInt(NO_OF_SERVINGS);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_ingredient, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeUi();
    }

    private void initializeUi() {

        String noOfServings = getString(R.string.no_of_servings) + " " + mServingNo;
        mNoOfServingsText.setText(noOfServings);
        mLinearLayoutManager = new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mIngredientListAdapter = new IngredientListAdapter(mContext, mIngredientList);
        mRecyclerView.setAdapter(mIngredientListAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        if(mListState != null){
            mLinearLayoutManager.onRestoreInstanceState(mListState);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mListState = mLinearLayoutManager.onSaveInstanceState();
        outState.putParcelable(LIST_STATE_KEY, mListState);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if(savedInstanceState != null){
            mListState = savedInstanceState.getParcelable(LIST_STATE_KEY);
        }
    }
}

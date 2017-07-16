package com.bakingapp.velu.ichirakurecipes.ui;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
public class IngredientFragment extends BaseFragment {

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    private static final String TAG = IngredientFragment.class.getSimpleName();
    public static final String TAB_NAME = "Ingredient";
    public static final String INGREDIENT_LIST = "ingredientList";

    private Context mContext;
    private List<Ingredient> mIngredientList;
    private IngredientListAdapter mIngredientListAdapter;

    public IngredientFragment() {
        // Required empty public constructor
    }

    public static IngredientFragment newInstance(List<Ingredient> ingredientList) {

        Bundle args = new Bundle();
        args.putSerializable(INGREDIENT_LIST, (Serializable) ingredientList);
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
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mIngredientListAdapter = new IngredientListAdapter(mContext, mIngredientList);
        mRecyclerView.setAdapter(mIngredientListAdapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(),
                DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(getActivity().getBaseContext(),
                R.drawable.custom_divider));
        mRecyclerView.addItemDecoration(dividerItemDecoration);

    }
}

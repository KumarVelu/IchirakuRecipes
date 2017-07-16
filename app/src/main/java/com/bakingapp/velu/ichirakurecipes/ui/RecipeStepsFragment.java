package com.bakingapp.velu.ichirakurecipes.ui;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bakingapp.velu.ichirakurecipes.R;
import com.bakingapp.velu.ichirakurecipes.adpater.RecipeStepAdapter;
import com.bakingapp.velu.ichirakurecipes.modal.RecipeStep;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecipeStepsFragment extends Fragment implements RecipeStepAdapter.IOnRecipeStepClickListener{

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    public static final String TAG = RecipeStepsFragment.class.getSimpleName();
    public static final String TAB_NAME = "Steps";
    public static final String RECIPE_STEP_LIST = "recipeStepList";
    private RecipeStepAdapter mRecipeStepAdapter;

    private Context mContext;
    private List<RecipeStep> mRecipeSteps;

    public RecipeStepsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
        if(getArguments() != null){
            mRecipeSteps = (List<RecipeStep>) getArguments().getSerializable(RECIPE_STEP_LIST);
        }
    }

    public static RecipeStepsFragment newInstance(List<RecipeStep> recipeStepList) {
        Bundle args = new Bundle();
        args.putSerializable(RECIPE_STEP_LIST, (Serializable) recipeStepList);
        RecipeStepsFragment fragment = new RecipeStepsFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_recipe_steps, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeUi();
    }

    private void initializeUi() {
        mRecipeStepAdapter = new RecipeStepAdapter(mContext, this, mRecipeSteps);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setAdapter(mRecipeStepAdapter);
    }

    @Override
    public void onRecipeStepClick(int index) {
        Log.i(TAG, "onRecipeStepClick: " + index);
        RecipeVideoTabFragment videoTabFragment = RecipeVideoTabFragment.newInstance(mRecipeSteps, index);
        getFragmentManager().beginTransaction()
                .add(R.id.frame, videoTabFragment)
                .addToBackStack(null)
                .commit();
    }
}

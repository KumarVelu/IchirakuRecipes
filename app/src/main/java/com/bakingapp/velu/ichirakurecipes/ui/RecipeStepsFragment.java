package com.bakingapp.velu.ichirakurecipes.ui;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

    private static final String DUAL_PANE_KEY = "dualPane";

    private Context mContext;
    private List<RecipeStep> mRecipeSteps;
    private boolean mDualPane;

    public RecipeStepsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
        if(getArguments() != null){
            mRecipeSteps = (List<RecipeStep>) getArguments().getSerializable(RECIPE_STEP_LIST);
            mDualPane = getArguments().getBoolean(DUAL_PANE_KEY);
        }
    }

    public static RecipeStepsFragment newInstance(List<RecipeStep> recipeStepList, boolean dualPane) {
        Bundle args = new Bundle();
        args.putSerializable(RECIPE_STEP_LIST, (Serializable) recipeStepList);
        args.putSerializable(DUAL_PANE_KEY, dualPane);
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
        if(mDualPane){
            RecipeVideoFragment videoFragment = RecipeVideoFragment.newInstance(mRecipeSteps.get(index));
            getFragmentManager().beginTransaction()
                    .replace(R.id.video_frame, videoFragment)
                    .commit();
        }
        else{
            Intent intent = new Intent(mContext, InstructionActivity.class);
            intent.putExtra(RECIPE_STEP_LIST, (Serializable) mRecipeSteps);
            intent.putExtra("selectedIndex", index);
            startActivity(intent);
        }
    }
}

package com.bakingapp.velu.ichirakurecipes.ui;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bakingapp.velu.ichirakurecipes.R;
import com.bakingapp.velu.ichirakurecipes.adpater.RecipeVideoViewPagerAdapter;
import com.bakingapp.velu.ichirakurecipes.modal.RecipeStep;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecipeVideoTabFragment extends Fragment {

    @BindView(R.id.viewPager)
    ViewPager mViewPager;

    public static final String TAG = RecipeVideoTabFragment.class.getSimpleName();
    public static final String RECIPE_STEP_LIST = "recipeStepList";
    public static final String CURRENT_ITEM_INDEX = "itemIndex";

    private Context mContext;
    private List<RecipeStep> mRecipeStep;
    private RecipeVideoViewPagerAdapter mVideoViewPagerAdapter;
    private int mCurrentItem;

    public RecipeVideoTabFragment() {
        // Required empty public constructor
    }

    public static RecipeVideoTabFragment newInstance(List<RecipeStep> recipeSteps, int currentItem) {

        Bundle args = new Bundle();
        args.putSerializable(RECIPE_STEP_LIST, (Serializable) recipeSteps);
        args.putInt(CURRENT_ITEM_INDEX, currentItem);
        RecipeVideoTabFragment fragment = new RecipeVideoTabFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();

        if(getArguments() != null){
            mRecipeStep = (List<RecipeStep>) getArguments().getSerializable(RECIPE_STEP_LIST);
            mCurrentItem = getArguments().getInt(CURRENT_ITEM_INDEX);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_recipe_video_tab, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i(TAG, "onViewCreated: " + mCurrentItem);
        initializeUi();
    }

    private void initializeUi() {
        mVideoViewPagerAdapter = new RecipeVideoViewPagerAdapter(getFragmentManager(), mRecipeStep);
        mViewPager.setAdapter(mVideoViewPagerAdapter);
        mViewPager.setCurrentItem(mCurrentItem);
    }
}

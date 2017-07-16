package com.bakingapp.velu.ichirakurecipes.ui;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bakingapp.velu.ichirakurecipes.R;
import com.bakingapp.velu.ichirakurecipes.adpater.IngredientViewPagerAdapter;
import com.bakingapp.velu.ichirakurecipes.modal.Recipe;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecipeDetailFragment extends BaseFragment {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.viewPager)
    ViewPager mViewPager;
    @BindView(R.id.tabs)
    TabLayout mTabLayout;

    private Recipe mRecipe;

    public RecipeDetailFragment() {
        // Required empty public constructor
    }

    public static RecipeDetailFragment newInstance(Recipe recipe) {
        Bundle args = new Bundle();
        args.putSerializable(RecipeListActivity.RECIPE, recipe);
        RecipeDetailFragment fragment = new RecipeDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            mRecipe = (Recipe) getArguments().getSerializable(RecipeListActivity.RECIPE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_recipe_detail, container, false);
        ButterKnife.bind(this, rootView);
        ((AppCompatActivity)getActivity()).setSupportActionBar(mToolbar);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeUi();
    }

    private void initializeUi(){
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });

        setUpViewPager();
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void setUpViewPager() {
        IngredientViewPagerAdapter viewPagerAdapter = new IngredientViewPagerAdapter(getFragmentManager());
        viewPagerAdapter.addFragment(IngredientFragment.newInstance(mRecipe.getmIngredientList()),
                IngredientFragment.TAB_NAME);
        viewPagerAdapter.addFragment(RecipeStepsFragment.newInstance(mRecipe.getmRecipeSteps()),
                RecipeStepsFragment.TAB_NAME);
        mViewPager.setAdapter(viewPagerAdapter);
    }

}

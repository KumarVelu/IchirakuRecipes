package com.bakingapp.velu.ichirakurecipes.adpater;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.bakingapp.velu.ichirakurecipes.modal.RecipeStep;
import com.bakingapp.velu.ichirakurecipes.ui.RecipeVideoFragment;

import java.util.List;

/**
 * Created by Velu on 16/07/17.
 */

public class RecipeVideoViewPagerAdapter extends FragmentStatePagerAdapter {

    private List<RecipeStep> mRecipeSteps;

    public RecipeVideoViewPagerAdapter(FragmentManager fm, List<RecipeStep> recipeSteps) {
        super(fm);
        mRecipeSteps = recipeSteps;
    }

    @Override
    public Fragment getItem(int position) {
        return RecipeVideoFragment.newInstance(mRecipeSteps.get(position));
    }

    @Override
    public int getCount() {
        return mRecipeSteps.size();
    }
}

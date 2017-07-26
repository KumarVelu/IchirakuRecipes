package com.bakingapp.velu.ichirakurecipes.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.bakingapp.velu.ichirakurecipes.R;
import com.bakingapp.velu.ichirakurecipes.modal.Recipe;

public class RecipeDetailActivity extends AppCompatActivity {

    private boolean mDualPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        if (findViewById(R.id.video_frame) != null) {
            mDualPane = true;
        }

        if (savedInstanceState == null)
            loadRecipeDetailFragment();
    }

    private void loadRecipeDetailFragment() {
        Recipe recipe = (Recipe) getIntent().getSerializableExtra(RecipeListActivity.RECIPE);

        RecipeDetailFragment detailFragment = RecipeDetailFragment.newInstance(recipe, mDualPane);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frame, detailFragment)
                .commit();
    }
}

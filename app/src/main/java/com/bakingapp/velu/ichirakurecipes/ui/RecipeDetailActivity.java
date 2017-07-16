package com.bakingapp.velu.ichirakurecipes.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.bakingapp.velu.ichirakurecipes.R;
import com.bakingapp.velu.ichirakurecipes.modal.Recipe;

public class RecipeDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);
        loadRecipeDetailFragment();
    }

    private void loadRecipeDetailFragment(){
        Recipe recipe = (Recipe) getIntent().getSerializableExtra(RecipeListActivity.RECIPE);

        RecipeDetailFragment detailFragment = RecipeDetailFragment.newInstance(recipe);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frame, detailFragment)
                .addToBackStack(null)
                .commit();
    }
}

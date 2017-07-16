package com.bakingapp.velu.ichirakurecipes.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.bakingapp.velu.ichirakurecipes.R;
import com.bakingapp.velu.ichirakurecipes.adpater.RecipeListAdapter;
import com.bakingapp.velu.ichirakurecipes.modal.Recipe;
import com.bakingapp.velu.ichirakurecipes.services.ApiInterface;
import com.bakingapp.velu.ichirakurecipes.services.ServiceGenerator;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecipeListActivity extends AppCompatActivity implements RecipeListAdapter.IRecipeClickListener{

    @BindView(R.id.recyclerView)
    RecyclerView mRecipeRecyclerView;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private RecipeListAdapter mRecipeListAdapter;

    private static final String TAG = RecipeListActivity.class.getSimpleName();
    public static final String RECIPE = "recipe";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);
        ButterKnife.bind(this);
        initialize();
        getRecipeList();
    }

    private void initialize() {
        mRecipeListAdapter = new RecipeListAdapter(this);
        mRecipeRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        mRecipeRecyclerView.setAdapter(mRecipeListAdapter);
    }

    private void getRecipeList(){
        ApiInterface apiInterface = ServiceGenerator.createService(ApiInterface.class);
        Call<List<Recipe>> recipeResultCall = apiInterface.getRecipes();

        recipeResultCall.enqueue(new Callback<List<Recipe>>() {
            @Override
            public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
                Log.i(TAG, "onResponse: response " + response.body());
                setUiWithRecipeList(response.body());
            }

            @Override
            public void onFailure(Call<List<Recipe>> call, Throwable t) {
                Log.i(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    private void setUiWithRecipeList(List<Recipe> recipeList){
        Log.i(TAG, "setUiWithRecipeList: recipelist size " + recipeList.size());
        mRecipeListAdapter.swapData(recipeList);
    }

    @Override
    public void onRecipeClick(Recipe recipe) {
        Intent detailIntent = new Intent(this, RecipeDetailActivity.class);
        detailIntent.putExtra(RECIPE, recipe);
        startActivity(detailIntent);
    }
}
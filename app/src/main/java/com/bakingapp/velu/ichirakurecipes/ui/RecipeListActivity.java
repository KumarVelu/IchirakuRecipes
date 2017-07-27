package com.bakingapp.velu.ichirakurecipes.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bakingapp.velu.ichirakurecipes.R;
import com.bakingapp.velu.ichirakurecipes.adpater.RecipeListAdapter;
import com.bakingapp.velu.ichirakurecipes.modal.Recipe;
import com.bakingapp.velu.ichirakurecipes.services.ApiInterface;
import com.bakingapp.velu.ichirakurecipes.services.ServiceGenerator;
import com.bakingapp.velu.ichirakurecipes.util.NetworkUtils;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecipeListActivity extends AppCompatActivity implements RecipeListAdapter.IRecipeClickListener,
    SwipeRefreshLayout.OnRefreshListener{

    @BindView(R.id.coordinatorLayout)
    CoordinatorLayout mCoordinatorLayout;
    @BindView(R.id.recyclerView)
    RecyclerView mRecipeRecyclerView;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.recipe_list_layout)
    LinearLayout mRecipeListLayout;
    @BindView(R.id.no_internet_layout)
    RelativeLayout mNoInternetLayout;

    private GridLayoutManager mGridLayoutManager;
    private RecipeListAdapter mRecipeListAdapter;
    private List<Recipe> mRecipeList;
    private Parcelable mListState;

    private static final String TAG = RecipeListActivity.class.getSimpleName();
    public static final String RECIPE = "recipe";
    public static final String RECIPE_LIST = "recipeList";
    public static final String LIST_STATE_KEY = "listState";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);
        ButterKnife.bind(this);
        initialize();

        if(savedInstanceState == null)
            getRecipeList();
        else{
            mRecipeList = (List<Recipe>) savedInstanceState.getSerializable(RECIPE_LIST);
            Log.i(TAG, "onCreate: mRecipeList " + mRecipeList);
            setUiWithRecipeList(mRecipeList);
        }
    }

    private void initialize() {
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mRecipeListAdapter = new RecipeListAdapter(this);
        mGridLayoutManager = new GridLayoutManager(this, 2);
        mRecipeRecyclerView.setLayoutManager(mGridLayoutManager);
        mRecipeRecyclerView.setAdapter(mRecipeListAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(mListState != null){
            mGridLayoutManager.onRestoreInstanceState(mListState);
        }
    }

    private void getRecipeList() {

        mSwipeRefreshLayout.setRefreshing(true);
        if (NetworkUtils.isInternetOn(this)) {
            ApiInterface apiInterface = ServiceGenerator.createService(ApiInterface.class);
            Call<List<Recipe>> recipeResultCall = apiInterface.getRecipes();

            recipeResultCall.enqueue(new Callback<List<Recipe>>() {
                @Override
                public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
                    mRecipeList = response.body();
                    setUiWithRecipeList(mRecipeList);
                }

                @Override
                public void onFailure(Call<List<Recipe>> call, Throwable t) {
                }
            });
        }else{
            mRecipeListLayout.setVisibility(View.GONE);
            mNoInternetLayout.setVisibility(View.VISIBLE);
            mSwipeRefreshLayout.setRefreshing(false);

        }
    }

    private void setUiWithRecipeList(List<Recipe> recipeList) {
        mRecipeListAdapter.swapData(recipeList);
        mRecipeListLayout.setVisibility(View.VISIBLE);
        mNoInternetLayout.setVisibility(View.GONE);
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onRecipeClick(Recipe recipe) {
        Intent detailIntent = new Intent(this, RecipeDetailActivity.class);
        detailIntent.putExtra(RECIPE, recipe);
        startActivity(detailIntent);
    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.retry:
                onRefresh();
                break;
        }
    }

    @Override
    public void onRefresh() {
        if (NetworkUtils.isInternetOn(this)) {
            mNoInternetLayout.setVisibility(View.GONE);
            getRecipeList();
        } else {
            mSwipeRefreshLayout.setRefreshing(false);
            Snackbar.make(mCoordinatorLayout,
                    getString(R.string.no_internet), Snackbar.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(RECIPE_LIST, (Serializable) mRecipeList);
        mListState = mGridLayoutManager.onSaveInstanceState();
        outState.putParcelable(LIST_STATE_KEY, mListState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        if(savedInstanceState != null){
            mListState = savedInstanceState.getParcelable(LIST_STATE_KEY);
        }
    }
}
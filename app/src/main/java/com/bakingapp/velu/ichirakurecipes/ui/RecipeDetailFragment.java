package com.bakingapp.velu.ichirakurecipes.ui;


import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bakingapp.velu.ichirakurecipes.Constants;
import com.bakingapp.velu.ichirakurecipes.R;
import com.bakingapp.velu.ichirakurecipes.adpater.IngredientViewPagerAdapter;
import com.bakingapp.velu.ichirakurecipes.modal.Recipe;
import com.bakingapp.velu.ichirakurecipes.widget.IngredientWidgetProvider;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecipeDetailFragment extends Fragment implements View.OnClickListener, SharedPreferences.OnSharedPreferenceChangeListener {

    private static final String TAG = RecipeDetailFragment.class.getSimpleName();
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.viewPager)
    ViewPager mViewPager;
    @BindView(R.id.tabs)
    TabLayout mTabLayout;
    @BindView(R.id.favFab)
    FloatingActionButton mFavFab;

    private static final String DUAL_PANE_KEY = "dualPane";
    private SharedPreferences mSharedPreferences;

    private Recipe mRecipe;
    private Context mContext;
    private boolean mDualPane;

    public RecipeDetailFragment() {
        // Required empty public constructor
    }

    public static RecipeDetailFragment newInstance(Recipe recipe, boolean isDualPane) {
        Bundle args = new Bundle();
        args.putSerializable(RecipeListActivity.RECIPE, recipe);
        args.putBoolean(DUAL_PANE_KEY, isDualPane);
        RecipeDetailFragment fragment = new RecipeDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mRecipe = (Recipe) getArguments().getSerializable(RecipeListActivity.RECIPE);
            mDualPane = getArguments().getBoolean(DUAL_PANE_KEY);
        }
        mContext = getActivity();

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        mSharedPreferences.registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_recipe_detail, container, false);
        ButterKnife.bind(this, rootView);
        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeUi();
    }

    private void initializeUi() {
        mToolbar.setTitle(mRecipe.getmName());
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });

        setUpViewPager();
        mTabLayout.setupWithViewPager(mViewPager);
        mFavFab.setOnClickListener(this);
    }

    private void setUpViewPager() {
        IngredientViewPagerAdapter viewPagerAdapter = new IngredientViewPagerAdapter(getFragmentManager());
        viewPagerAdapter.addFragment(IngredientFragment.newInstance(mRecipe.getmIngredientList(), mRecipe.getmServings()),
                IngredientFragment.TAB_NAME);
        viewPagerAdapter.addFragment(RecipeStepsFragment.newInstance(mRecipe.getmRecipeSteps(), mDualPane),
                RecipeStepsFragment.TAB_NAME);
        mViewPager.setAdapter(viewPagerAdapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.favFab:
                Toast.makeText(mContext, getString(R.string.added_fav), Toast.LENGTH_SHORT).show();
                addToFav();
                break;
        }
    }

    private void addToFav() {
        // store the recipe ingredient in sp to show in widget
        SharedPreferences.Editor spe = mSharedPreferences.edit();
        spe.putString(Constants.RECIPE_KEY, mRecipe.getmName());

        Gson gson = new Gson();
        String json = gson.toJson(mRecipe.getmIngredientList());
        spe.putString(Constants.INGREDIENT_KEY, json).apply();
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals(Constants.RECIPE_KEY)) {
            // update widget
            Intent intent = new Intent(mContext, IngredientWidgetProvider.class);
            intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
            // Use an array and EXTRA_APPWIDGET_IDS instead of AppWidgetManager.EXTRA_APPWIDGET_ID,
               // since it seems the onUpdate() is only fired on that:
            int [] ids = AppWidgetManager.getInstance(getActivity().getApplication())
                    .getAppWidgetIds(new ComponentName(getActivity().getApplication(), IngredientWidgetProvider.class));

            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, ids);
            mContext.sendBroadcast(intent);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mSharedPreferences.unregisterOnSharedPreferenceChangeListener(this);
    }
}

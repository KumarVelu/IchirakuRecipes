package com.bakingapp.velu.ichirakurecipes.widget;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.bakingapp.velu.ichirakurecipes.Constants;
import com.bakingapp.velu.ichirakurecipes.R;
import com.bakingapp.velu.ichirakurecipes.modal.Ingredient;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by Velu on 23/07/17.
 */

public class IngredientListProvider implements RemoteViewsService.RemoteViewsFactory {

    private static final String TAG = IngredientListProvider.class.getSimpleName();

    private List<Ingredient> mIngredientList;
    private Context mContext;

    public IngredientListProvider(Context context, Intent intent) {
        mContext = context;

        populateIngredientList();
    }

    private void populateIngredientList() {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(mContext);
        Gson gson = new Gson();
        String jsonStr = sp.getString(Constants.INGREDIENT_KEY, null);
        Type type = new TypeToken<List<Ingredient>>(){}.getType();

        mIngredientList = gson.fromJson(jsonStr, type);
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return mIngredientList != null ? mIngredientList.size() : 0;
    }

    @Override
    public RemoteViews getViewAt(int position) {
        RemoteViews remoteViews = new RemoteViews(mContext.getPackageName(), R.layout.row_item_widget);
        Ingredient ingredient = mIngredientList.get(position);
        String ingredientDetail = ingredient.getmName() + " " +
                ingredient.getmQuantity() + ingredient.getmMeasure();
        remoteViews.setTextViewText(R.id.ingredient, ingredientDetail);
        return remoteViews;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }
}

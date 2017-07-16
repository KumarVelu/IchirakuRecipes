package com.bakingapp.velu.ichirakurecipes.adpater;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bakingapp.velu.ichirakurecipes.R;
import com.bakingapp.velu.ichirakurecipes.modal.Ingredient;

import java.util.List;

/**
 * Created by Velu on 16/07/17.
 */

public class IngredientListAdapter extends RecyclerView.Adapter<IngredientListAdapter.IngredientListViewHolder>{

    private List<Ingredient> mIngredientList;
    private LayoutInflater mInflater;

    public IngredientListAdapter(Context context, List<Ingredient> ingredientList) {
        mInflater = LayoutInflater.from(context);
        mIngredientList = ingredientList;
    }

    @Override
    public IngredientListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rowItem = mInflater.inflate(R.layout.row_item_ingredient, parent, false);
        return new IngredientListViewHolder(rowItem);
    }

    @Override
    public void onBindViewHolder(IngredientListViewHolder holder, int position) {
        Ingredient ingredient = mIngredientList.get(position);
        holder.ingredientName.setText(ingredient.getmName());
        String quantity = ingredient.getmQuantity() + " " + ingredient.getmMeasure();
        holder.ingredientMeasure.setText(quantity);
    }

    @Override
    public int getItemCount() {
        return mIngredientList == null ? 0 : mIngredientList.size();
    }

    public class IngredientListViewHolder extends RecyclerView.ViewHolder{

        TextView ingredientName;
        TextView ingredientMeasure;

        public IngredientListViewHolder(View itemView) {
            super(itemView);
            ingredientName = (TextView) itemView.findViewById(R.id.ingredient_name);
            ingredientMeasure = (TextView) itemView.findViewById(R.id.ingredient_measure);
        }
    }
}

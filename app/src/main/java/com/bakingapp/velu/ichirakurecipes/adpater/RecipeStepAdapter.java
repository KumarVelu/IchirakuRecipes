package com.bakingapp.velu.ichirakurecipes.adpater;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bakingapp.velu.ichirakurecipes.R;
import com.bakingapp.velu.ichirakurecipes.modal.RecipeStep;

import java.util.List;

/**
 * Created by Velu on 16/07/17.
 */

public class RecipeStepAdapter extends RecyclerView.Adapter<RecipeStepAdapter.RecipeStepViewHolder> {

    private List<RecipeStep> mRecipeSteps;
    private LayoutInflater mLayoutInflater;
    private IOnRecipeStepClickListener mClickListener;

    public RecipeStepAdapter(Context context, IOnRecipeStepClickListener clickListener,
                             List<RecipeStep> recipeSteps) {

        mClickListener = clickListener;
        mRecipeSteps = recipeSteps;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public RecipeStepViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rowItem = mLayoutInflater.inflate(R.layout.row_item_recipe_step, parent, false);
        return new RecipeStepViewHolder(rowItem);
    }

    @Override
    public void onBindViewHolder(RecipeStepViewHolder holder, final int position) {
        holder.recipeStepNo.setText(String.valueOf(mRecipeSteps.get(position).getmId()));
        holder.recipeStep.setText(mRecipeSteps.get(position).getmShortDescription());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mClickListener.onRecipeStepClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mRecipeSteps == null ? 0 : mRecipeSteps.size();
    }

    public interface IOnRecipeStepClickListener{
        void onRecipeStepClick(int index);
    }

    public class RecipeStepViewHolder extends RecyclerView.ViewHolder{

        TextView recipeStepNo;
        TextView recipeStep;

        public RecipeStepViewHolder(View itemView) {
            super(itemView);
            recipeStepNo = (TextView) itemView.findViewById(R.id.recipe_step_no);
            recipeStep = (TextView) itemView.findViewById(R.id.recipe_step);
        }
    }
}

package com.bakingapp.velu.ichirakurecipes.adpater;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bakingapp.velu.ichirakurecipes.R;
import com.bakingapp.velu.ichirakurecipes.modal.Recipe;

import java.util.List;

/**
 * Created by Velu on 15/07/17.
 */

public class RecipeListAdapter extends RecyclerView.Adapter<RecipeListAdapter.RecipeListViewHolder> {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<Recipe> mRecipeList;
    private IRecipeClickListener mOnClickHandler;

    public RecipeListAdapter(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(mContext);

        if(context instanceof IRecipeClickListener){
            mOnClickHandler = (IRecipeClickListener) context;
        }else {
            throw new RuntimeException(context.toString()
                + " must implement IRecipeClickListener");
        }
    }

    @Override
    public RecipeListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rowItem = mInflater.inflate(R.layout.row_recipe_item, parent, false);
        return new RecipeListViewHolder(rowItem);
    }

    @Override
    public void onBindViewHolder(RecipeListViewHolder holder, final int position) {
        holder.recipeName.setText(mRecipeList.get(position).getmName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnClickHandler.onRecipeClick(mRecipeList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mRecipeList == null ? 0 : mRecipeList.size();
    }

    public void swapData(List<Recipe> recipeList){
        mRecipeList = recipeList;
        notifyDataSetChanged();
    }

    public interface IRecipeClickListener{
        void onRecipeClick(Recipe recipe);
    }

    public class RecipeListViewHolder extends RecyclerView.ViewHolder {

        ImageView recipeImage;
        TextView recipeName;

        public RecipeListViewHolder(View itemView) {
            super(itemView);
            recipeImage = (ImageView) itemView.findViewById(R.id.recipe_image);
            recipeName = (TextView) itemView.findViewById(R.id.recipe_name);
        }
    }

}

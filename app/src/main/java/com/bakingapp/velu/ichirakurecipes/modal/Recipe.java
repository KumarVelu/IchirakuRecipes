package com.bakingapp.velu.ichirakurecipes.modal;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Velu on 13/07/17.
 */

public class Recipe implements Serializable{

    @SerializedName("id")
    private int mId;
    @SerializedName("name")
    private String mName;
    @SerializedName("ingredients")
    private List<Ingredient> mIngredientList;
    @SerializedName("steps")
    private List<RecipeStep> mRecipeSteps;
    @SerializedName("servings")
    private int mServings;
    @SerializedName("image")
    private String mImage;

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public List<Ingredient> getmIngredientList() {
        return mIngredientList;
    }

    public void setmIngredientList(List<Ingredient> mIngredientList) {
        this.mIngredientList = mIngredientList;
    }

    public List<RecipeStep> getmRecipeSteps() {
        return mRecipeSteps;
    }

    public void setmRecipeSteps(List<RecipeStep> mRecipeSteps) {
        this.mRecipeSteps = mRecipeSteps;
    }

    public int getmServings() {
        return mServings;
    }

    public void setmServings(int mServings) {
        this.mServings = mServings;
    }

    public String getmImage() {
        return mImage;
    }

    public void setmImage(String mImage) {
        this.mImage = mImage;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "mId=" + mId +
                ", mName='" + mName + '\'' +
                ", mIngredientList=" + mIngredientList +
                ", mRecipeSteps=" + mRecipeSteps +
                ", mServings=" + mServings +
                ", mImage='" + mImage + '\'' +
                '}';
    }
}

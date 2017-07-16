package com.bakingapp.velu.ichirakurecipes.modal;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Velu on 14/07/17.
 */

public class Ingredient implements Serializable{

    @SerializedName("ingredient")
    private String mName;
    @SerializedName("quantity")
    private String mQuantity;
    @SerializedName("measure")
    private String mMeasure;

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmQuantity() {
        return mQuantity;
    }

    public void setmQuantity(String mQuantity) {
        this.mQuantity = mQuantity;
    }

    public String getmMeasure() {
        return mMeasure;
    }

    public void setmMeasure(String mMeasure) {
        this.mMeasure = mMeasure;
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "mName='" + mName + '\'' +
                ", mQuantity=" + mQuantity +
                ", mMeasure='" + mMeasure + '\'' +
                '}';
    }

    public static String getName(){
        return Ingredient.class.getSimpleName();
    }
}

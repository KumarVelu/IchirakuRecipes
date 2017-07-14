package com.bakingapp.velu.ichirakurecipes.services;

import com.bakingapp.velu.ichirakurecipes.modal.Recipe;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Velu on 14/07/17.
 */

public interface ApiInterface {

    @GET("/topher/2017/May/59121517_baking/baking.json")
    Call<List<Recipe>> getRecipes();
}

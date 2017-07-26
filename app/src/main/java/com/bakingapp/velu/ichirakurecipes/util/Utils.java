package com.bakingapp.velu.ichirakurecipes.util;

import com.bakingapp.velu.ichirakurecipes.R;

/**
 * Created by Velu on 26/07/17.
 */

public class Utils {

    private static final String NUTELLA_PIE = "nutella pie";
    private static final String BROWNIES = "brownies";
    private static final String YELLOW_CAKE = "yellow cake";
    private static final String CHEESECAKE = "cheesecake";

    public static int getImageByName(String recipeName){
        int resId = -1;
        recipeName = recipeName.toLowerCase();
        switch (recipeName){
            case NUTELLA_PIE:
                resId = R.mipmap.nutella_pie;
                break;

            case BROWNIES:
                resId = R.mipmap.brownies;
                break;

            case YELLOW_CAKE:
                resId = R.mipmap.yellowcake;
                break;

            case CHEESECAKE:
                resId = R.mipmap.cheescake;
                break;

            default:
                resId = R.mipmap.food;
        }

        return resId;
    }

}

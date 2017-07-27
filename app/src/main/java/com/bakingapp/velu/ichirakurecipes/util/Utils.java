package com.bakingapp.velu.ichirakurecipes.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.bakingapp.velu.ichirakurecipes.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

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

    public static int getDefaultRecipeImage(){
        return R.mipmap.food;
    }

    public static Bitmap getBitmapFromUrl(String stringUrl) throws IOException {
        URL url = new URL(stringUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoInput(true);
        connection.connect();
        InputStream input = connection.getInputStream();
        Bitmap myBitmap = BitmapFactory.decodeStream(input);
        return myBitmap;
    }

}

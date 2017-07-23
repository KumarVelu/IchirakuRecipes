package com.bakingapp.velu.ichirakurecipes.widget;

import android.content.Intent;
import android.widget.RemoteViewsService;

/**
 * Created by Velu on 23/07/17.
 */

public class IngredientRemoteViewsService extends RemoteViewsService {

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new IngredientListProvider(this, intent);
    }
}

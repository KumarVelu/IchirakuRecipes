package com.bakingapp.velu.ichirakurecipes.ui;

import android.support.v4.app.Fragment;

/**
 * Created by Velu on 15/07/17.
 */

public class BaseFragment extends Fragment {

    void closeFragment(){
        int count = getFragmentManager().getBackStackEntryCount();

        if(count > 0)
            getFragmentManager().popBackStackImmediate();
    }

}

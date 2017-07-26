package com.bakingapp.velu.ichirakurecipes;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.v7.widget.AppCompatTextView;

import com.bakingapp.velu.ichirakurecipes.ui.RecipeListActivity;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.instanceOf;

/**
 * Created by Velu on 23/07/17.
 */

public class RecipeListActivityBasicTest {

    private static final String RECIPE_NAME = "Nutella Pie";

    @Rule
    public ActivityTestRule<RecipeListActivity> recipeListActivityActivityTestRule =
            new ActivityTestRule<>(RecipeListActivity.class);

    @Test
    public void clickListItem_openDetailActivity(){

        onView(withId(R.id.recyclerView))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        onView(allOf(instanceOf(AppCompatTextView.class), withParent(withId(R.id.toolbar))))
                .check(matches(withText(RECIPE_NAME)));

    }
}

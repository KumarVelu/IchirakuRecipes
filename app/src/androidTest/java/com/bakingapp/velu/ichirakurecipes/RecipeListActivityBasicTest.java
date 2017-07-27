package com.bakingapp.velu.ichirakurecipes;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;

import com.bakingapp.velu.ichirakurecipes.ui.RecipeListActivity;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

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

        onView(withId(R.id.collapsingToolbarLayout)).check(matches(isDisplayed()));

        /*onView(allOf(instanceOf(CollapsingToolbarLayout.class), withParent(withId(R.id.collapsingToolbarLayout))))
                .check(matches(withText(RECIPE_NAME)));*/
    }

    @Test
    public void clickRecipeStep_ShowVideo(){

        // click on recipe list
        onView(withId(R.id.recyclerView))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        // swipe to steps fragment
        onView(allOf(withText("Steps"), isDisplayed())).perform(click());

        //click step item on index 0
        onView(withId(R.id.steps_recyclerView))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));


        //match video desc
        onView(allOf(withId(R.id.short_desc), isCompletelyDisplayed()))
                .check(matches(withText("Recipe Introduction")));
    }
}

package com.bakingapp.velu.ichirakurecipes.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.bakingapp.velu.ichirakurecipes.R;
import com.bakingapp.velu.ichirakurecipes.modal.RecipeStep;

import java.util.List;

public class InstructionActivity extends AppCompatActivity {

    private static final String TAG = InstructionActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instruction);

        if (savedInstanceState == null)
            loadVideoFragment();
    }

    @SuppressWarnings("unchecked")
    private void loadVideoFragment() {
        List<RecipeStep> recipeSteps = (List<RecipeStep>) getIntent()
                .getSerializableExtra(RecipeStepsFragment.RECIPE_STEP_LIST);
        int selectedIndex = getIntent().getIntExtra("selectedIndex", -1);

        RecipeVideoTabFragment videoTabFragment = RecipeVideoTabFragment.newInstance(recipeSteps, selectedIndex);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_instruction, videoTabFragment)
                .commit();
    }
}

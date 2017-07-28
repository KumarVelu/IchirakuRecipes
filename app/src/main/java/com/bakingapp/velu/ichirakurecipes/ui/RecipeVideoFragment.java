package com.bakingapp.velu.ichirakurecipes.ui;


import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bakingapp.velu.ichirakurecipes.R;
import com.bakingapp.velu.ichirakurecipes.modal.RecipeStep;
import com.bakingapp.velu.ichirakurecipes.util.NetworkUtils;
import com.bakingapp.velu.ichirakurecipes.util.Utils;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecipeVideoFragment extends Fragment {

    @BindView(R.id.playerview)
    SimpleExoPlayerView mSimpleExoPlayerView;

    TextView mShortDesc;
    TextView mDescription;

    public static final String TAG = RecipeVideoFragment.class.getSimpleName();
    public static final String RECIPE_STEP = "recipeStep";

    private Context mContext;
    private SimpleExoPlayer mExoPlayer;
    private RecipeStep mRecipeStep;
    private boolean mShouldAutoPlay;
    private boolean mIsPortrait;

    public RecipeVideoFragment() {
        // Required empty public constructor
    }

    public static RecipeVideoFragment newInstance(RecipeStep recipeStep) {
        Bundle args = new Bundle();
        args.putSerializable(RECIPE_STEP, recipeStep);
        RecipeVideoFragment fragment = new RecipeVideoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.i(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
        mContext = getActivity();
        if (getArguments() != null) {
            mRecipeStep = (RecipeStep) getArguments().getSerializable(RECIPE_STEP);
        }
        mShouldAutoPlay = true;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_recipe_video, container, false);
        ButterKnife.bind(this, rootView);

        if(rootView.findViewById(R.id.short_desc) != null){
            mIsPortrait = true;
            mShortDesc = (TextView) rootView.findViewById(R.id.short_desc);
            mDescription = (TextView) rootView.findViewById(R.id.description);
        }
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeUi();
    }

    private void initializeUi() {

        if(mIsPortrait){
            mShortDesc.setText(mRecipeStep.getmShortDescription());
            mDescription.setText(mRecipeStep.getmDescription());
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        initializePlayer(Uri.parse(mRecipeStep.getmVideoUrl()));
    }

    private void initializePlayer(Uri mediaUri) {
        if (mExoPlayer == null) {
            try {
                TrackSelector trackSelector = new DefaultTrackSelector();
                LoadControl loadControl = new DefaultLoadControl();

                mExoPlayer = ExoPlayerFactory.newSimpleInstance(mContext, trackSelector, loadControl);
                if (!mRecipeStep.getmThumbnailUrl().trim().equals("") && NetworkUtils.isInternetOn(mContext)) {
                    mSimpleExoPlayerView.setDefaultArtwork(Utils.getBitmapFromUrl(mRecipeStep.getmThumbnailUrl()));
                }
                mSimpleExoPlayerView.setPlayer(mExoPlayer);

                String userAgent = Util.getUserAgent(mContext, "IchirakuRecipe");

                MediaSource mediaSource = new ExtractorMediaSource(mediaUri, new DefaultDataSourceFactory(
                        mContext, userAgent), new DefaultExtractorsFactory(), null, null);

                mExoPlayer.prepare(mediaSource);
                mExoPlayer.setPlayWhenReady(mShouldAutoPlay);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        releasePlayer();
    }

    private void releasePlayer() {
        if(mExoPlayer != null){
            mShouldAutoPlay = mExoPlayer.getPlayWhenReady();
            mExoPlayer.stop();
            mExoPlayer.release();
            mExoPlayer = null;
        }
    }

}

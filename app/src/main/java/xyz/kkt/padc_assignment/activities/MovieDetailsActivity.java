package xyz.kkt.padc_assignment.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;


import butterknife.BindView;
import butterknife.ButterKnife;
import xyz.kkt.padc_assignment.R;
import xyz.kkt.padc_assignment.adapters.ReviewAdapter;
import xyz.kkt.padc_assignment.adapters.TagAdapter;
import xyz.kkt.padc_assignment.adapters.TrailerMovieAdapter;
import xyz.kkt.padc_assignment.components.SmartRecyclerView;
import xyz.kkt.padc_assignment.components.SmartScrollListener;

/**
 * Created by Lenovo on 12/14/2017.
 */

public class MovieDetailsActivity extends BaseActivity {

//    @BindView(R.id.toolbar)
//    Toolbar toolbar;

    @BindView(R.id.rv_trailer_movies)
    SmartRecyclerView rvTrailerMovies;

    @BindView(R.id.rv_list_tags)
    SmartRecyclerView rvListTags;

    @BindView(R.id.rv_review_list)
    SmartRecyclerView rvReviewList;

    private TrailerMovieAdapter mTrailerMovieAdapter;

    private TagAdapter mTagAdapter;

    private ReviewAdapter mReviewAdapter;

    private SmartScrollListener mSmartScrollListener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_movie_details);

        ButterKnife.bind(this, this);

//        setSupportActionBar(toolbar);
//
//        ActionBar actionBar = getSupportActionBar();
//
//        actionBar.setTitle(" ");

        mTagAdapter = new TagAdapter(getApplicationContext());
        rvListTags.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
        rvListTags.setAdapter(mTagAdapter);

        mTrailerMovieAdapter = new TrailerMovieAdapter(getApplicationContext());

        mReviewAdapter = new ReviewAdapter(getApplicationContext());
        rvReviewList.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        rvReviewList.setAdapter(mReviewAdapter);

        rvTrailerMovies.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
        rvTrailerMovies.setAdapter(mTrailerMovieAdapter);

        mSmartScrollListener = new SmartScrollListener(new SmartScrollListener.OnSmartScrollListener() {
            @Override
            public void onListEndReach() {
                Snackbar.make(rvTrailerMovies, "This is all the data for Now.", Snackbar.LENGTH_LONG).show();
            }
        });

        rvTrailerMovies.addOnScrollListener(mSmartScrollListener);

    }

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, MovieDetailsActivity.class);
        return intent;
    }
}

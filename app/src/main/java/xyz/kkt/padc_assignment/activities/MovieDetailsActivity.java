package xyz.kkt.padc_assignment.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;


import butterknife.BindView;
import butterknife.ButterKnife;
import xyz.kkt.padc_assignment.R;
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

    private TrailerMovieAdapter mTrailerMovieAdapter;

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

        mTrailerMovieAdapter = new TrailerMovieAdapter(getApplicationContext());

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
}

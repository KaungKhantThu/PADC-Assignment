package xyz.kkt.padc_assignment.activities;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import xyz.kkt.padc_assignment.R;
import xyz.kkt.padc_assignment.adapters.ReviewAdapter;
import xyz.kkt.padc_assignment.adapters.TagAdapter;
import xyz.kkt.padc_assignment.adapters.TrailerMovieAdapter;
import xyz.kkt.padc_assignment.components.SmartRecyclerView;
import xyz.kkt.padc_assignment.components.SmartScrollListener;
import xyz.kkt.padc_assignment.data.persistence.MoviesContract;
import xyz.kkt.padc_assignment.data.vo.MovieVO;

/**
 * Created by Lenovo on 12/14/2017.
 */

public class MovieDetailsActivity extends BaseActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    private static final String MOVIE_ID = "MOVIE_ID";

    private static final int MOVIES_DETAILS_LOADER_ID = 1002;

//    @BindView(R.id.toolbar)
//    Toolbar toolbar;

    @BindView(R.id.rv_trailer_movies)
    SmartRecyclerView rvTrailerMovies;

    @BindView(R.id.rv_list_tags)
    SmartRecyclerView rvListTags;

    @BindView(R.id.rv_review_list)
    SmartRecyclerView rvReviewList;

    @BindView(R.id.iv_movie_logo)
    ImageView ivMovieLogo;

    @BindView(R.id.tv_score)
    TextView tvScore;

    @BindView(R.id.tv_movie_quote)
    TextView tvMovieQuote;

    @BindView(R.id.tv_synopis)
    TextView tvSynopis;

    @BindView(R.id.tv_released_date)
    TextView tvReleasedDate;

    @BindView(R.id.tv_movie_name)
    TextView tvMovieName;

    @BindView(R.id.tv_movie_desc)
    TextView tvMovieDesc;

    @BindView(R.id.iv_bg_img)
    ImageView ivBgImg;

    @BindView(R.id.rl_container)
    RelativeLayout rlContainer;

    private TrailerMovieAdapter mTrailerMovieAdapter;

    private TagAdapter mTagAdapter;

    private ReviewAdapter mReviewAdapter;

    private SmartScrollListener mSmartScrollListener;

    private String mMovieId;

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

        mMovieId = getIntent().getStringExtra(MOVIE_ID);

        if (TextUtils.isEmpty(mMovieId)) {
            throw new UnsupportedOperationException("newsId required for NewsDetailsActivity");
        } else {
            getSupportLoaderManager().initLoader(MOVIES_DETAILS_LOADER_ID, null, this);
        }

    }

    public static Intent newIntent(Context context, Integer movieId) {
        Intent intent = new Intent(context, MovieDetailsActivity.class);
        String movieIdConverted = String.valueOf(movieId);
        intent.putExtra(MOVIE_ID, movieIdConverted);
        return intent;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(getApplicationContext(),
                MoviesContract.MovieEntry.CONTENT_URI,
                null,
                MoviesContract.MovieEntry.COLUMN_MOVIE_ID + " = ?", new String[]{mMovieId},
                null
        );
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if (data != null && data.moveToFirst()) {
            MovieVO movie = MovieVO.parseFromCursor(getApplicationContext(), data);
            bindData(movie);
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    private void bindData(MovieVO movie) {

        String score = String.valueOf(movie.getVoteAverage());

        tvMovieName.setText(movie.getTitle());
        tvMovieQuote.setText(movie.getTitle());
        tvReleasedDate.setText(movie.getReleaseDate());
        tvScore.setText(score);
        tvSynopis.setText(movie.getOverview());
        tvMovieDesc.setText(movie.getOverview());


        Glide
                .with(ivMovieLogo.getContext())
                .load("https://image.tmdb.org/t/p/original" + movie.getPosterPath())
                .into(ivMovieLogo);

        Glide
                .with(ivBgImg.getContext())
                .load("https://image.tmdb.org/t/p/original" + movie.getPosterPath())
                .into(ivBgImg);

    }

}

package xyz.kkt.padc_assignment.fragments;


import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.Snackbar;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import xyz.kkt.padc_assignment.MovieApp;
import xyz.kkt.padc_assignment.R;
import xyz.kkt.padc_assignment.activities.MovieDetailsActivity;
import xyz.kkt.padc_assignment.adapters.MovieAdapter;
import xyz.kkt.padc_assignment.components.EmptyViewPod;
import xyz.kkt.padc_assignment.components.SmartRecyclerView;
import xyz.kkt.padc_assignment.components.SmartScrollListener;
import xyz.kkt.padc_assignment.data.model.MovieModel;
import xyz.kkt.padc_assignment.data.persistence.MoviesContract;
import xyz.kkt.padc_assignment.data.vo.MovieVO;
import xyz.kkt.padc_assignment.delegates.MovieItemDelegate;
import xyz.kkt.padc_assignment.events.RestApiEvents;
import xyz.kkt.padc_assignment.mvp.presenters.MovieListPresenter;
import xyz.kkt.padc_assignment.mvp.views.MovieListView;

/**
 * Created by Lenovo on 11/8/2017.
 */

public class MovieFragment extends BaseFragment implements LoaderManager.LoaderCallbacks<Cursor>,
        MovieListView {

    private static final int MOVIES_LIST_LOADER_ID = 1;

    private MovieItemDelegate mMovieItemDelegate;

    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.rv_movies)
    SmartRecyclerView rvMovies;

    private SmartScrollListener mSmartScrollListener;

    private MovieAdapter mMovieAdapter;

    @Inject
    MovieListPresenter mPresenter;

    @BindView(R.id.vp_empty_movie)
    EmptyViewPod vpEmptyMovie;

    public static MovieFragment newInstance() {
        MovieFragment fragment = new MovieFragment();
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // mMovieItemDelegate = (MovieItemDelegate) context;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Context context = getActivity();

        MovieApp movieApp = (MovieApp) context.getApplicationContext();
        movieApp.getSFCAppComponent().inject(this);

        //mPresenter = new MovieListPresenter();
        mPresenter.onCreate(this);

        mMovieAdapter = new MovieAdapter(getContext(), mPresenter);

    }

    @Override
    public void onStart() {
        super.onStart();
        mPresenter.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
        mPresenter.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movies, container, false);
        ButterKnife.bind(this, view);

        rvMovies.setEmptyView(vpEmptyMovie);
        rvMovies.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        rvMovies.setAdapter(mMovieAdapter);

        mSmartScrollListener = new SmartScrollListener(new SmartScrollListener.OnSmartScrollListener() {
            @Override
            public void onListEndReach() {
                Snackbar.make(rvMovies, "Loading new data.", Snackbar.LENGTH_LONG).show();
                swipeRefreshLayout.setRefreshing(true);
                mPresenter.onNewsListEndReach(getContext());
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.onForceRefresh(getContext());
            }
        });

        rvMovies.addOnScrollListener(mSmartScrollListener);

        getLoaderManager().initLoader(MOVIES_LIST_LOADER_ID, null, this);

        return view;
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onNewsDataLoaded(RestApiEvents.MovieDataLoadedEvent event) {
//        mMovieAdapter.appendNewData(event.getLoadMovies());
//        swipeRefreshLayout.setRefreshing(false);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onErrorInvokingAPI(RestApiEvents.ErrorInvokingAPIEvent event) {
        Snackbar.make(rvMovies, event.getErrorMsg(), BaseTransientBottomBar.LENGTH_INDEFINITE);
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(getContext(),
                MoviesContract.MovieEntry.CONTENT_URI,
                null, null,
                null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mPresenter.onDataLoaded(getContext(), data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    @Override
    public void displayMovieList(List<MovieVO> movieList) {
        mMovieAdapter.setNewData(movieList);
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void setTrueSwipeRefreshLayout() {
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void navigateToMovieDetails(MovieVO movieVO) {
        Intent intent = MovieDetailsActivity.newIntent(getContext(), movieVO.getId());
        startActivity(intent);
    }

    @Override
    public Context getContextFromView() {
        Context context = getActivity();
        return context.getApplicationContext();
    }
}

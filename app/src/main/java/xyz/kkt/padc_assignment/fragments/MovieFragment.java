package xyz.kkt.padc_assignment.fragments;


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

import butterknife.BindView;
import butterknife.ButterKnife;
import xyz.kkt.padc_assignment.R;
import xyz.kkt.padc_assignment.adapters.MovieAdapter;
import xyz.kkt.padc_assignment.components.EmptyViewPod;
import xyz.kkt.padc_assignment.components.SmartRecyclerView;
import xyz.kkt.padc_assignment.components.SmartScrollListener;
import xyz.kkt.padc_assignment.data.model.MovieModel;
import xyz.kkt.padc_assignment.data.persistence.MoviesContract;
import xyz.kkt.padc_assignment.data.vo.MovieVO;
import xyz.kkt.padc_assignment.events.RestApiEvents;

/**
 * Created by Lenovo on 11/8/2017.
 */

public class MovieFragment extends BaseFragment implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final int MOVIES_LIST_LOADER_ID = 1;

    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.rv_movies)
    SmartRecyclerView rvMovies;

    private SmartScrollListener mSmartScrollListener;

    private MovieAdapter mMovieAdapter;

    @BindView(R.id.vp_empty_movie)
    EmptyViewPod vpEmptyMovie;

    public static MovieFragment newInstance() {
        MovieFragment fragment = new MovieFragment();
        return fragment;
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
        List<MovieVO> movieList = MovieModel.getInstance().getMovies();
        if (!movieList.isEmpty()) {
            mMovieAdapter.setNewData(movieList);
        } else {
            swipeRefreshLayout.setRefreshing(true);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMovieAdapter = new MovieAdapter(getContext());
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
                //Snackbar.make(rvNews, "This is all the data for Now.", Snackbar.LENGTH_LONG).show();
                MovieModel.getInstance().loadMoreMovies(getContext());
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                MovieModel.getInstance().forceRefreshMovies(getContext());
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
        if (data != null && data.moveToFirst()) {
            List<MovieVO> newsList = new ArrayList<>();
            do {
                MovieVO movies = MovieVO.parseFromCursor(data);
                newsList.add(movies);
            } while (data.moveToNext());
            {
                mMovieAdapter.setNewData(newsList);
                swipeRefreshLayout.setRefreshing(false);
            }

        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}

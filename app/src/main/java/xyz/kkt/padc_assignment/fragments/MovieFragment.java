package xyz.kkt.padc_assignment.fragments;


import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.Snackbar;
import android.os.Bundle;
import android.support.annotation.Nullable;
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

import butterknife.BindView;
import butterknife.ButterKnife;
import xyz.kkt.padc_assignment.R;
import xyz.kkt.padc_assignment.adapters.MovieAdapter;
import xyz.kkt.padc_assignment.events.RestApiEvents;

/**
 * Created by Lenovo on 11/8/2017.
 */

public class MovieFragment extends BaseFragment {
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.rv_movies)
    RecyclerView rvMovies;

    private MovieAdapter mMovieAdapter;

    public static MovieFragment newInstance() {
        MovieFragment fragment = new MovieFragment();
        return fragment;
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
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

        rvMovies.setAdapter(mMovieAdapter);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 1,
                LinearLayoutManager.VERTICAL, false);
        rvMovies.setLayoutManager(gridLayoutManager);

        return view;
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onNewsDataLoaded(RestApiEvents.MovieDataLoadedEvent event) {
        mMovieAdapter.appendNewData(event.getLoadMovies());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onErrorInvokingAPI(RestApiEvents.ErrorInvokingAPIEvent event) {
        Snackbar.make(rvMovies, event.getErrorMsg(), BaseTransientBottomBar.LENGTH_INDEFINITE);
    }

}

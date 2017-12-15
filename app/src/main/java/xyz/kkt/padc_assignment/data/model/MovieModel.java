package xyz.kkt.padc_assignment.data.model;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import xyz.kkt.padc_assignment.data.vo.MovieVO;
import xyz.kkt.padc_assignment.events.RestApiEvents;
import xyz.kkt.padc_assignment.network.MovieDataAgentImpl;
import xyz.kkt.padc_assignment.utils.AppConstants;


/**
 * Created by Lenovo on 12/3/2017.
 */

public class MovieModel {

    private static MovieModel objInstance;

    private List<MovieVO> mMovies;

    private int moviePageIndex = 1;

    private MovieModel() {
        EventBus.getDefault().register(this);
        mMovies = new ArrayList<>();
    }

    public static MovieModel getInstance() {
        if (objInstance == null) {
            objInstance = new MovieModel();
        }
        return objInstance;
    }

    public List<MovieVO> getMovies() {
        return mMovies;
    }

    public void startLoadingMovies() {
        MovieDataAgentImpl.getInstance().loadMovies(AppConstants.ACCESS_TOKEN, moviePageIndex);
    }

    public void loadMoreMovies() {
        MovieDataAgentImpl.getInstance().loadMovies(AppConstants.ACCESS_TOKEN, moviePageIndex);
    }

    public void forceRefreshMovies() {
        mMovies = new ArrayList<>();
        moviePageIndex = 1;
        startLoadingMovies();
    }

    @Subscribe
    public void onMovieDataLoaded(RestApiEvents.MovieDataLoadedEvent event) {
        mMovies.addAll(event.getLoadMovies());
        moviePageIndex = event.getLoadedPageIndex() + 1;
    }


}

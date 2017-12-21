package xyz.kkt.padc_assignment.data.model;


import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import xyz.kkt.padc_assignment.MovieApp;
import xyz.kkt.padc_assignment.data.persistence.MoviesContract;
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

    public void startLoadingMovies(Context context) {
        MovieDataAgentImpl.getInstance().loadMovies(AppConstants.ACCESS_TOKEN, moviePageIndex, context);
    }

    public void loadMoreMovies(Context context) {
        MovieDataAgentImpl.getInstance().loadMovies(AppConstants.ACCESS_TOKEN, moviePageIndex, context);
    }

    public void forceRefreshMovies(Context context) {
        mMovies = new ArrayList<>();
        moviePageIndex = 1;
        startLoadingMovies(context);
    }

    @Subscribe
    public void onMovieDataLoaded(RestApiEvents.MovieDataLoadedEvent event) {
        mMovies.addAll(event.getLoadMovies());
        moviePageIndex = event.getLoadedPageIndex() + 1;

        //TODO Logic to save the data in Persistence Layer

        ContentValues[] moviesCVs = new ContentValues[event.getLoadMovies().size()];
        for (int index = 0; index < moviesCVs.length; index++) {
            moviesCVs[index] = event.getLoadMovies().get(index).parseToContentValues();
        }

        int insertedRows = event.getContext().getContentResolver().bulkInsert(MoviesContract.MovieEntry.CONTENT_URI,
                moviesCVs);
        Log.d(MovieApp.LOG_TAG, "Inserted Rows : " + insertedRows);
    }


}

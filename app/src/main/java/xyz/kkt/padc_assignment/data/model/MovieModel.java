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
import xyz.kkt.padc_assignment.utils.ConfigUtils;


/**
 * Created by Lenovo on 12/3/2017.
 */

public class MovieModel {

    private static MovieModel objInstance;

    private List<MovieVO> mMovies;

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
        MovieDataAgentImpl.getInstance().loadMovies(AppConstants.ACCESS_TOKEN, ConfigUtils.getObjInstance().loadPageIndex(), context);
    }

    public void loadMoreMovies(Context context) {
        int pageIndex = ConfigUtils.getObjInstance().loadPageIndex();
        MovieDataAgentImpl.getInstance().loadMovies(AppConstants.ACCESS_TOKEN, pageIndex, context);
    }

    public void forceRefreshMovies(Context context) {
        mMovies = new ArrayList<>();
        ConfigUtils.getObjInstance().savePageIndex(1);
        startLoadingMovies(context);
    }

    @Subscribe
    public void onMovieDataLoaded(RestApiEvents.MovieDataLoadedEvent event) {
        mMovies.addAll(event.getLoadMovies());
        ConfigUtils.getObjInstance().savePageIndex(event.getLoadedPageIndex() + 1);

        //TODO Logic to save the data in Persistence Layer

        ContentValues[] moviesCVs = new ContentValues[event.getLoadMovies().size()];
        List<ContentValues> genreCVlist = new ArrayList<>();
        List<ContentValues> genreInMovieCVList = new ArrayList<>();

        for (int index = 0; index < moviesCVs.length; index++) {
            MovieVO movie = event.getLoadMovies().get(index);
            moviesCVs[index] = movie.parseToContentValues();

            for (Integer genre : movie.getGenreIdList()) {
                ContentValues genreCV = new ContentValues();
                genreCV.put(MoviesContract.GenreEntry.COLUMN_GENRE_ID, genre);
                genreCVlist.add(genreCV);

                ContentValues genreInMovieCV = new ContentValues();
                genreInMovieCV.put(MoviesContract.MovieGenreEntry.COLUMN_MOVIE_ID, movie.getId());
                genreInMovieCV.put(MoviesContract.MovieGenreEntry.COLUMN_GENRE_ID, genre);
                genreInMovieCVList.add(genreInMovieCV);
            }
        }

        int insertedMovieRows = event.getContext().getContentResolver().bulkInsert(MoviesContract.MovieEntry.CONTENT_URI,
                moviesCVs);
        Log.d(MovieApp.LOG_TAG, "Inserted Movie Rows : " + insertedMovieRows);

        int insertedGenreRows = event.getContext().getContentResolver().bulkInsert(MoviesContract.GenreEntry.CONTENT_URI,
                genreCVlist.toArray(new ContentValues[0]));
        Log.d(MovieApp.LOG_TAG, "Inserted Genre Rows : " + insertedGenreRows);

        int insertedGenreInMovieRows = event.getContext().getContentResolver().bulkInsert(MoviesContract.MovieGenreEntry.CONTENT_URI,
                genreInMovieCVList.toArray(new ContentValues[0]));
        Log.d(MovieApp.LOG_TAG, "Inserted Genre In Movie Rows : " + insertedGenreInMovieRows);

    }


}

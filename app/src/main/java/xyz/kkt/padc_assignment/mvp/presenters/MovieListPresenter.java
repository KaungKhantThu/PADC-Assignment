package xyz.kkt.padc_assignment.mvp.presenters;

import android.content.Context;
import android.database.Cursor;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import xyz.kkt.padc_assignment.MovieApp;
import xyz.kkt.padc_assignment.data.model.MovieModel;
import xyz.kkt.padc_assignment.data.vo.MovieVO;
import xyz.kkt.padc_assignment.delegates.MovieItemDelegate;
import xyz.kkt.padc_assignment.mvp.views.MovieListView;

/**
 * Created by Lenovo on 1/6/2018.
 */

public class MovieListPresenter extends BasePresenter<MovieListView> implements MovieItemDelegate {

    @Inject
    MovieModel mMovieModel;

    public MovieListPresenter() {

    }

    @Override
    public void onCreate(MovieListView view) {
        super.onCreate(view);
        MovieApp movieApp = (MovieApp) mView.getContextFromView();
        movieApp.getSFCAppComponent().inject(this);
    }

    @Override
    public void onStart() {
//        EventBus.getDefault().register(this);
        mView.setTrueSwipeRefreshLayout();
    }

    @Override
    public void onStop() {
        //EventBus.getDefault().unregister(this);
    }

    public void onNewsListEndReach(Context context) {
        mMovieModel.loadMoreMovies(context);
    }

    public void onForceRefresh(Context context) {
        mMovieModel.forceRefreshMovies(context);
    }

    public void onDataLoaded(Context context, Cursor data) {
        if (data != null && data.moveToFirst()) {
            List<MovieVO> movieList = new ArrayList<>();
            do {
                MovieVO movies = MovieVO.parseFromCursor(context, data);
                movieList.add(movies);
            } while (data.moveToNext());
            {
                mView.displayMovieList(movieList);
            }

        }
    }

    @Override
    public void onTapMovieItem(MovieVO movieVO) {
        mView.navigateToMovieDetails(movieVO);
    }
}

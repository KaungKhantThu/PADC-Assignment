package xyz.kkt.padc_assignment.mvp.views;

import android.content.Context;

import java.util.List;

import xyz.kkt.padc_assignment.data.vo.MovieVO;

/**
 * Created by Lenovo on 1/6/2018.
 */

public interface MovieListView {

    void displayMovieList(List<MovieVO> movieList);

    void setTrueSwipeRefreshLayout();

    void navigateToMovieDetails(MovieVO movieVO);

    Context getContextFromView();

}

package xyz.kkt.padc_assignment.adapters;

import android.content.Context;

import android.view.View;
import android.view.ViewGroup;

import xyz.kkt.padc_assignment.R;
import xyz.kkt.padc_assignment.data.vo.MovieVO;
import xyz.kkt.padc_assignment.delegates.MovieItemDelegate;
import xyz.kkt.padc_assignment.viewHolders.MovieViewHolder;

/**
 * Created by Lenovo on 11/8/2017.
 */

public class MovieAdapter extends BaseRecycleAdapter<MovieViewHolder, MovieVO> {

    private MovieItemDelegate mMovieItemDelegate;

    public MovieAdapter(Context context, MovieItemDelegate movieItemDelegate) {
        super(context);
        mMovieItemDelegate = movieItemDelegate;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.view_item_movies, parent, false);
        return new MovieViewHolder(itemView, mMovieItemDelegate);
    }

}

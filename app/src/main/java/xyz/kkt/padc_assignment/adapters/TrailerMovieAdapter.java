package xyz.kkt.padc_assignment.adapters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import xyz.kkt.padc_assignment.R;
import xyz.kkt.padc_assignment.data.vo.MovieVO;
import xyz.kkt.padc_assignment.viewHolders.BaseViewHolder;
import xyz.kkt.padc_assignment.viewHolders.TrailerMovieViewHolder;


/**
 * Created by Lenovo on 12/14/2017.
 */

public class TrailerMovieAdapter extends BaseRecycleAdapter<TrailerMovieViewHolder, MovieVO> {

    public TrailerMovieAdapter(Context context) {
        super(context);
    }

    @Override
    public void onBindViewHolder(TrailerMovieViewHolder holder, int position) {

    }

    @Override
    public TrailerMovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.view_trailer_movie, parent, false);
        return new TrailerMovieViewHolder(itemView);
    }

    @Override
    public int getItemCount() {
        return 10;
    }

}

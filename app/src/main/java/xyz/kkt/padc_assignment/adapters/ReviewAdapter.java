package xyz.kkt.padc_assignment.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import xyz.kkt.padc_assignment.R;
import xyz.kkt.padc_assignment.data.vo.MovieVO;
import xyz.kkt.padc_assignment.viewHolders.ReviewViewHolder;

/**
 * Created by Lenovo on 12/22/2017.
 */

public class ReviewAdapter extends BaseRecycleAdapter<ReviewViewHolder, MovieVO> {
    public ReviewAdapter(Context context) {
        super(context);
    }


    @Override
    public ReviewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.view_item_review, parent, false);
        return new ReviewViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ReviewViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 2;
    }
}

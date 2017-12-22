package xyz.kkt.padc_assignment.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import xyz.kkt.padc_assignment.R;
import xyz.kkt.padc_assignment.data.vo.MovieVO;
import xyz.kkt.padc_assignment.viewHolders.TagViewHolder;

/**
 * Created by Lenovo on 12/22/2017.
 */

public class TagAdapter extends BaseRecycleAdapter<TagViewHolder, MovieVO> {

    public TagAdapter(Context context) {
        super(context);
    }

    @Override
    public TagViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.view_item_tag, parent, false);
        return new TagViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TagViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 3;
    }
}

package xyz.kkt.padc_assignment.viewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Lenovo on 12/7/2017.
 */


public abstract class BaseViewHolder<W> extends RecyclerView.ViewHolder implements View.OnClickListener {
    private W mData;

    public BaseViewHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
    }

    public abstract void setData(W data);
}

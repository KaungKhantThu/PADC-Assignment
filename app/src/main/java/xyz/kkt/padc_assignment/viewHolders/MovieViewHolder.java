package xyz.kkt.padc_assignment.viewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import xyz.kkt.padc_assignment.R;
import xyz.kkt.padc_assignment.adapters.BaseRecycleAdapter;
import xyz.kkt.padc_assignment.data.vo.MovieVO;

/**
 * Created by Lenovo on 11/8/2017.
 */

public class MovieViewHolder extends BaseViewHolder<MovieVO> {

    @BindView(R.id.tv_score)
    TextView tvScore;
    @BindView(R.id.iv_hero_movie)
    ImageView ivHeroMovie;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_category)
    TextView tvCategory;
    @BindView(R.id.tv_popularity)
    TextView tvPopularity;
    @BindView(R.id.btn_movie_overview)
    Button btnMovieOverview;

    public MovieViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setData(MovieVO data) {
//        String genre = "";
//        for (int i = 0; i < data.getGenreIdList().size(); i++) {
//            genre = genre + data.getGenreIdList().get(i) + " ";
//        }
        tvScore.setText(String.valueOf(data.getVoteAverage()));

        //tvCategory.setText(genre);
        tvPopularity.setText(String.valueOf(data.getPopularity()));
        tvName.setText(data.getTitle());

        Glide.with(ivHeroMovie.getContext())
                .load("https://image.tmdb.org/t/p/original"+data.getPosterPath())
                .into(ivHeroMovie);

    }

    @Override
    public void onClick(View view) {

    }
}

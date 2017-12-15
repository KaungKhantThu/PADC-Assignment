package xyz.kkt.padc_assignment.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import xyz.kkt.padc_assignment.R;
import xyz.kkt.padc_assignment.adapters.MovieTabAdapter;
import xyz.kkt.padc_assignment.events.RestApiEvents;
import xyz.kkt.padc_assignment.fragments.MovieFragment;

/**
 * Created by Lenovo on 11/8/2017.
 */

public class MovieActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tl_movies)
    TabLayout tabLayout;
    @BindView(R.id.pager_movies)
    ViewPager viewPager;

    private MovieTabAdapter mMovieTabAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_movie);

        ButterKnife.bind(this, this);

        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_dehaze_24dp);

            actionBar.setTitle(getString(R.string.screen_title_movies));
        }

        mMovieTabAdapter = new MovieTabAdapter(getSupportFragmentManager());

        mMovieTabAdapter.addTap(MovieFragment.newInstance(), getString(R.string.now_on_cinema));
        mMovieTabAdapter.addTap(MovieFragment.newInstance(), getString(R.string.upcoming));
        mMovieTabAdapter.addTap(MovieFragment.newInstance(), getString(R.string.most_popular));


        viewPager.setAdapter(mMovieTabAdapter);
        viewPager.setOffscreenPageLimit(mMovieTabAdapter.getCount());//load all the fragments

        tabLayout.setupWithViewPager(viewPager);//connect viewpager and tablayout
    }



}

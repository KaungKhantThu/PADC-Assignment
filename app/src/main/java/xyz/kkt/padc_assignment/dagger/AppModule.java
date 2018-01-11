package xyz.kkt.padc_assignment.dagger;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import xyz.kkt.padc_assignment.MovieApp;
import xyz.kkt.padc_assignment.data.model.MovieModel;
import xyz.kkt.padc_assignment.mvp.presenters.MovieListPresenter;
import xyz.kkt.padc_assignment.network.MovieDataAgent;
import xyz.kkt.padc_assignment.network.MovieDataAgentImpl;

/**
 * Created by Lenovo on 1/6/2018.
 */
@Module
public class AppModule {

    private MovieApp mApp;

    public AppModule(MovieApp application) {
        mApp = application;
    }

    @Provides
    @Singleton
    public Context provideContext() {
        return mApp.getApplicationContext();
    }

    @Provides
    @Singleton
    public MovieModel provideMovieModel(Context context) {
        return new MovieModel(context);
    }

    @Provides
    @Singleton
    public MovieDataAgent provideMovieDataAgent() {
        return new MovieDataAgentImpl();
    }

    @Provides
    public MovieListPresenter provideMovieListPresenter() {
        return new MovieListPresenter();
    }

}

package xyz.kkt.padc_assignment;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import javax.inject.Inject;

import xyz.kkt.padc_assignment.dagger.AppComponent;
import xyz.kkt.padc_assignment.dagger.AppModule;
import xyz.kkt.padc_assignment.dagger.DaggerAppComponent;
import xyz.kkt.padc_assignment.dagger.UtilsModule;
import xyz.kkt.padc_assignment.data.model.MovieModel;
import xyz.kkt.padc_assignment.utils.ConfigUtils;

/**
 * Created by Lenovo on 11/8/2017.
 */

public class MovieApp extends Application {

    public static final String LOG_TAG = "MoviesApp";

    private AppComponent mAppComponent;

    @Inject
    Context mContext;

    @Inject
    MovieModel mNewsModel;

    @Override
    public void onCreate() {
        super.onCreate();
        // ConfigUtils.initConfigUtils(getApplicationContext());

        mAppComponent = initDagger();//dagger init
        mAppComponent.inject(this); //register consumer

        Log.d(LOG_TAG, "mContext : " + mContext);

        mNewsModel.startLoadingMovies(getApplicationContext());
    }

    private AppComponent initDagger() {
        //return null;
        return DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .utilsModule(new UtilsModule())
                .build();
    }

    public AppComponent getSFCAppComponent() {
        return mAppComponent;
    }


}

package xyz.kkt.padc_assignment;

import android.app.Application;

import xyz.kkt.padc_assignment.data.model.MovieModel;
import xyz.kkt.padc_assignment.utils.ConfigUtils;

/**
 * Created by Lenovo on 11/8/2017.
 */

public class MovieApp extends Application {

    public static final String LOG_TAG = "MoviesApp";

    @Override
    public void onCreate() {
        super.onCreate();
        ConfigUtils.initConfigUtils(getApplicationContext());
        MovieModel.getInstance().startLoadingMovies(getApplicationContext());
    }
}

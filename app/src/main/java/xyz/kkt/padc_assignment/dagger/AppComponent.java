package xyz.kkt.padc_assignment.dagger;

import javax.inject.Singleton;

import dagger.Component;
import xyz.kkt.padc_assignment.MovieApp;
import xyz.kkt.padc_assignment.data.model.MovieModel;
import xyz.kkt.padc_assignment.fragments.MovieFragment;
import xyz.kkt.padc_assignment.mvp.presenters.MovieListPresenter;

/**
 * Created by Lenovo on 1/6/2018.
 */

@Component(modules = {AppModule.class, UtilsModule.class})
@Singleton
public interface AppComponent {

    void inject(MovieApp app);

    void inject(MovieModel movieModel);

    void inject(MovieListPresenter movieListPresenter);

    void inject(MovieFragment movieFragment);

}

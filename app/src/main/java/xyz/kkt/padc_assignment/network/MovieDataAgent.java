package xyz.kkt.padc_assignment.network;

import android.content.Context;

/**
 * Created by Lenovo on 12/3/2017.
 */

public interface MovieDataAgent {

    void loadMovies(String accessToken, int pageNo, Context context);

}

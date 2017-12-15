package xyz.kkt.padc_assignment.network;


import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import xyz.kkt.padc_assignment.events.RestApiEvents;
import xyz.kkt.padc_assignment.network.response.GetMovieResponse;

/**
 * Created by Lenovo on 12/3/2017.
 */

public class MovieDataAgentImpl implements MovieDataAgent {

    private static MovieDataAgentImpl objInstance;

    private MovieAPI theAPI;

    private MovieDataAgentImpl() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://padcmyanmar.com/padc-3/popular-movies/apis/")
                .addConverterFactory(GsonConverterFactory.create(new Gson())) // addding gson converterfactory for retrofit to convert JSON to object
                .client(okHttpClient)
                .build();

        theAPI = retrofit.create(MovieAPI.class);//creating API instance (API instance is interface)

    }


    public static MovieDataAgentImpl getInstance() {
        if (objInstance == null) {
            objInstance = new MovieDataAgentImpl();
        }
        return objInstance;
    }

    @Override
    public void loadMovies(String accessToken, int pageNo) {
        Call<GetMovieResponse> loadMMNewsCall = theAPI.loadMovies(accessToken, pageNo);
        loadMMNewsCall.enqueue(new SFCCallback<GetMovieResponse>() {
            @Override
            public void onResponse(Call<GetMovieResponse> call, Response<GetMovieResponse> response) {
                GetMovieResponse getMovieResponse = response.body();
                if (getMovieResponse != null
                        && getMovieResponse.getMovieVOList().size() > 0) {
                    RestApiEvents.MovieDataLoadedEvent movieDataLoadedEvent = new RestApiEvents.MovieDataLoadedEvent(
                            getMovieResponse.getPageNo(), getMovieResponse.getMovieVOList());
                    EventBus.getDefault().post(movieDataLoadedEvent);
                }
            }
        });
    }
}

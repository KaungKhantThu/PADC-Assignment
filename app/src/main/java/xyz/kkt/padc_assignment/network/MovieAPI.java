package xyz.kkt.padc_assignment.network;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import xyz.kkt.padc_assignment.network.response.GetMovieResponse;

/**
 * Created by Lenovo on 12/3/2017.
 */

public interface MovieAPI {

    @FormUrlEncoded
    @POST("v1/getPopularMovies.php")
    Call<GetMovieResponse> loadMovies(
            @Field("access_token") String accessToken,
            @Field("page") int pageIndex);

}

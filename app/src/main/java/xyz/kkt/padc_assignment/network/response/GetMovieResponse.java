package xyz.kkt.padc_assignment.network.response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import xyz.kkt.padc_assignment.data.vo.MovieVO;

/**
 * Created by Lenovo on 12/3/2017.
 */

public class GetMovieResponse {

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("apiVersion")
    private String apiVersion;

    @SerializedName("page")
    private int pageNo;

    @SerializedName("popular-movies")
    private List<MovieVO> movieVOList;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public int getPageNo() {
        return pageNo;
    }

    public List<MovieVO> getMovieVOList() {
        if (movieVOList == null) {
            movieVOList = new ArrayList<>();
        }
        return movieVOList;
    }
}

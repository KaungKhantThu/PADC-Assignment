package xyz.kkt.padc_assignment.events;

import java.util.List;

import xyz.kkt.padc_assignment.data.vo.MovieVO;


/**
 * Created by Lenovo on 12/3/2017.
 */

public class RestApiEvents {

    public static class EmptyResponseEvent {

    }

    public static class ErrorInvokingAPIEvent {
        private String errorMsg;

        public ErrorInvokingAPIEvent(String errorMsg) {
            this.errorMsg = errorMsg;
        }

        public String getErrorMsg() {
            return errorMsg;
        }
    }

    public static class MovieDataLoadedEvent {
        private int loadedPageIndex;
        private List<MovieVO> loadMovies;

        public MovieDataLoadedEvent(int loadedPageIndex, List<MovieVO> loadMovies) {
            this.loadedPageIndex = loadedPageIndex;
            this.loadMovies = loadMovies;
        }

        public int getLoadedPageIndex() {
            return loadedPageIndex;
        }

        public List<MovieVO> getLoadMovies() {
            return loadMovies;
        }
    }

}

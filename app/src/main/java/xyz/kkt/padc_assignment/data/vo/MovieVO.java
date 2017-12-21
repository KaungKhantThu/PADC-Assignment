package xyz.kkt.padc_assignment.data.vo;

import android.content.ContentValues;
import android.database.Cursor;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import xyz.kkt.padc_assignment.data.persistence.MoviesContract;

/**
 * Created by Lenovo on 12/7/2017.
 */

public class MovieVO {

    @SerializedName("vote_count")
    private int voteCount;

    @SerializedName("id")
    private int id;

    @SerializedName("video")
    private boolean video;

    @SerializedName("vote_average")
    private double voteAverage;

    @SerializedName("title")
    private String title;

    @SerializedName("popularity")
    private double popularity;

    @SerializedName("poster_path")
    private String posterPath;

    @SerializedName("original_language")
    private String originalLanguage;

    @SerializedName("original_title")
    private String originalTitle;

    @SerializedName("genre_ids")
    private List<Integer> genreIdList;

    @SerializedName("backdrop_path")
    private String backdropPath;

    @SerializedName("adult")
    private boolean adult;

    @SerializedName("overview")
    private String overview;

    @SerializedName("release_date")
    private String releaseDate;

    public int getVoteCount() {
        return voteCount;
    }

    public int getId() {
        return id;
    }

    public boolean isVideo() {
        return video;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public String getTitle() {
        return title;
    }

    public double getPopularity() {
        return popularity;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public List<Integer> getGenreIdList() {
        return genreIdList;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public boolean isAdult() {
        return adult;
    }

    public String getOverview() {
        return overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public ContentValues parseToContentValues() {
        ContentValues contentValues = new ContentValues();

        contentValues.put(MoviesContract.MovieEntry.COLUMN_MOVIE_ID, id);
        contentValues.put(MoviesContract.MovieEntry.COLUMN_VOTE_COUNT, voteCount);
        contentValues.put(MoviesContract.MovieEntry.COLUMN_VIDEO, video);
        contentValues.put(MoviesContract.MovieEntry.COLUMN_VOTE_AVERAGE, voteAverage);
        contentValues.put(MoviesContract.MovieEntry.COLUMN_TITLE, title);
        contentValues.put(MoviesContract.MovieEntry.COLUMN_POPULARITY, popularity);
        contentValues.put(MoviesContract.MovieEntry.COLUMN_POSTER_PATH, posterPath);
        contentValues.put(MoviesContract.MovieEntry.COLUMN_ORIGINAL_LANGUAGE, originalLanguage);
        contentValues.put(MoviesContract.MovieEntry.COLUMN_ORIGINAL_TITLE, originalTitle);
        contentValues.put(MoviesContract.MovieEntry.COLUMN_BACK_DROP_PATH, backdropPath);
        contentValues.put(MoviesContract.MovieEntry.COLUMN_ADULT, adult);
        contentValues.put(MoviesContract.MovieEntry.COLUMN_OVERVIEW, overview);
        contentValues.put(MoviesContract.MovieEntry.COLUMN_RELEASE_DATE, releaseDate);
        return contentValues;
    }

    public static MovieVO parseFromCursor(Cursor cursor) {
        MovieVO movies = new MovieVO();
        movies.id = cursor.getInt(cursor.getColumnIndex(MoviesContract.MovieEntry.COLUMN_MOVIE_ID));
        movies.voteCount = cursor.getInt(cursor.getColumnIndex(MoviesContract.MovieEntry.COLUMN_VOTE_COUNT));
        movies.video = Boolean.parseBoolean(cursor.getString(cursor.getColumnIndex(MoviesContract.MovieEntry.COLUMN_VIDEO)));
        movies.voteAverage = Double.parseDouble(cursor.getString(cursor.getColumnIndex(MoviesContract.MovieEntry.COLUMN_VOTE_AVERAGE)));
        movies.title = cursor.getString(cursor.getColumnIndex(MoviesContract.MovieEntry.COLUMN_TITLE));
        movies.popularity = Double.parseDouble((cursor.getString(cursor.getColumnIndex(MoviesContract.MovieEntry.COLUMN_POPULARITY))));
        movies.posterPath = cursor.getString(cursor.getColumnIndex(MoviesContract.MovieEntry.COLUMN_POSTER_PATH));
        movies.originalLanguage = cursor.getString(cursor.getColumnIndex(MoviesContract.MovieEntry.COLUMN_ORIGINAL_LANGUAGE));
        movies.originalTitle = cursor.getString(cursor.getColumnIndex(MoviesContract.MovieEntry.COLUMN_ORIGINAL_TITLE));
        movies.backdropPath = cursor.getString(cursor.getColumnIndex(MoviesContract.MovieEntry.COLUMN_BACK_DROP_PATH));
        movies.adult = Boolean.parseBoolean(cursor.getString(cursor.getColumnIndex(MoviesContract.MovieEntry.COLUMN_ADULT)));
        movies.overview = cursor.getString(cursor.getColumnIndex(MoviesContract.MovieEntry.COLUMN_OVERVIEW));
        movies.releaseDate = cursor.getString(cursor.getColumnIndex(MoviesContract.MovieEntry.COLUMN_RELEASE_DATE));

        return movies;
    }

}

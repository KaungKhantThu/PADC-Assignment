package xyz.kkt.padc_assignment.data.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import xyz.kkt.padc_assignment.data.persistence.MoviesContract.MovieEntry;
import xyz.kkt.padc_assignment.data.persistence.MoviesContract.MovieGenreEntry;

/**
 * Created by aung on 7/9/16.
 */
public class MoviesDBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 7;
    public static final String DATABASE_NAME = "movies.db";


    private static final String SQL_CREATE_MOVIE_TABLE = "CREATE TABLE " + MovieEntry.TABLE_NAME + " (" +
            MovieEntry._ID + " INTEGER PRIMARY KEY, " +
            MovieEntry.COLUMN_VOTE_COUNT + " INTEGER, " +
            MovieEntry.COLUMN_VIDEO + " BOOLEAN, " +
            MovieEntry.COLUMN_VOTE_AVERAGE + " DOUBLE, " +
            MovieEntry.COLUMN_TITLE + " TEXT, " +
            MovieEntry.COLUMN_POPULARITY + " DOUBLE, " +
            MovieEntry.COLUMN_POSTER_PATH + " TEXT, " +
            MovieEntry.COLUMN_ORIGINAL_LANGUAGE + " TEXT, " +
            MovieEntry.COLUMN_BACK_DROP_PATH + " TEXT, " +
            MovieEntry.COLUMN_TITLE + " TEXT, " +
            MovieEntry.COLUMN_ADULT + " BOOLEAN, " +
            MovieEntry.COLUMN_RELEASE_DATE + " TEXT, " +

            " UNIQUE (" + MovieEntry.COLUMN_TITLE + ") ON CONFLICT IGNORE" +
            " );";

    private static final String SQL_CREATE_MOVIE_GENRE_TABLE = "CREATE TABLE " + MovieGenreEntry.TABLE_NAME + " (" +
            MovieGenreEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            MovieGenreEntry.COLUMN_MOVIE_TITLE + " TEXT, " +
            MovieGenreEntry.COLUMN_GENRE + " TEXT, " +

            " UNIQUE (" + MovieGenreEntry.COLUMN_MOVIE_TITLE + ", " +
            MovieGenreEntry.COLUMN_GENRE + ") ON CONFLICT IGNORE" +
            " );";

    /*private static final String SQL_CREATE_LOGIN_USER_TABLE = "CREATE TABLE " + LoginUserEntry.TABLE_NAME + " (" +
            LoginUserEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            LoginUserEntry.COLUMN_NAME + " TEXT NOT NULL, " +
            LoginUserEntry.COLUMN_EMAIL + " TEXT NOT NULL, " +
            LoginUserEntry.COLUMN_ACCESS_TOKEN + " TEXT NOT NULL, " +
            LoginUserEntry.COLUMN_DATE_OF_BIRTH + " TEXT NOT NULL, " +
            LoginUserEntry.COLUMN_COUNTRY + " TEXT NOT NULL, " +
            LoginUserEntry.COLUMN_REGISTERED_DATE + " TEXT NOT NULL, " +
            LoginUserEntry.COLUMN_LAST_USED_DATE + " TEXT, " +
            LoginUserEntry.COLUMN_PROFILE_PICTURE + " TEXT, " +
            LoginUserEntry.COLUMN_COVER_PICTURE + " TEXT, " +
            LoginUserEntry.COLUMN_FACEBOOK_ID + " TEXT, " +

            " UNIQUE (" + LoginUserEntry.COLUMN_EMAIL + ") ON CONFLICT REPLACE" +
            " );";
        */
    public MoviesDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {//is called when the persistence layer is created
        sqLiteDatabase.execSQL(SQL_CREATE_MOVIE_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_MOVIE_GENRE_TABLE);
        //sqLiteDatabase.execSQL(SQL_CREATE_LOGIN_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {//is called when db version is updated
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + MovieGenreEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + MovieEntry.TABLE_NAME);
        //sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + LoginUserEntry.TABLE_NAME);
        //Delete all the data in local device
        onCreate(sqLiteDatabase);
        //Add all the new data from api
    }
}

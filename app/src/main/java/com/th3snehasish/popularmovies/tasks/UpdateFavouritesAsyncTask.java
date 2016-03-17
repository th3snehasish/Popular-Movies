package com.th3snehasish.popularmovies.tasks;

import android.content.ContentValues;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Button;
import android.widget.Toast;

import com.th3snehasish.popularmovies.R;
import com.th3snehasish.popularmovies.db.DbUtils;
import com.th3snehasish.popularmovies.db.MovieContracts;
import com.th3snehasish.popularmovies.models.Movie;

/**
 * @author Snehasish Nayak
 */
public class UpdateFavouritesAsyncTask extends AsyncTask<Void, Void, Void> {
    private Context mContext;
    private Movie movie;
    private Boolean isAlreadyFavouite;
    private Button favButton;


    public UpdateFavouritesAsyncTask(Context mContext, Movie movie, Boolean isAlreadyFavouite, Button favButton) {
        this.mContext = mContext;
        this.movie = movie;
        this.isAlreadyFavouite = isAlreadyFavouite;
        this.favButton = favButton;
    }

    @Override
    protected Void doInBackground(Void... params) {
        if (!isAlreadyFavouite) {
            ContentValues contentValues = DbUtils.toContentValue(movie);
            mContext.getContentResolver().insert(MovieContracts.MOVIES_TABLE.CONTENT_URI, contentValues);

        } else {
            mContext.getContentResolver().delete(
                    MovieContracts.MOVIES_TABLE.CONTENT_URI,
                    MovieContracts.MOVIES_TABLE._ID + " = ?",
                    new String[]{movie.getId()}
            );
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        int toastStrRes;
        if (!isAlreadyFavouite) {
            toastStrRes = R.string.added_fav_msg;
            favButton.setText(mContext.getString(R.string.mark_unfavorite));
        } else {
            toastStrRes = R.string.remove_fav_msg;
            favButton.setText(mContext.getString(R.string.mark_favorite));
        }
        Toast.makeText(mContext, mContext.getString(toastStrRes), Toast.LENGTH_SHORT).show();
    }
}

package com.th3snehasish.popularmovies.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Button;

import com.th3snehasish.popularmovies.R;
import com.th3snehasish.popularmovies.db.DbUtils;
import com.th3snehasish.popularmovies.models.Movie;

/**
 * @author Snehasish Nayak
 */
public class ManageFavouritesAsyncTask extends AsyncTask<Void, Void, Boolean> {
    private Context mContext;
    private Movie movie;
    private Boolean performAction;
    private Button favButton;

    public ManageFavouritesAsyncTask(Context mContext, Movie movie, Boolean performAction, Button favButton) {
        this.mContext = mContext;
        this.movie = movie;
        this.performAction = performAction;
        this.favButton = favButton;
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        return DbUtils.isFavourite(mContext, movie.getId());
    }


    @Override
    protected void onPostExecute(Boolean isFavorited) {
        if (performAction) {
            new UpdateFavouritesAsyncTask(mContext, movie, isFavorited, favButton).execute();
        } else {
            if (isFavorited) {
                favButton.setText(mContext.getString(R.string.mark_unfavorite));
            } else {
                favButton.setText(mContext.getString(R.string.mark_favorite));
            }
        }
    }
}

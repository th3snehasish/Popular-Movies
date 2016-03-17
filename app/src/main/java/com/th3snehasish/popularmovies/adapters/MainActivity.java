package com.th3snehasish.popularmovies.adapters;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.th3snehasish.popularmovies.DetailActivity;
import com.th3snehasish.popularmovies.DetailActivityFragment;
import com.th3snehasish.popularmovies.MainActivityFragment;
import com.th3snehasish.popularmovies.R;
import com.th3snehasish.popularmovies.models.Movie;
import com.th3snehasish.popularmovies.utils.Constants;

public class MainActivity extends AppCompatActivity implements MainActivityFragment.BunldeCallback {

    private static final String DETAIL_FRAGMENT_TAG = "DFTAG";
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (findViewById(R.id.movie_detail_container) != null) {
            mTwoPane = true;
            if (savedInstanceState == null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.movie_detail_container, new DetailActivityFragment(), DETAIL_FRAGMENT_TAG)
                        .commit();
            }
        } else {
            mTwoPane = false;
        }
    }

    @Override
    public void onItemSelected(Movie movie) {
        if (mTwoPane) {
            Bundle arguments = new Bundle();
            arguments.putParcelable(Constants.MOVIE_TAG, movie);

            DetailActivityFragment fragment = new DetailActivityFragment();
            fragment.setArguments(arguments);

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.movie_detail_container, fragment, DETAIL_FRAGMENT_TAG)
                    .commit();
        } else {
            Intent intent = new Intent(this, DetailActivity.class)
                    .putExtra(Constants.MOVIE_TAG, movie);
            startActivity(intent);
        }
    }

}
package com.th3snehasish.popularmovies.services;

import com.th3snehasish.popularmovies.models.ListResponse;
import com.th3snehasish.popularmovies.models.Movie;
import com.th3snehasish.popularmovies.models.Review;
import com.th3snehasish.popularmovies.models.TrailersList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface MovieService {

    @GET("3/discover/movie?")
    Call<ListResponse<Movie>> getMovies(@Query("sort_by") String sortBy);

    @GET("3/movie/{id}/reviews?")
    Call<ListResponse<Review>> getMovieReviews(@Path("id") String id);

    @GET("3/movie/{id}/videos?")
    Call<TrailersList> getMovieTrailers(@Path("id") String id);
}

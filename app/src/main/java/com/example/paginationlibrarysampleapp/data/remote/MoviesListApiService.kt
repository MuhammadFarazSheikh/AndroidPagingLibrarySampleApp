package com.example.paginationlibrarysampleapp.data.remote

import com.example.paginationlibrarysampleapp.domain.models.TopRatedMoviesList
import com.example.paginationlibrarysampleapp.others.ApiEndpoints.TOP_RATED_MOVIES
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesListApiService {

    @GET(TOP_RATED_MOVIES)
    suspend fun getTopRatedMoviesList(
        @Query("api_key") api_key: String,
        @Query("page") page: Int
    ):TopRatedMoviesList

}
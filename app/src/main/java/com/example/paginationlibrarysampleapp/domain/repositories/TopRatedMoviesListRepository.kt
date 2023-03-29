package com.example.paginationlibrarysampleapp.domain.repositories

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.paginationlibrarysampleapp.data.pagingdatasource.PagingDataSource
import com.example.paginationlibrarysampleapp.data.remote.MoviesListApiService
import com.example.paginationlibrarysampleapp.domain.models.TopRatedMovie
import com.example.paginationlibrarysampleapp.others.Constants.MOVIES_LIST_API_KEY
import javax.inject.Inject

class TopRatedMoviesListRepository @Inject constructor(
    val moviesListApiService:MoviesListApiService
): BaseRepository() {
    fun getAllMovies(): LiveData<PagingData<TopRatedMovie>> {

        return Pager(
            config = PagingConfig(
                pageSize = 100,
                enablePlaceholders = false,
                initialLoadSize = 2
            ),
            pagingSourceFactory = {
                PagingDataSource(moviesListApiService)
            }
            , initialKey = 1
        ).liveData
    }
}
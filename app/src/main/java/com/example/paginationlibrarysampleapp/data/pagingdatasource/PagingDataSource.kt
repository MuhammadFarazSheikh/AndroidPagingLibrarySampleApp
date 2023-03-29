package com.example.paginationlibrarysampleapp.data.pagingdatasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.paginationlibrarysampleapp.data.remote.MoviesListApiService
import com.example.paginationlibrarysampleapp.domain.models.TopRatedMovie
import com.example.paginationlibrarysampleapp.others.Constants.MOVIES_LIST_API_KEY

class PagingDataSource(
    val moviesListApiService:MoviesListApiService
): PagingSource<Int, TopRatedMovie>() {
    override fun getRefreshKey(state: PagingState<Int, TopRatedMovie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TopRatedMovie> {
        return try {
            val position = params.key ?: 1
            val response = moviesListApiService.getTopRatedMoviesList(MOVIES_LIST_API_KEY,position)
            LoadResult.Page(data = response!!.results, prevKey = if (position == 1) null else position - 1,
                nextKey = position + 1)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}
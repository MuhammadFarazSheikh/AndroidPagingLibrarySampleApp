package com.example.paginationlibrarysampleapp.presentation.viewmodels

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.paginationlibrarysampleapp.domain.models.TopRatedMovie
import com.example.paginationlibrarysampleapp.domain.repositories.TopRatedMoviesListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TopRatedMoviesListViewModel @Inject constructor(
    val topRatedMoviesListRepository: TopRatedMoviesListRepository
) : ViewModel() {

    fun getMovieList(): LiveData<PagingData<TopRatedMovie>> {
        return topRatedMoviesListRepository.getAllMovies().cachedIn(viewModelScope)
    }
}
package com.example.paginationlibrarysampleapp.domain.models

data class TopRatedMoviesList(
    val page:Int,
    val results:ArrayList<TopRatedMovie>,
    val total_pages:Int,
    val total_results:Int
)
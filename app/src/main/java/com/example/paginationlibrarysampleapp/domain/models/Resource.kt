package com.example.paginationlibrarysampleapp.domain.models

sealed class Resource<out T> {
    data class Success<out T>(val value: T) : Resource<T>()
    data class Failure(val isNetworkError: Boolean, val errorCode: Int?, val errorResponseString: String) : Resource<Nothing>()
    object Loading : Resource<Nothing>()
}
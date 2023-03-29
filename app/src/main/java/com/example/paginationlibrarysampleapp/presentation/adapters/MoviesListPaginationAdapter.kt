package com.example.paginationlibrarysampleapp.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.paginationlibrarysampleapp.databinding.RowTopRatedMovieRecyclerBinding
import com.example.paginationlibrarysampleapp.domain.models.TopRatedMovie
import com.example.paginationlibrarysampleapp.utils.loadImageFromUrl
import dagger.hilt.android.scopes.ActivityScoped

@ActivityScoped
class MoviesListPaginationAdapter:PagingDataAdapter<TopRatedMovie,
        RecyclerView.ViewHolder>(comparator) {

    object comparator: DiffUtil.ItemCallback<TopRatedMovie>() {
        override fun areItemsTheSame(oldItem: TopRatedMovie, newItem: TopRatedMovie): Boolean {
            return oldItem.original_title == newItem.original_title
        }

        override fun areContentsTheSame(oldItem: TopRatedMovie, newItem: TopRatedMovie): Boolean {
            return oldItem == newItem
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as DateViewHolder).rowTopRatedMovieRecyclerBinding.topRatedMovie = getItem(position)
        loadImageFromUrl(
            (holder as DateViewHolder).rowTopRatedMovieRecyclerBinding.imageViewMoviePoster,
            getItem(position)?.backdrop_path!!,
            (holder as DateViewHolder).rowTopRatedMovieRecyclerBinding.root.context
        )
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return DateViewHolder(RowTopRatedMovieRecyclerBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    internal class DateViewHolder(
        val rowTopRatedMovieRecyclerBinding: RowTopRatedMovieRecyclerBinding
        ) : RecyclerView.ViewHolder(rowTopRatedMovieRecyclerBinding.root)
}
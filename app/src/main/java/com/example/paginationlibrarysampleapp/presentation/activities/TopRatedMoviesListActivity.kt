package com.example.paginationlibrarysampleapp.presentation.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.example.paginationlibrarysampleapp.R
import com.example.paginationlibrarysampleapp.databinding.ActivityTopRatedMoviesListBinding
import com.example.paginationlibrarysampleapp.presentation.adapters.MoviesListPaginationAdapter
import com.example.paginationlibrarysampleapp.presentation.viewmodels.TopRatedMoviesListViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.multibindings.ElementsIntoSet
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TopRatedMoviesListActivity : AppCompatActivity() {

    private lateinit var topRatedMoviesListBinding: ActivityTopRatedMoviesListBinding
    val moviesListViewModel:TopRatedMoviesListViewModel by viewModels()
    private lateinit var moviesListPaginationAdapter: MoviesListPaginationAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        topRatedMoviesListBinding = ActivityTopRatedMoviesListBinding.inflate(layoutInflater)
        setContentView(topRatedMoviesListBinding.root)
        moviesListPaginationAdapter = MoviesListPaginationAdapter()
        topRatedMoviesListBinding.moviesListPagingAdapter = moviesListPaginationAdapter

        moviesListViewModel.getMovieList().observe(this)
        {
            lifecycleScope.launch {
                moviesListPaginationAdapter.submitData(it)
            }
        }

        moviesListPaginationAdapter.addLoadStateListener {loadState->
            if (loadState.refresh is LoadState.Loading || loadState.append is LoadState.Loading)
            {
                topRatedMoviesListBinding.circularProgressIndicator.visibility = View.VISIBLE
            }
            else
            {
                topRatedMoviesListBinding.circularProgressIndicator.visibility = View.GONE
            }
        }
    }
}
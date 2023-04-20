package com.ltu.m7019e.v23.themoviedb.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ltu.m7019e.v23.themoviedb.model.Movie
import com.ltu.m7019e.v23.themoviedb.network.TMDBApi
import kotlinx.coroutines.launch

class MovieListViewModel(application: Application) : AndroidViewModel(application) {
    private val _movieList = MutableLiveData<List<Movie>>()
    val movieList: LiveData<List<Movie>>
        get() {
            return _movieList
        }
    private val _navigateToMovieDetail = MutableLiveData<Movie?>()
    val navigateToMovieDetail: MutableLiveData<Movie?>
        get() {
            return _navigateToMovieDetail
        }

    init {
        getPopularMovies()
    }

    fun getPopularMovies(){
        viewModelScope.launch {
            _movieList.value = TMDBApi.movieListRetrofitService.getPopularMovies().results
        }
    }

    fun getTopRatedMovies(){
        viewModelScope.launch {
            _movieList.value = TMDBApi.movieListRetrofitService.getTopRatedMovies().results
        }
    }


    fun onMovieListItemClicked(movie: Movie) {
        _navigateToMovieDetail.value = movie
    }

    fun onMovieDetailNavigated() {
        _navigateToMovieDetail.value = null
    }
}
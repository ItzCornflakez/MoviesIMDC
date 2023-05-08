package com.ltu.m7019e.v23.themoviedb.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ltu.m7019e.v23.themoviedb.database.MovieDatabaseDao
import com.ltu.m7019e.v23.themoviedb.model.Movie
import com.ltu.m7019e.v23.themoviedb.network.DataFetchStatus
import com.ltu.m7019e.v23.themoviedb.network.MovieResponse
import com.ltu.m7019e.v23.themoviedb.network.TMDBApi
import com.ltu.m7019e.v23.themoviedb.repository.MovieRepository
import kotlinx.coroutines.launch

class MovieListViewModel(private val movieDatabaseDao: MovieDatabaseDao, application: Application) :
    AndroidViewModel(application) {

    private val movieRepository = MovieRepository(movieDatabaseDao)

    private val _dataFetchStatus = movieRepository.dataFetchStatus
    val dataFetchStatus: LiveData<DataFetchStatus>
        get() {
            return _dataFetchStatus
        }

    val _movieList = MutableLiveData<List<Movie>>()
    val movieList: LiveData<List<Movie>>
        get() {
            return movieRepository.movieList
        }

    private val _navigateToMovieDetail = MutableLiveData<Movie?>()
    val navigateToMovieDetail: MutableLiveData<Movie?>
        get() {
            return _navigateToMovieDetail
        }

    init {
        refreshPopularMovies()
        movieRepository.setDataFetchStatus(DataFetchStatus.LOADING)
    }


    fun refreshPopularMovies(){
        viewModelScope.launch {
            movieRepository.refreshPopularMovies()
        }
    }

    fun refreshTopRatedMovies(){
        viewModelScope.launch {
            movieRepository.refreshTopRatedMovies()
        }
    }

    fun getFavoriteMovies(){
        viewModelScope.launch{
            movieRepository.getFavoriteMovies()
        }
    }

    fun onMovieListItemClicked(movie: Movie) {
        _navigateToMovieDetail.value = movie
    }

    fun onMovieDetailNavigated() {
        _navigateToMovieDetail.value = null
    }
}
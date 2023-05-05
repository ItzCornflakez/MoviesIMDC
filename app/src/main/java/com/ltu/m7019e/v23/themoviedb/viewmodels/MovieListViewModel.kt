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

    private val _dataFetchStatus = MutableLiveData<DataFetchStatus>()
    val dataFetchStatus: LiveData<DataFetchStatus>
        get() {
            return _dataFetchStatus
        }

    private val _movieList = movieRepository.movies
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
        //refreshDataFromRepository()
        _dataFetchStatus.value = DataFetchStatus.LOADING
    }

    /*
    fun getPopularMovies(){
        viewModelScope.launch {
            try {
                val movieResponse: MovieResponse = TMDBApi.movieListRetrofitService.getPopularMovies()
                _movieList.value = movieResponse.results
                _dataFetchStatus.value = DataFetchStatus.DONE
            } catch (e : java.lang.Exception) {
                _dataFetchStatus.value = DataFetchStatus.ERROR
                _movieList.value = arrayListOf()

            }

        }
    }

    fun getTopRatedMovies(){
        viewModelScope.launch {
            try {
                val movieResponse: MovieResponse = TMDBApi.movieListRetrofitService.getTopRatedMovies()
                _movieList.value = movieResponse.results
                _dataFetchStatus.value = DataFetchStatus.DONE
            } catch (e : java.lang.Exception) {
                _dataFetchStatus.value = DataFetchStatus.ERROR
                _movieList.value = arrayListOf()

            }

        }
    }

     */

    private fun refreshDataFromRepository() {
        viewModelScope.launch {
            try {
                movieRepository.refreshMovies()
                _dataFetchStatus.value = DataFetchStatus.DONE
            } catch (e : java.lang.Exception) {
                _dataFetchStatus.value = DataFetchStatus.ERROR
                //_movieList.value = arrayListOf()
            }
        }
    }


    fun getSavedMovies(){
        viewModelScope.launch {
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
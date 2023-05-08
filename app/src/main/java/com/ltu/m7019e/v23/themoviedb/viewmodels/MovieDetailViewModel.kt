package com.ltu.m7019e.v23.themoviedb.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ltu.m7019e.v23.themoviedb.database.MovieDatabaseDao
import com.ltu.m7019e.v23.themoviedb.model.DatabaseMovieDetail
import com.ltu.m7019e.v23.themoviedb.model.Movie
import com.ltu.m7019e.v23.themoviedb.network.DataFetchStatus
import com.ltu.m7019e.v23.themoviedb.network.MovieDetailsResponse
import com.ltu.m7019e.v23.themoviedb.network.TMDBApi
import kotlinx.coroutines.launch

class MovieDetailViewModel(private val movieDatabaseDao: MovieDatabaseDao, application: Application, movie: Movie) : AndroidViewModel(application) {

    private val _dataFetchStatus = MutableLiveData<DataFetchStatus>()
    val dataFetchStatus: LiveData<DataFetchStatus>
        get() {
            return _dataFetchStatus
        }

    private val _isFavorite = MutableLiveData<Boolean>()
    val isFavorite: LiveData<Boolean>
        get() {
            return _isFavorite
        }

    private val _movieDetail = MutableLiveData<DatabaseMovieDetail>()
    val movieDetail: LiveData<DatabaseMovieDetail>
        get() {
            return _movieDetail
        }

    init{
        isFavorite(movie)
        getMovieDetail(movie.id)
    }

    fun isFavorite(movie: Movie){
        viewModelScope.launch {
            _isFavorite.value = movieDatabaseDao.isFavorite(movie.id)
        }
    }

    fun onSaveMovieButtonClicked(movie: Movie){
        viewModelScope.launch {
            movie.isFavorite = true
            movieDatabaseDao.insert(movie)
            isFavorite(movie)
        }
    }

    fun onRemoveMovieButtonClicked(movie: Movie){
        viewModelScope.launch {
            movie.isFavorite = false
            movieDatabaseDao.delete(movie)
            isFavorite(movie)
        }
    }


   fun getMovieDetail(movieId: Int){
       viewModelScope.launch {
           _movieDetail.value = movieDatabaseDao.getMovieDetail(movieId)
       }
    }






}

package com.ltu.m7019e.v23.themoviedb.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ltu.m7019e.v23.themoviedb.database.MovieDatabaseDao
import com.ltu.m7019e.v23.themoviedb.model.Movie

class MovieDetailViewModelFactory(private val movieDatabaseDao: MovieDatabaseDao, private val application: Application, private val movie: Movie) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieDetailViewModel::class.java)) {
            return MovieDetailViewModel(movieDatabaseDao, application, movie) as T
        }
        throw java.lang.IllegalArgumentException("Unknown ViewModel Class")
    }
}
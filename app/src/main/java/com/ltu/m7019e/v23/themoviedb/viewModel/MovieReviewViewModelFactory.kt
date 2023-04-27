package com.ltu.m7019e.v23.themoviedb.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ltu.m7019e.v23.themoviedb.model.Movie

class MovieReviewViewModelFactory(private val application: Application, private val movie: Movie) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieReviewViewModel::class.java)) {
            return MovieReviewViewModel(application, movie) as T
        }
        throw java.lang.IllegalArgumentException("Unknown ViewModel Class")
    }
}
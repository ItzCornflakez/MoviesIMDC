package com.ltu.m7019e.v23.themoviedb.ViewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MovieListViewModelFactory(private val  application: Application): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MovieListViewModel::class.java)) {
            return MovieListViewModel(application) as T
        }
        throw java.lang.IllegalArgumentException("Unknown ViewModel Class")
    }

}
package com.ltu.m7019e.v23.themoviedb.repository

import androidx.lifecycle.LiveData
import com.ltu.m7019e.v23.themoviedb.database.MovieDatabase
import com.ltu.m7019e.v23.themoviedb.database.MovieDatabaseDao
import com.ltu.m7019e.v23.themoviedb.model.Movie
import com.ltu.m7019e.v23.themoviedb.network.TMDBApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieRepository(private val database: MovieDatabase) {

    suspend fun refreshMovies(){
        withContext(Dispatchers.IO){
            val movies = TMDBApi.movieListRetrofitService.getPopularMovies().results
            movies.forEach { it.isCached = true }
            database.movieDatabaseDao.insertAll(movies)
        }

    }

}
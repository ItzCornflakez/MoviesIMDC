package com.ltu.m7019e.v23.themoviedb.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ltu.m7019e.v23.themoviedb.database.MovieDatabase
import com.ltu.m7019e.v23.themoviedb.database.MovieDatabaseDao
import com.ltu.m7019e.v23.themoviedb.model.Movie
import com.ltu.m7019e.v23.themoviedb.network.DataFetchStatus
import com.ltu.m7019e.v23.themoviedb.network.TMDBApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieRepository(private val movieDatabaseDao: MovieDatabaseDao) {

    var movies: LiveData<List<Movie>> = movieDatabaseDao.getFavoriteMovies()

    suspend fun refreshMovies(){
        withContext(Dispatchers.IO){
            movieDatabaseDao.removeCachedStatusFromFavoriteMovies()
            movieDatabaseDao.deleteCachedMovies()
            val movies = TMDBApi.movieListRetrofitService.getPopularMovies().results
            movies.forEach { movie -> movie
                val isFavorite = movieDatabaseDao.isFavorite(movie.id)
                if(isFavorite){
                    movie.isFavorite = true
                }

                movie.isCached = true
            }
            movieDatabaseDao.insertAll(movies)
        }
    }

    suspend fun getFavoriteMovies(){
        withContext(Dispatchers.IO){
            movies = movieDatabaseDao.getFavoriteMovies()
        }
    }

}
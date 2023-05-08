package com.ltu.m7019e.v23.themoviedb.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ltu.m7019e.v23.themoviedb.database.MovieDatabase
import com.ltu.m7019e.v23.themoviedb.database.MovieDatabaseDao
import com.ltu.m7019e.v23.themoviedb.model.Movie
import com.ltu.m7019e.v23.themoviedb.model.MovieDetail
import com.ltu.m7019e.v23.themoviedb.network.DataFetchStatus
import com.ltu.m7019e.v23.themoviedb.network.MovieDetailsResponse
import com.ltu.m7019e.v23.themoviedb.network.MovieResponse
import com.ltu.m7019e.v23.themoviedb.network.TMDBApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieRepository(private val movieDatabaseDao: MovieDatabaseDao) {

    val _movieList = MutableLiveData<List<Movie>>()
    val movieList: LiveData<List<Movie>>
        get() {
            return _movieList
        }

    private val _dataFetchStatus = MutableLiveData<DataFetchStatus>()
    val dataFetchStatus: LiveData<DataFetchStatus>
        get() {
            return _dataFetchStatus
        }

    suspend fun refreshPopularMovies(){
        withContext(Dispatchers.IO) {
            try {
                val movies = TMDBApi.movieListRetrofitService.getPopularMovies().results
                var movieDetailResponses = mutableListOf<MovieDetail>()
                movies.forEach { movie ->
                    movie
                    val isFavorite = movieDatabaseDao.isFavorite(movie.id)
                    if (isFavorite) {
                        movie.isFavorite = true
                    }
                    movie.category = "Popular"

                    //Get Movie Details
                    val movieDetail = TMDBApi.movieListRetrofitService.getMovieDetails(movie.id)
                    var tempStr = ""
                    movieDetail.genres.forEach{
                        tempStr += it.name + ":"
                    }
                    movieDetailResponses.add(MovieDetail(movieDetail.id,movieDetail.homepage, movieDetail.imdb_id, tempStr, movieDetail.backdropPath))
                }

                movieDatabaseDao.insertAll(movies)
                movieDatabaseDao.deleteMovieDetailsForNonFavoriteMovies()
                movieDatabaseDao.insertAllMovieDetail(movieDetailResponses)
                movieDatabaseDao.unsetCategoryOnFavoriteMovies()
                movieDatabaseDao.deleteNonFavoriteTopRatedMovies()

                _movieList.postValue(movies)
                _dataFetchStatus.postValue(DataFetchStatus.DONE)
            } catch (e: java.lang.Exception) {
                val movies = movieDatabaseDao.getPopularMovies()
                _movieList.postValue(movies)
                if (movies.isNullOrEmpty()) {
                    _dataFetchStatus.postValue(DataFetchStatus.ERROR)
                } else {
                    _dataFetchStatus.postValue(DataFetchStatus.DONE)
                }
            }
        }
    }

    suspend fun refreshTopRatedMovies(){
        withContext(Dispatchers.IO){
            try{
                val movies = TMDBApi.movieListRetrofitService.getTopRatedMovies().results
                var movieDetailResponses = mutableListOf<MovieDetail>()
                movies.forEach{movie -> movie
                    val isFavorite = movieDatabaseDao.isFavorite(movie.id)
                    if(isFavorite){
                        movie.isFavorite = true
                    }
                    movie.category = "TopRated"

                    //Get Movie Details
                    val movieDetail = TMDBApi.movieListRetrofitService.getMovieDetails(movie.id)
                    var tempStr = ""
                    movieDetail.genres.forEach{
                        tempStr += it.name + ":"
                    }
                    movieDetailResponses.add(MovieDetail(movieDetail.id,movieDetail.homepage, movieDetail.imdb_id, tempStr, movieDetail.backdropPath))
                }

                movieDatabaseDao.insertAll(movies)
                movieDatabaseDao.deleteMovieDetailsForNonFavoriteMovies()
                movieDatabaseDao.insertAllMovieDetail(movieDetailResponses)
                movieDatabaseDao.unsetCategoryOnFavoriteMovies()
                movieDatabaseDao.deleteNonFavoritePopularMovies()

                _movieList.postValue(movies)
                _dataFetchStatus.postValue(DataFetchStatus.DONE)
            }catch (e : java.lang.Exception){
                val movies = movieDatabaseDao.getTopRatedMovies()
                _movieList.postValue(movies)
                if(movies.isNullOrEmpty()){
                    _dataFetchStatus.postValue(DataFetchStatus.ERROR)
                }else{
                    _dataFetchStatus.postValue(DataFetchStatus.DONE)
                }
            }
        }

    }

    suspend fun getFavoriteMovies(){
        withContext(Dispatchers.IO){
            movieDatabaseDao.deleteMovieDetailsForNonFavoriteMovies()
            val movies = movieDatabaseDao.getFavoriteMovies()
            _movieList.postValue(movies)
            _dataFetchStatus.postValue(DataFetchStatus.DONE) // Don't let error image show when showing favorite movies
        }

    }

    fun setDataFetchStatus(fetchStatus: DataFetchStatus) {
        _dataFetchStatus.value = fetchStatus
    }

}
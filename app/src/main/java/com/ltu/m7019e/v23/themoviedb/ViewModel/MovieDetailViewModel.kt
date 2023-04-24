package com.ltu.m7019e.v23.themoviedb.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ltu.m7019e.v23.themoviedb.model.Movie
import com.ltu.m7019e.v23.themoviedb.model.MovieDetail
import com.ltu.m7019e.v23.themoviedb.network.TMDBApi
import kotlinx.coroutines.launch

class MovieDetailViewModel(application: Application, movie: Movie) : AndroidViewModel(application) {
    private val _movieDetail = MutableLiveData<MovieDetail>()
    val movieDetail: LiveData<MovieDetail>
        get() {
            return _movieDetail
        }

    init{
        getMovieDetail(movie.id)
    }

   fun getMovieDetail(movieId: Int){
       viewModelScope.launch {
           val movieDetailResponse = TMDBApi.movieListRetrofitService.getMovieDetails(movieId)

           val id = movieDetailResponse.id
           val homePage = movieDetailResponse.homepage
           val imdbId = movieDetailResponse.imdb_id
           val genres = movieDetailResponse.genres
           val backdropPath = movieDetailResponse.backdropPath

           _movieDetail.value = MovieDetail(id, genres, homePage, backdropPath, imdbId)
       }

    }






}

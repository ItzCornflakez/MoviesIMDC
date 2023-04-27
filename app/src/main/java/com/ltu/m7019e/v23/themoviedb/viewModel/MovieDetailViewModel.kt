package com.ltu.m7019e.v23.themoviedb.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ltu.m7019e.v23.themoviedb.model.Movie
import com.ltu.m7019e.v23.themoviedb.network.DataFetchStatus
import com.ltu.m7019e.v23.themoviedb.network.MovieDetailsResponse
import com.ltu.m7019e.v23.themoviedb.network.TMDBApi
import kotlinx.coroutines.launch

class MovieDetailViewModel(application: Application, movie: Movie) : AndroidViewModel(application) {

    private val _dataFetchStatus = MutableLiveData<DataFetchStatus>()
    val dataFetchStatus: LiveData<DataFetchStatus>
        get() {
            return _dataFetchStatus
        }

    private val _movieDetail = MutableLiveData<MovieDetailsResponse?>()
    val movieDetail: LiveData<MovieDetailsResponse?>
        get() {
            return _movieDetail
        }

    init{
        getMovieDetail(movie.id)
    }

   fun getMovieDetail(movieId: Int){
       viewModelScope.launch {
           try {
               val movieDetail: MovieDetailsResponse = TMDBApi.movieListRetrofitService.getMovieDetails(movieId)
               _movieDetail.value = movieDetail
               _dataFetchStatus.value = DataFetchStatus.DONE
           } catch (e : Exception) {
               _dataFetchStatus.value = DataFetchStatus.ERROR
               _movieDetail.value = null

           }

       }

    }






}

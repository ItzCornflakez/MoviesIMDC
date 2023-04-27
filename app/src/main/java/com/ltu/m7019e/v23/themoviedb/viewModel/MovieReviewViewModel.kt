package com.ltu.m7019e.v23.themoviedb.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ltu.m7019e.v23.themoviedb.model.Movie
import com.ltu.m7019e.v23.themoviedb.model.Review
import com.ltu.m7019e.v23.themoviedb.network.DataFetchStatus
import com.ltu.m7019e.v23.themoviedb.network.MovieDetailsResponse
import com.ltu.m7019e.v23.themoviedb.network.MovieReviewResponse
import com.ltu.m7019e.v23.themoviedb.network.TMDBApi
import kotlinx.coroutines.launch

class MovieReviewViewModel(application: Application, movie: Movie) : AndroidViewModel(application) {

    private val _dataFetchStatus = MutableLiveData<DataFetchStatus>()
    val dataFetchStatus: LiveData<DataFetchStatus>
        get() {
            return _dataFetchStatus
        }

    private val _movieReviewList = MutableLiveData<List<Review>>()
    val movieReviewList: LiveData<List<Review>>
        get() {
            return _movieReviewList
        }

    private val _movieReview = MutableLiveData<MovieReviewResponse?>()
    val movieReview: LiveData<MovieReviewResponse?>
        get() {
            return _movieReview
        }

    init{
        getMovieReview(movie.id)
    }

    fun getMovieReview(movieId: Int){
        viewModelScope.launch {
            try {
                val movieReview: MovieReviewResponse = TMDBApi.movieListRetrofitService.getMovieReview(movieId)
                _movieReviewList.value = movieReview.results
                _dataFetchStatus.value = DataFetchStatus.DONE
            } catch (e : Exception) {
                _dataFetchStatus.value = DataFetchStatus.ERROR
                _movieReviewList.value = listOf()

            }

        }

    }






}
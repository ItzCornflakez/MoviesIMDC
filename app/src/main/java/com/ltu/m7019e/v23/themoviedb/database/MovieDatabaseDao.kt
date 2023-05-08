package com.ltu.m7019e.v23.themoviedb.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.ltu.m7019e.v23.themoviedb.model.Movie
import com.ltu.m7019e.v23.themoviedb.model.MovieDetail

@Dao
interface MovieDatabaseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movie: Movie)

    //Cache movies retrived from API-call
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movies: List<Movie>)

    @Delete
    suspend fun delete(movie: Movie)

    @Query("DELETE FROM movies WHERE category = 'Popular' AND is_favorite = FALSE")
    suspend fun deleteNonFavoritePopularMovies()

    @Query("DELETE FROM movies WHERE category = 'TopRated' AND is_favorite = FALSE")
    suspend fun deleteNonFavoriteTopRatedMovies()

    @Query("SELECT * FROM movies WHERE category = 'Popular'")

    suspend fun getPopularMovies(): List<Movie>

    @Query("SELECT * FROM movies WHERE category = 'TopRated'")
    suspend fun getTopRatedMovies(): List<Movie>

    @Query("SELECT * FROM movies WHERE is_favorite = TRUE")
    suspend fun getFavoriteMovies(): List<Movie>
    @Query("SELECT EXISTS(SELECT * FROM movies WHERE id = :id AND is_favorite = TRUE)")
    suspend fun isFavorite(id: Int): Boolean

    @Query("UPDATE movies SET category = '' WHERE is_favorite = TRUE")
    suspend fun unsetCategoryOnFavoriteMovies()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllMovieDetail(movies: List<MovieDetail>)

    @Query("DELETE FROM movieDetail WHERE id IN (SELECT id FROM movies WHERE is_favorite = FALSE)")
    suspend fun deleteMovieDetailsForNonFavoriteMovies()




}
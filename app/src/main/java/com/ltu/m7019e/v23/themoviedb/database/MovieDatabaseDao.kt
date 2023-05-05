package com.ltu.m7019e.v23.themoviedb.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.ltu.m7019e.v23.themoviedb.model.Movie

@Dao
interface MovieDatabaseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movie: Movie)

    @Delete
    suspend fun delete(movie: Movie)

    @Query("SELECT * FROM movies WHERE is_favorite = TRUE ORDER BY id")
    fun getFavoriteMovies(): LiveData<List<Movie>>

    @Query("SELECT EXISTS(SELECT * FROM movies WHERE id = :id AND is_favorite = TRUE)")
    suspend fun isFavorite(id: Int): Boolean

    @Query("SELECT EXISTS(SELECT * FROM movies WHERE id = :id AND is_cached = TRUE)")
    suspend fun isCached(id: Int): Boolean

    //Cache movies retrived from API-call
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movies: List<Movie>)

    @Query("UPDATE movies SET is_cached = FALSE WHERE is_favorite = TRUE")
    suspend fun removeCachedStatusFromFavoriteMovies()

    @Query("SELECT * FROM movies WHERE is_cached = TRUE")
    fun getAllCachedMovies(): LiveData<List<Movie>>

    @Query("DELETE FROM movies WHERE is_cached = TRUE")
    suspend fun deleteCachedMovies()

}
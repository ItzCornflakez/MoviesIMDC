package com.ltu.m7019e.v23.themoviedb.database

import androidx.room.*
import com.ltu.m7019e.v23.themoviedb.model.Movie

@Dao
interface MovieDatabaseDao {

    @Insert
    suspend fun insert(movie: Movie)

    @Delete
    suspend fun delete(movie: Movie)

    @Query("SELECT * FROM movies ORDER BY id")
    suspend fun getAllMovies(): List<Movie>

    @Query("SELECT EXISTS(SELECT * FROM movies WHERE id = :id)")
    suspend fun isFavorite(id: Int): Boolean

    //Cache movies retrived from API-call
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(movies: List<Movie>)

}
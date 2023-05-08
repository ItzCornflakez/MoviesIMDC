package com.ltu.m7019e.v23.themoviedb.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "movieDetail")
data class MovieDetail (

    @PrimaryKey()
    var id: Int = 0,

    @ColumnInfo(name = "homepage")
    var homepage: String = "",


    @ColumnInfo(name = "imdb_id")
    var imdb_id: String = "",

    @ColumnInfo(name = "genres")
    var genres: String = "",

    @ColumnInfo(name = "backdrop_path")
    var backdropPath: String = ""
)
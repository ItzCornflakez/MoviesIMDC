package com.ltu.m7019e.v23.themoviedb.network

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ltu.m7019e.v23.themoviedb.model.Genre
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
class MovieDetailsResponse {

        @Json(name = "id")
        var id: Int = 0


        @Json(name = "homepage")
        var homepage: String = ""

        @Json(name = "imdb_id")
        var imdb_id: String = ""

        @Json(name = "genres")
        var genres: List<Genre> = listOf()

        @Json(name = "backdrop_path")
        var backdropPath: String = ""
    }


package com.ltu.m7019e.v23.themoviedb.model


import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieDetail (
    @Json(name = "id")
    var id: Int,

    @Json(name = "genres")
    var genres: MutableList<String>,

    @Json(name = "homepage")
    var URL_link: String,

    @Json(name = "backdrop_path")
    var backdropPath: String,

    @Json(name = "imdb_id")
    var imdb_id: String
    ) : Parcelable
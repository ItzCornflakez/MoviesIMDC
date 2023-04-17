package com.ltu.m7019e.v23.themoviedb.model


import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieDetail (
    var id: Int,
    var genres: MutableList<String>,
    var URL_link: String,
    var imdb_id: String
    ) : Parcelable
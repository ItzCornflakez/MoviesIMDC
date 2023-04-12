package com.ltu.m7019e.v23.themoviedb.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
        var title: String,
        var posterPath: String,
        var backdropPath: String,
        var releaseDate: String,
        var overview: String,
        var genres: MutableList<String>,
        var URL_link: String
) : Parcelable
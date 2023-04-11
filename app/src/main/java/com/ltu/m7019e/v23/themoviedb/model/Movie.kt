package com.ltu.m7019e.v23.themoviedb.model

data class Movie(
        var title: String,
        var posterPath: String,
        var releaseDate: String,
        var overview: String,
        var genres: MutableList<String>,
        var URL_link: String

)
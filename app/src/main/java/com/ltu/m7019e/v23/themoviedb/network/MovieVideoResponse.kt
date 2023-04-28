package com.ltu.m7019e.v23.themoviedb.network


import com.ltu.m7019e.v23.themoviedb.model.Video
import com.squareup.moshi.Json

class MovieVideoResponse {
    @Json(name = "id")
    var id: Int = 0

    @Json(name = "results")
    var results: List<Video> = listOf()

}
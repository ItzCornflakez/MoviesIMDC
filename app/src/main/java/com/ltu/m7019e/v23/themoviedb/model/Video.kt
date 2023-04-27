package com.ltu.m7019e.v23.themoviedb.model


import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class Video (

    @Json(name = "id")
    var id: String,

    @Json(name = "site")
    var site: String,

    @Json(name = "key")
    var key: String

) : Parcelable
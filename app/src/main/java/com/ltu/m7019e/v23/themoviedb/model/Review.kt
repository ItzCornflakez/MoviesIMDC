package com.ltu.m7019e.v23.themoviedb.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize


@Parcelize
data class Review(
    @Json(name = "id")
    var id: String,

    @Json(name = "author")
    var author: String,

    @Json(name = "content")
    var content: String,

    @Json(name = "created_at")
    var createdAt: String,

    @Json(name = "url")
    var url: String

) : Parcelable
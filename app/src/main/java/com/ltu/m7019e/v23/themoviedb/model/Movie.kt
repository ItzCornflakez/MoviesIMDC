package com.ltu.m7019e.v23.themoviedb.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "movies")
data class Movie(
        @Json(name = "id")
        @PrimaryKey()
        var id: Int,

        @ColumnInfo(name = "title")
        @Json(name = "title")
        var title: String,

        @Json(name = "poster_path")
        @ColumnInfo(name = "posterPath")
        var posterPath: String,

        @Json(name = "release_date")
        @ColumnInfo(name = "releaseDate")
        var releaseDate: String,

        @Json(name = "overview")
        @ColumnInfo(name = "overview")
        var overview: String,

        @Json(name = "backdrop_path")
        @ColumnInfo(name = "backdropPath")
        var backdropPath: String = ""

) : Parcelable
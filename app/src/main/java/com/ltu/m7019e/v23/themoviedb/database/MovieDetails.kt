package com.ltu.m7019e.v23.themoviedb.database

import com.ltu.m7019e.v23.themoviedb.model.Movie
import com.ltu.m7019e.v23.themoviedb.model.MovieDetail

class MovieDetails {

    val list = mutableListOf<MovieDetail>()

    init {
        list.add(
            MovieDetail(
                527774,
                listOf("Animation", "Family", "Fantasy", "Action", "Adventure") as MutableList<String>,
                "https://movies.disney.com/raya-and-the-last-dragon",
                "tt5109280"


            )
        )
        list.add(
            MovieDetail(
                793723,
                listOf("Thriller", "Action", "Drama") as MutableList<String>,
                "https://www.netflix.com/title/81218770",
                "tt11734264"
            )
        )
        list.add(
            MovieDetail(
                791373,
                listOf("Action", "Adventure", "Fantasy", "Science Fiction") as MutableList<String>,
                "https://www.hbomax.com/zacksnydersjusticeleague",
                "tt12361974"
            )
        )
        list.add(
            MovieDetail(
                587807,
                listOf("Comedy", "Family", "Animation") as MutableList<String>,
                "https://www.tomandjerrymovie.com",
                "tt1361336"
            )
        )
        list.add(
            MovieDetail(
                587996,
                listOf("Action", "Crime", "Thriller") as MutableList<String>,
                "https://www.netflix.com/title/81038588",
                "tt9845564"
            )
        )
        list.add(
            MovieDetail(
                527774,
                listOf("Animation", "Family", "Fantasy", "Action", "Adventure") as MutableList<String>,
                "https://movies.disney.com/raya-and-the-last-dragon",
                "tt5109280"

            )
        )
        list.add(
            MovieDetail(
                793723,
                listOf("Thriller", "Action", "Drama") as MutableList<String>,
                "https://www.netflix.com/title/81218770",
                "tt11734264"
            )
        )
        list.add(
            MovieDetail(
                791373,
                listOf("Action", "Adventure", "Fantasy", "Science Fiction") as MutableList<String>,
                "https://www.hbomax.com/zacksnydersjusticeleague",
                "tt12361974"
            )
        )
        list.add(
            MovieDetail(
                587807,
                listOf("Comedy", "Family", "Animation") as MutableList<String>,
                "https://www.tomandjerrymovie.com",
                "tt1361336"
            )
        )
        list.add(
            MovieDetail(
                587996,
                listOf("Action", "Crime", "Thriller") as MutableList<String>,
                "https://www.netflix.com/title/81038588",
                "tt9845564"
            )
        )
    }

}

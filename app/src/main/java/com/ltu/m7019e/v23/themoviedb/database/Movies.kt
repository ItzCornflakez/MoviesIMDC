package com.ltu.m7019e.v23.themoviedb.database

import com.ltu.m7019e.v23.themoviedb.model.Movie

class Movies {
    val list = mutableListOf<Movie>()

    init {
        list.add(
            Movie(
                    "Raya and the Last Dragon",
                    "/lPsD10PP4rgUGiGR4CCXA6iY0QQ.jpg",
                   "/rcUcYzGGicDvhDs58uM44tJKB9F.jpg",
                    "2021-03-03",
                    "Long ago, in the fantasy world of Kumandra, humans and dragons lived together in harmony. But when an evil force threatened the land, the dragons sacrificed themselves to save humanity. Now, 500 years later, that same evil has returned and it’s up to a lone warrior, Raya, to track down the legendary last dragon to restore the fractured land and its divided people.",
                            listOf("Animation", "Family", "Fantasy", "Action", "Adventure") as MutableList<String>,
                    "https://movies.disney.com/raya-and-the-last-dragon"

            )
        )
        list.add(
            Movie(
                    "Sentinelle",
                    "/fFRq98cW9lTo6di2o4lK1qUAWaN.jpg",
                "/eTgQlyIQH0nA5BsmYpvCzSPAorg.jpg",
                    "2021-03-05",
                    "Transferred home after a traumatizing combat mission, a highly trained French soldier uses her lethal skills to hunt down the man who hurt her sister.",
                        listOf("Thriller", "Action", "Drama") as MutableList<String>,
                    "https://www.netflix.com/title/81218770"
            )
        )
        list.add(
            Movie(
                    "Zack Snyder's Justice League",
                    "/tnAuB8q5vv7Ax9UAEje5Xi4BXik.jpg",
                "/pcDc2WJAYGJTTvRSEIpRZwM3Ola.jpg",
                    "2021-03-18",
                    "Determined to ensure Superman's ultimate sacrifice was not in vain, Bruce Wayne aligns forces with Diana Prince with plans to recruit a team of metahumans to protect the world from an approaching threat of catastrophic proportions.",
                        listOf("Action", "Adventure", "Fantasy", "Science Fiction") as MutableList<String>,
                    "https://www.hbomax.com/zacksnydersjusticeleague"
            )
        )
        list.add(
            Movie(
                    "Tom & Jerry",
                    "/6KErczPBROQty7QoIsaa6wJYXZi.jpg",
                "/9ns9463dwOeo1CK1JU2wirL5Yi1.jpg",
                    "2021-02-11",
                    "Tom the cat and Jerry the mouse get kicked out of their home and relocate to a fancy New York hotel, where a scrappy employee named Kayla will lose her job if she can’t evict Jerry before a high-class wedding at the hotel. Her solution? Hiring Tom to get rid of the pesky mouse.",
                        listOf("Comedy", "Family", "Animation") as MutableList<String>,
                    "https://www.tomandjerrymovie.com"
            )
        )
        list.add(
            Movie(
                    "Below Zero",
                    "/dWSnsAGTfc8U27bWsy2RfwZs0Bs.jpg",
                "/6TPZSJ06OEXeelx1U1VIAt0j9Ry.jpg",
                    "2021-01-29",
                    "When a prisoner transfer van is attacked, the cop in charge must fight those inside and outside while dealing with a silent foe: the icy temperatures.",
                        listOf("Action", "Crime", "Thriller") as MutableList<String>,
                    "https://www.netflix.com/title/81038588"
            )
        )
        list.add(
            Movie(
                "Raya and the Last Dragon",
                "/lPsD10PP4rgUGiGR4CCXA6iY0QQ.jpg",
                "/rcUcYzGGicDvhDs58uM44tJKB9F.jpg",
                "2021-03-03",
                "Long ago, in the fantasy world of Kumandra, humans and dragons lived together in harmony. But when an evil force threatened the land, the dragons sacrificed themselves to save humanity. Now, 500 years later, that same evil has returned and it’s up to a lone warrior, Raya, to track down the legendary last dragon to restore the fractured land and its divided people.",
                listOf("Animation", "Family", "Fantasy", "Action", "Adventure") as MutableList<String>,
                "https://movies.disney.com/raya-and-the-last-dragon"

            )
        )
        list.add(
            Movie(
                "Sentinelle",
                "/fFRq98cW9lTo6di2o4lK1qUAWaN.jpg",
                "/eTgQlyIQH0nA5BsmYpvCzSPAorg.jpg",
                "2021-03-05",
                "Transferred home after a traumatizing combat mission, a highly trained French soldier uses her lethal skills to hunt down the man who hurt her sister.",
                listOf("Thriller", "Action", "Drama") as MutableList<String>,
                "https://www.netflix.com/title/81218770"
            )
        )
        list.add(
            Movie(
                "Zack Snyder's Justice League",
                "/tnAuB8q5vv7Ax9UAEje5Xi4BXik.jpg",
                "/pcDc2WJAYGJTTvRSEIpRZwM3Ola.jpg",
                "2021-03-18",
                "Determined to ensure Superman's ultimate sacrifice was not in vain, Bruce Wayne aligns forces with Diana Prince with plans to recruit a team of metahumans to protect the world from an approaching threat of catastrophic proportions.",
                listOf("Action", "Adventure", "Fantasy", "Science Fiction") as MutableList<String>,
                "https://www.hbomax.com/zacksnydersjusticeleague"
            )
        )
        list.add(
            Movie(
                "Tom & Jerry",
                "/6KErczPBROQty7QoIsaa6wJYXZi.jpg",
                "/9ns9463dwOeo1CK1JU2wirL5Yi1.jpg",
                "2021-02-11",
                "Tom the cat and Jerry the mouse get kicked out of their home and relocate to a fancy New York hotel, where a scrappy employee named Kayla will lose her job if she can’t evict Jerry before a high-class wedding at the hotel. Her solution? Hiring Tom to get rid of the pesky mouse.",
                listOf("Comedy", "Family", "Animation") as MutableList<String>,
                "https://www.tomandjerrymovie.com"
            )
        )
        list.add(
            Movie(
                "Below Zero",
                "/dWSnsAGTfc8U27bWsy2RfwZs0Bs.jpg",
                "/6TPZSJ06OEXeelx1U1VIAt0j9Ry.jpg",
                "2021-01-29",
                "When a prisoner transfer van is attacked, the cop in charge must fight those inside and outside while dealing with a silent foe: the icy temperatures.",
                listOf("Action", "Crime", "Thriller") as MutableList<String>,
                "https://www.netflix.com/title/81038588"
            )
        )
    }
}
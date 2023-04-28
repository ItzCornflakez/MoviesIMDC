package com.ltu.m7019e.v23.themoviedb.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ltu.m7019e.v23.themoviedb.R
import com.ltu.m7019e.v23.themoviedb.model.Video
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

class MovieVideoAdapter(private val genres: List<Video>): RecyclerView.Adapter<MovieVideoAdapter.ViewHolder>(){

    class ViewHolder(val view: View): RecyclerView.ViewHolder(view){
        val youtubePlayerView: YouTubePlayerView = view.findViewById<YouTubePlayerView>(R.id.youtube_player_view)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val layout = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.movie_video_item, parent, false)

        return ViewHolder(layout)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = genres[position]
        holder.youtubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                // loading the selected video into the YouTube Player
                youTubePlayer.cueVideo(item.key, 0F)
            }

            override fun onStateChange(youTubePlayer: YouTubePlayer, state: PlayerConstants.PlayerState) {
                // this method is called if video has ended,
                super.onStateChange(youTubePlayer, state)
            }
        })
    }

    override fun getItemCount(): Int {
        return genres.size
    }

}
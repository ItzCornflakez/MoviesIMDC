package com.ltu.m7019e.v23.themoviedb.utils

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ltu.m7019e.v23.themoviedb.R

class GenreAdapter(private val genres: MutableList<String>): RecyclerView.Adapter<GenreAdapter.GenreViewHolder>(){

    class GenreViewHolder(val view: View): RecyclerView.ViewHolder(view){
        val text = view.findViewById<TextView>(R.id.genre_item)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GenreViewHolder {
        val layout = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.genre_list_item, parent, false)


        return GenreViewHolder(layout)
    }

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        val item = genres.get(position)
        if(position == genres.size - 1){
            holder.text.text = item.toString()
        }else{
            holder.text.text = item.toString() + ", "
        }
    }

    override fun getItemCount(): Int {
        return genres.size
    }

}
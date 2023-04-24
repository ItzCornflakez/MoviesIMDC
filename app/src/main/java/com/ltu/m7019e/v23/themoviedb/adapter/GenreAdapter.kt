package com.ltu.m7019e.v23.themoviedb.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ltu.m7019e.v23.themoviedb.R
import com.ltu.m7019e.v23.themoviedb.model.Genre

class GenreAdapter(private val genres: List<Genre>): RecyclerView.Adapter<GenreAdapter.GenreViewHolder>(){

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
        val item = genres.get(position).name
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
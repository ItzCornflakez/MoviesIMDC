package com.ltu.m7019e.v23.themoviedb.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ltu.m7019e.v23.themoviedb.databinding.MovieReviewItemBinding
import com.ltu.m7019e.v23.themoviedb.model.Review
import com.ltu.m7019e.v23.themoviedb.network.MovieReviewResponse

class MovieReviewAdapter(private val reviewClickListener: ReviewClickListener) :  ListAdapter<Review, MovieReviewAdapter.ViewHolder>(MovieReviewDiffCallback()){
    class ViewHolder(private var binding: MovieReviewItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(review: Review, reviewClickListener: ReviewClickListener) {
            binding.review = review
            binding.reviewClickListener = reviewClickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup) : ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = MovieReviewItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), reviewClickListener)
    }
}

class MovieReviewDiffCallback : DiffUtil.ItemCallback<Review>() {
    override fun areItemsTheSame(oldItem: Review, newItem: Review): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Review, newItem: Review): Boolean {
        return oldItem == newItem
    }
}

class ReviewClickListener(val clickListener: (review: Review) -> Unit){
    fun onClick(review: Review) = clickListener(review)
}
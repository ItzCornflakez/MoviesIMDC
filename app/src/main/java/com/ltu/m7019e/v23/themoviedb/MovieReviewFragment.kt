package com.ltu.m7019e.v23.themoviedb

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ltu.m7019e.v23.themoviedb.adapter.*
import com.ltu.m7019e.v23.themoviedb.databinding.FragmentMovieReviewBinding
import com.ltu.m7019e.v23.themoviedb.model.Movie
import com.ltu.m7019e.v23.themoviedb.utils.Constants
import com.ltu.m7019e.v23.themoviedb.viewModel.MovieDetailViewModel
import com.ltu.m7019e.v23.themoviedb.viewModel.MovieDetailViewModelFactory
import com.ltu.m7019e.v23.themoviedb.viewModel.MovieReviewViewModel
import com.ltu.m7019e.v23.themoviedb.viewModel.MovieReviewViewModelFactory


/**
 * A simple [Fragment] subclass.
**/
class MovieReviewFragment : Fragment() {

    private var _binding: FragmentMovieReviewBinding? = null;
    private val binding get() = _binding!!

    private lateinit var viewModel: MovieReviewViewModel
    private lateinit var viewModelFactory: MovieReviewViewModelFactory
    private lateinit var movie: Movie

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieReviewBinding.inflate(inflater)

        movie = MovieReviewFragmentArgs.fromBundle(requireArguments()).movie

        val application = requireNotNull(this.activity).application

        viewModelFactory = MovieReviewViewModelFactory(application, movie)
        viewModel = ViewModelProvider(this, viewModelFactory)[MovieReviewViewModel::class.java]

        val movieReviewAdapter = MovieReviewAdapter(
            ReviewClickListener { review -> viewModel.onReviewClicked(review) }
        )

        binding.reviewsRecyclerView.adapter = movieReviewAdapter

        viewModel.movieReviewList.observe(
            viewLifecycleOwner
        ) { movieReviewList ->
            movieReviewList?.let {
                movieReviewAdapter.submitList(movieReviewList)

                //Set the empty_review_view to be visible if no reviews exists for the movie
                if(movieReviewList.isEmpty()){
                    val noReviewText = view?.findViewById<TextView>(R.id.empty_review_view)
                    noReviewText?.visibility = View.VISIBLE
                }
            }
        }

        viewModel.movieVideoList.observe(
            viewLifecycleOwner
        ) { movieVideoList ->
            movieVideoList?.let {
                val recyclerView = binding.videosRecyclerView
                recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                recyclerView.adapter = MovieVideoAdapter(movieVideoList)

                //Set the empty_view_view to be visible if no videos exists for the movie
                if (movieVideoList.isEmpty()){
                    val noVideosText = view?.findViewById<TextView>(R.id.empty_video_view)
                    noVideosText?.visibility = View.VISIBLE
                }
            }
        }

        viewModel.openReviewUrl.observe(viewLifecycleOwner){ review ->
            review?.let {
                val queryUrl: Uri = Uri.parse(review.url)
                val intent = Intent(Intent.ACTION_VIEW, queryUrl)
                context?.startActivity(intent)

                viewModel.onReviewOpened()
            }
        }

        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.reviewsRecyclerView.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL, false)

        binding.toMovieDetailBtn.setOnClickListener(){
            val action = MovieReviewFragmentDirections.actionMovieReviewFragmentToMovieDetailsFragment(movie)
            findNavController().navigate(action)
        }
    }
}
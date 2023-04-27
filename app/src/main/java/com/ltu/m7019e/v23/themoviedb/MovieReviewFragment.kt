package com.ltu.m7019e.v23.themoviedb

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ltu.m7019e.v23.themoviedb.adapter.MovieListAdapter
import com.ltu.m7019e.v23.themoviedb.adapter.MovieListClickListener
import com.ltu.m7019e.v23.themoviedb.adapter.MovieReviewAdapter
import com.ltu.m7019e.v23.themoviedb.adapter.MovieVideoAdapter
import com.ltu.m7019e.v23.themoviedb.databinding.FragmentMovieReviewBinding
import com.ltu.m7019e.v23.themoviedb.model.Movie
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

        val movieReviewAdapter = MovieReviewAdapter()

        binding.reviewsRecyclerView.adapter = movieReviewAdapter

        viewModel.movieReviewList.observe(
            viewLifecycleOwner
        ) { movieReviewList ->
            movieReviewList?.let {
                movieReviewAdapter.submitList(movieReviewList)
            }
        }

        val movieVideoAdapter = MovieVideoAdapter()

        binding.videosRecyclerView.adapter = movieVideoAdapter

        viewModel.movieVideoList.observe(
            viewLifecycleOwner
        ) { movieVideoList ->
            movieVideoList?.let {
                movieVideoAdapter.submitList(movieVideoList)
            }
        }

        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.reviewsRecyclerView.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL, false)
        binding.videosRecyclerView.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL, false)

        binding.toMovieDetailBtn.setOnClickListener(){
            val action = MovieReviewFragmentDirections.actionMovieReviewFragmentToMovieDetailsFragment(movie)
            findNavController().navigate(action)
        }
    }
}
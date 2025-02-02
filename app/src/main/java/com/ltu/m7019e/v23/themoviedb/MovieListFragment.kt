package com.ltu.m7019e.v23.themoviedb

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.fragment.findNavController
import com.ltu.m7019e.v23.themoviedb.ViewModel.MovieListViewModel
import com.ltu.m7019e.v23.themoviedb.ViewModel.MovieListViewModelFactory
import com.ltu.m7019e.v23.themoviedb.database.MovieDetails
import com.ltu.m7019e.v23.themoviedb.database.Movies
import com.ltu.m7019e.v23.themoviedb.databinding.FragmentMovieListBinding
import com.ltu.m7019e.v23.themoviedb.databinding.MovieListItemBinding
import com.ltu.m7019e.v23.themoviedb.model.Movie
import com.ltu.m7019e.v23.themoviedb.model.MovieDetail

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class MovieListFragment : Fragment() {

    private lateinit var viewModel: MovieListViewModel
    private lateinit var viewModelFactory: MovieListViewModelFactory

    private var _binding: FragmentMovieListBinding? = null;
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMovieListBinding.inflate(inflater)

        val movieDetailList = MovieDetails().list

        val application = requireNotNull(this.activity).application

        viewModelFactory = MovieListViewModelFactory(application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MovieListViewModel::class.java)

        viewModel.movieList.observe(
            viewLifecycleOwner
        ) { movieList ->
            movieList.forEach { movie ->
                val movieListItemBinding: MovieListItemBinding =
                    DataBindingUtil.inflate(inflater, R.layout.movie_list_item, container, false);
                movieListItemBinding.movie = movie
                val movieDetail = movieDetailList.find { it.id == movie.id }!!
                movieListItemBinding.movieCard.setOnClickListener {
                    val action =
                        MovieListFragmentDirections.actionMovieListFragmentToMovieDetailsFragment(movie, movieDetail)
                    findNavController().navigate(action)
                }
                binding.movieListLl.addView(movieListItemBinding.root)
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}
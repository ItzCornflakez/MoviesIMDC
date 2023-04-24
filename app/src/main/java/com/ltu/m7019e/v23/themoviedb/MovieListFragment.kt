package com.ltu.m7019e.v23.themoviedb

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.ltu.m7019e.v23.themoviedb.ViewModel.MovieListViewModel
import com.ltu.m7019e.v23.themoviedb.ViewModel.MovieListViewModelFactory
import com.ltu.m7019e.v23.themoviedb.adapter.MovieListAdapter
import com.ltu.m7019e.v23.themoviedb.adapter.MovieListClickListener
import com.ltu.m7019e.v23.themoviedb.databinding.FragmentMovieListBinding

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

        val application = requireNotNull(this.activity).application

        viewModelFactory = MovieListViewModelFactory(application)
        viewModel = ViewModelProvider(this, viewModelFactory)[MovieListViewModel::class.java]

        val movieListAdapter = MovieListAdapter(
            MovieListClickListener { movie ->
                viewModel.onMovieListItemClicked(movie)
            }
        )

        binding.movieListRv.adapter = movieListAdapter

        viewModel.movieList.observe(
            viewLifecycleOwner
        ) { movieList ->
            movieList?.let {
                movieListAdapter.submitList(movieList)
            }
        }

        viewModel.navigateToMovieDetail.observe(viewLifecycleOwner){movie ->
            movie?.let {
                this.findNavController().navigate(MovieListFragmentDirections.actionMovieListFragmentToMovieDetailsFragment(movie))
                viewModel.onMovieDetailNavigated()
            }

        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.movieListRv.layoutManager = GridLayoutManager(context,3)
    }


}
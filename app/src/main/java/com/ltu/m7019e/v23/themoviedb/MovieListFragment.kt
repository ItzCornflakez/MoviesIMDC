package com.ltu.m7019e.v23.themoviedb

import android.os.Bundle
import android.view.*
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.ltu.m7019e.v23.themoviedb.viewModel.MovieListViewModel
import com.ltu.m7019e.v23.themoviedb.viewModel.MovieListViewModelFactory
import com.ltu.m7019e.v23.themoviedb.adapter.MovieListAdapter
import com.ltu.m7019e.v23.themoviedb.adapter.MovieListClickListener
import com.ltu.m7019e.v23.themoviedb.database.MovieDatabase
import com.ltu.m7019e.v23.themoviedb.database.MovieDatabaseDao
import com.ltu.m7019e.v23.themoviedb.databinding.FragmentMovieListBinding
import com.ltu.m7019e.v23.themoviedb.network.DataFetchStatus

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class MovieListFragment : Fragment() {

    private lateinit var viewModel: MovieListViewModel
    private lateinit var viewModelFactory: MovieListViewModelFactory

    private lateinit var movieDatabaseDao: MovieDatabaseDao

    private var _binding: FragmentMovieListBinding? = null;
    private val binding get() = _binding!!

    private var lastSelectedMenuOptions = R.id.action_load_popular_movies

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        _binding = FragmentMovieListBinding.inflate(inflater)

        val application = requireNotNull(this.activity).application

        movieDatabaseDao = MovieDatabase.getInstance(application).movieDatabaseDao

        viewModelFactory = MovieListViewModelFactory(movieDatabaseDao, application)
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

        viewModel.navigateToMovieDetail.observe(viewLifecycleOwner) { movie ->
            movie?.let {
                this.findNavController().navigate(
                    MovieListFragmentDirections.actionMovieListFragmentToMovieDetailsFragment(movie)
                )
                viewModel.onMovieDetailNavigated()
            }

        }

        viewModel.dataFetchStatus.observe(viewLifecycleOwner) { status ->
            status?.let {
                when (status) {
                    DataFetchStatus.LOADING -> {
                        binding.statusImage.visibility = View.VISIBLE
                        binding.statusImage.setImageResource(R.drawable.loading_animation)
                    }
                    DataFetchStatus.ERROR -> {
                        binding.statusImage.visibility = View.VISIBLE
                        binding.statusImage.setImageResource(R.drawable.ic_connection_error)
                    }
                    DataFetchStatus.DONE -> {
                        binding.statusImage.visibility = View.VISIBLE
                        binding.statusImage.setImageResource(R.drawable.loading_img)

                    }

                }
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.movieListRv.layoutManager = GridLayoutManager(context, 3)

        // The usage of an interface lets you inject your own implementation
        val menuHost: MenuHost = requireActivity()

        // Add menu items without using the Fragment Menu APIs
        // Note how we can tie the MenuProvider to the viewLifecycleOwner
        // and an optional Lifecycle.State (here, RESUMED) to indicate when
        // the menu should be visible
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                // Add menu items here
                menuInflater.inflate(R.menu.menu_main, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                // Handle the menu selection
                when (menuItem.itemId) {
                    R.id.action_load_popular_movies -> {
                        lastSelectedMenuOptions = R.id.action_load_popular_movies
                        viewModel.getPopularMovies()
                    }
                    R.id.action_load_top_rated_movies -> {
                        lastSelectedMenuOptions = R.id.action_load_top_rated_movies
                        viewModel.getTopRatedMovies()
                    }
                    R.id.action_load_saved_movies ->{
                        lastSelectedMenuOptions = R.id.action_load_saved_movies
                        viewModel.getSavedMovies()
                    }
                }
                return true
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }


    override fun onResume() {
        super.onResume()
        when(lastSelectedMenuOptions){
            R.id.action_load_saved_movies -> {
                viewModel.getSavedMovies()
            }
        }

    }
}
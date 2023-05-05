package com.ltu.m7019e.v23.themoviedb

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.ltu.m7019e.v23.themoviedb.database.MovieDatabase
import com.ltu.m7019e.v23.themoviedb.database.MovieDatabaseDao
import com.ltu.m7019e.v23.themoviedb.viewmodels.MovieDetailViewModel
import com.ltu.m7019e.v23.themoviedb.viewmodels.MovieDetailViewModelFactory
import com.ltu.m7019e.v23.themoviedb.databinding.FragmentMovieDetailBinding
import com.ltu.m7019e.v23.themoviedb.model.Movie
import com.ltu.m7019e.v23.themoviedb.utils.Constants.IMDB_BASE_URL
import com.ltu.m7019e.v23.themoviedb.network.DataFetchStatus
import com.ltu.m7019e.v23.themoviedb.network.MovieDetailsResponse

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class MovieDetailFragment : Fragment() {

    private var _binding: FragmentMovieDetailBinding? = null;
    private val binding get() = _binding!!

    private lateinit var movieDatabaseDao: MovieDatabaseDao

    private lateinit var movie: Movie
    private lateinit var movieDetail: MovieDetailsResponse
    private lateinit var viewModel: MovieDetailViewModel
    private lateinit var viewModelFactory: MovieDetailViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        movie = MovieDetailFragmentArgs.fromBundle(requireArguments()).movie
        binding.movie = movie


        val application = requireNotNull(this.activity).application

        movieDatabaseDao = MovieDatabase.getInstance(application).movieDatabaseDao

        viewModelFactory = MovieDetailViewModelFactory(movieDatabaseDao, application, movie)
        viewModel = ViewModelProvider(this, viewModelFactory)[MovieDetailViewModel::class.java]

        binding.viewModel = viewModel


        viewModel.movieDetail.observe(viewLifecycleOwner) {
            it?.let {
                binding.movieDetail = it
                movieDetail = it
                var genreText = "Genre: "
                var isFirst = true
                movieDetail.genres.forEach {
                    it?.let {
                        if(isFirst){
                            genreText += it.name
                            isFirst = false
                        } else {
                            genreText += ", " + it.name
                        }
                    }
                }
                binding.genreText.setText(genreText)


                //Create explicit intent with an url
                binding.urlLink.setOnClickListener() {
                    val queryUrl: Uri = Uri.parse(movieDetail.homepage)
                    val intent = Intent(Intent.ACTION_VIEW, queryUrl)
                    context?.startActivity(intent)
                }
                binding.imdbLink.setOnClickListener() {
                    val queryUrl: Uri = Uri.parse(IMDB_BASE_URL + movieDetail.imdb_id)
                    val intent = Intent(Intent.ACTION_VIEW, queryUrl)
                    context?.startActivity(intent)
                }


            }
        }
        viewModel.isFavorite.observe(viewLifecycleOwner){
        it?.let {
            if(it){
                binding.removeFromDatabase.visibility = View.VISIBLE
                binding.saveToDatabase.visibility = View.GONE
            } else {
                binding.removeFromDatabase.visibility = View.GONE
                binding.saveToDatabase.visibility = View.VISIBLE
            }
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

        binding.toMovieReviewFragmentBtn.setOnClickListener() {
            val action =
                MovieDetailFragmentDirections.actionMovieDetailsFragmentToMovieReviewFragment(movie)
            findNavController().navigate(action)
        }

        //Created a recycler view for the genres


    }

}
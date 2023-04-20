package com.ltu.m7019e.v23.themoviedb

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ltu.m7019e.v23.themoviedb.databinding.FragmentMovieDetailBinding
import com.ltu.m7019e.v23.themoviedb.model.Movie
import com.ltu.m7019e.v23.themoviedb.model.MovieDetail
import com.ltu.m7019e.v23.themoviedb.utils.Constants.IMDB_BASE_URL
import com.ltu.m7019e.v23.themoviedb.adapter.GenreAdapter
import com.ltu.m7019e.v23.themoviedb.network.TMDBApi
import com.ltu.m7019e.v23.themoviedb.network.TMDBApiService

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class MovieDetailFragment : Fragment() {

    private var _binding: FragmentMovieDetailBinding? = null;
    private val binding get() = _binding!!

    private lateinit var movie: Movie
    private lateinit var movieDetail: MovieDetail

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMovieDetailBinding.inflate(inflater)
        movie = MovieDetailFragmentArgs.fromBundle(requireArguments()).movie
        binding.movie = movie

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.toThirdFragmentBtn.setOnClickListener(){
            val action = MovieDetailFragmentDirections.actionMovieDetailsFragmentToThirdFragment(movie)
            findNavController().navigate(action)
        }

        //Created a recycler view for the genres

        recyclerView = binding.recycleViewGenres
        recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = GenreAdapter(movieDetail.genres)

        //Create explicit intent with an url
        binding.urlLink.setOnClickListener(){
            val queryUrl: Uri = Uri.parse("${movieDetail.URL_link}")
            val intent = Intent(Intent.ACTION_VIEW, queryUrl)
            context?.startActivity(intent)
        }
        binding.imdbLink.setOnClickListener(){
            val queryUrl: Uri = Uri.parse(IMDB_BASE_URL + "${movieDetail.imdb_id}")
            val intent = Intent(Intent.ACTION_VIEW, queryUrl)
            context?.startActivity(intent)
        }
    }

}
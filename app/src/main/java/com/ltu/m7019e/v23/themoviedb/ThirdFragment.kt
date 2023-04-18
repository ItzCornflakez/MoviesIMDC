package com.ltu.m7019e.v23.themoviedb

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController

import com.ltu.m7019e.v23.themoviedb.databinding.FragmentThirdBinding
import com.ltu.m7019e.v23.themoviedb.model.Movie
import com.ltu.m7019e.v23.themoviedb.model.MovieDetail


/**
 * A simple [Fragment] subclass.
**/
class ThirdFragment : Fragment() {

    private var _binding: FragmentThirdBinding? = null;
    private val binding get() = _binding!!

    private lateinit var movie: Movie
    private lateinit var movieDetail: MovieDetail

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentThirdBinding.inflate(inflater)

        movie = MovieDetailFragmentArgs.fromBundle(requireArguments()).movie
        movieDetail = MovieDetailFragmentArgs.fromBundle(requireArguments()).movieDetail

        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toMovieDetailBtn.setOnClickListener(){
            val action = ThirdFragmentDirections.actionThirdFragmentToMovieDetailsFragment(movie, movieDetail)
            findNavController().navigate(action)
        }
    }
}
package com.example.app_db.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.app_db.R
import com.example.app_db.data.Application
import com.example.app_db.data.movie.MovieViewModel
import com.example.app_db.databinding.FragmentMovieBinding

class MovieFragment : Fragment(R.layout.fragment_movie) {

    private val movieViewModel: MovieViewModel by viewModels {
        MovieViewModel.MovieViewModelFactory((activity?.application as Application).repository)
    }

    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding!!

    val args: MovieFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var movie = args.movie

        binding.txtMovieName.text = movie.name

        binding.btnEdit.setOnClickListener {
            val action = MovieFragmentDirections.actionMovieFragmentToAddEditMovieFragment()
            findNavController().navigate(action)
        }


        binding.btnRemove.setOnClickListener {
            movieViewModel.delete(movie)
            val action = MovieFragmentDirections.actionMovieFragmentToHomeFragment()
            findNavController().navigate(action)
        }
    }
}
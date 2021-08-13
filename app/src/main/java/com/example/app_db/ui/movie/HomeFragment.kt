package com.example.app_db.ui.movie

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.app_db.R
import com.example.app_db.data.Application
import com.example.app_db.data.movie.Movie
import com.example.app_db.data.movie.MovieViewModel
import com.example.app_db.databinding.FragmentHomeBinding

class HomeFragment : Fragment(R.layout.fragment_home), MovieAdapter.OnItemClickListener {



    private val movieViewModel: MovieViewModel by viewModels {
        MovieViewModel.MovieViewModelFactory((activity?.application as Application).repository)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentHomeBinding.bind(view)
        val movieAdapter = MovieAdapter(this)

        binding.apply {
            recyclerViewMovies.apply {
                adapter = movieAdapter
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)
            }
        }

        movieViewModel.allMovies.observe(viewLifecycleOwner) {
            movieAdapter.submitList(it)
        }

        binding.btnAddNewMovie.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToAddEditMovieFragment()
            findNavController().navigate(action)
        }

    }

    override fun onItemClickListener(movie: Movie) {
        val action = HomeFragmentDirections.actionHomeFragmentToMovieFragment(movie)
        findNavController().navigate(action)
    }

}
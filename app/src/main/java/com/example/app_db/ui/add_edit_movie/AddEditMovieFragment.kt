package com.example.app_db.ui.add_edit_movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.app_db.R
import com.example.app_db.data.Application
import com.example.app_db.data.movie.Movie
import com.example.app_db.data.movie.MovieViewModel
import com.example.app_db.databinding.FragmentAddEditMovieBinding

class AddEditMovieFragment : Fragment(R.layout.fragment_add_edit_movie)  {

    private val movieViewModel: MovieViewModel by viewModels {
         MovieViewModel.MovieViewModelFactory((activity?.application as Application).repository)
    }

    private var _binding: FragmentAddEditMovieBinding? = null
    private val binding get() = _binding!!

    val args: AddEditMovieFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddEditMovieBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val newMovie = args.movie == null
        val movie = args?.movie ?: Movie(name = "")

        binding.apply {
            movieNameEditText.setText(movie.name)

            btnSave.setOnClickListener {
                var movieName = binding.movieNameEditText.text.toString()

                if (movieName.isBlank()) {
                    Toast.makeText(context, "Enter the movie name", Toast.LENGTH_SHORT).show()
                } else {
                    if (newMovie) {
                        movieViewModel.insert(Movie(name = movieName))
                    } else {
                        movieViewModel.update(movie.copy(name = movieName))
                    }

                    val action =
                        AddEditMovieFragmentDirections.actionAddEditMovieFragmentToMovieFragment(
                            Movie(name = movieName)
                        )
                    findNavController().navigate(action)
                }

            }
        }
    }

}
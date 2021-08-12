package com.example.app_db.ui.add_edit_movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.app_db.R
import com.example.app_db.data.movie.Movie
import com.example.app_db.databinding.FragmentAddEditMovieBinding

class AddEditMovieFragment : Fragment(R.layout.fragment_add_edit_movie)  {

    private var _binding: FragmentAddEditMovieBinding? = null
    private val binding get() = _binding!!

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

        binding.btnSaveNewMovie.setOnClickListener {
            val action = AddEditMovieFragmentDirections.actionAddEditMovieFragmentToMovieFragment(
                Movie(name="Teste Movie")
            )
            findNavController().navigate(action)
        }
    }

}
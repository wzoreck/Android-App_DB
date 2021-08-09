package com.example.app_db

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.app_db.databinding.FragmentMovieBinding

class MovieFragment : Fragment(R.layout.fragment_movie) {

    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding!!

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

        binding.btnTempDirectors.setOnClickListener {
            val action = MovieFragmentDirections.actionMovieFragmentToDirectorsFragment()
            findNavController().navigate(action)
        }

        binding.btnEdit.setOnClickListener {
            val action = MovieFragmentDirections.actionMovieFragmentToAddEditMovieFragment()
            findNavController().navigate(action)
        }

        binding.btnRemove.setOnClickListener {
            val action = MovieFragmentDirections.actionMovieFragmentToHomeFragment()
            findNavController().navigate(action)
        }
    }
}
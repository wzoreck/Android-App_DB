package com.example.app_db.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.app_db.R
import com.example.app_db.databinding.FragmentHomeBinding

class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnDirectors.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToDirectorsFragment()
            findNavController().navigate(action)
        }

        binding.btnAddNewMovie.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToAddEditMovieFragment()
            findNavController().navigate(action)
        }

        /*binding.btnTempMovie.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToMovieFragment()
            findNavController().navigate(action)
        }*/
    }
}
package com.example.app_db

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.app_db.databinding.FragmentDirectorBinding

class DirectorFragment : Fragment(R.layout.fragment_director) {

    private var _binding: FragmentDirectorBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDirectorBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnTempMovies.setOnClickListener {
            val action = DirectorFragmentDirections.actionDirectorFragmentToMovieFragment()
            findNavController().navigate(action)
        }

    }
}
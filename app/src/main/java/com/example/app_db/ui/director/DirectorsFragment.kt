package com.example.app_db.ui.director

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.app_db.R
import com.example.app_db.databinding.FragmentDirectorsBinding

class DirectorsFragment : Fragment(R.layout.fragment_directors) {

    private var _binding: FragmentDirectorsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDirectorsBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnTempDirector.setOnClickListener {
            val action = DirectorsFragmentDirections.actionDirectorsFragmentToDirectorFragment()
            findNavController().navigate(action)
        }

        binding.btnMovies.setOnClickListener {
            val action = DirectorsFragmentDirections.actionDirectorsFragmentToHomeFragment()
            findNavController().navigate(action)
        }

        binding.btnAddNewDirector.setOnClickListener {
            val action = DirectorsFragmentDirections.actionDirectorsFragmentToAddEditDirectorFragment()
            findNavController().navigate(action)
        }

    }
}
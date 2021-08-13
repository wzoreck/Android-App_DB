package com.example.app_db.ui.director

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.app_db.R
import com.example.app_db.data.Application
import com.example.app_db.data.DirectorWithMovies
import com.example.app_db.data.director.Director
import com.example.app_db.data.director.DirectorViewModel
import com.example.app_db.databinding.FragmentDirectorsBinding

class DirectorsFragment: Fragment(R.layout.fragment_directors), DirectorAdapter.OnItemClickListener {

    private val directorViewModel: DirectorViewModel by viewModels {
        DirectorViewModel.DirectorViewModelFactory((activity?.application as Application).repository)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentDirectorsBinding.bind(view)
        val directorAdapter = DirectorAdapter(this)

        binding.apply {
            recyclerViewDirectors.apply {
                adapter = directorAdapter
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)
            }
        }

        directorViewModel.allDirectorsWithMovies.observe(viewLifecycleOwner) {
            directorAdapter.submitList(it)
        }

        binding.btnAddNewDirector.setOnClickListener {
            val action = DirectorsFragmentDirections.actionDirectorsFragmentToAddEditDirectorFragment()
            findNavController().navigate(action)
        }
    }

    override fun onItemClickListener(director: DirectorWithMovies) {
        val action = DirectorsFragmentDirections.actionDirectorsFragmentToDirectorFragment(director.director)
        findNavController().navigate(action)
    }

}
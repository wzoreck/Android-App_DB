package com.example.app_db.ui.director

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.app_db.DirectorApplication
import com.example.app_db.R
import com.example.app_db.data.Director
import com.example.app_db.data.DirectorViewModel
import com.example.app_db.databinding.FragmentDirectorBinding
import com.example.app_db.databinding.FragmentDirectorsBinding

class DirectorFragment : Fragment(R.layout.fragment_director) {

    private val directorViewModel: DirectorViewModel by viewModels {
        DirectorViewModel.DirectorViewModelFactory((activity?.application as DirectorApplication).repository)
    }

    private var _binding: FragmentDirectorBinding? = null
    private val binding get() = _binding!!

    val args: DirectorFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDirectorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var director = args.director

        binding.btnTempMovie.setOnClickListener {
            val action = DirectorFragmentDirections.actionDirectorFragmentToMovieFragment()
            findNavController().navigate(action)
        }

        binding.btnEdit.setOnClickListener {
            val action = DirectorFragmentDirections.actionDirectorFragmentToAddEditDirectorFragment(director)
            findNavController().navigate(action)
        }

        binding.btnDirectors.setOnClickListener {
            val action = DirectorFragmentDirections.actionDirectorFragmentToDirectorsFragment()
            findNavController().navigate(action)
        }

        binding.btnRemove.setOnClickListener {
            directorViewModel.delete(director)
            val action = DirectorFragmentDirections.actionDirectorFragmentToDirectorsFragment()
            findNavController().navigate(action)
        }

        val callbackList = requireActivity().onBackPressedDispatcher.addCallback(this) {
            var action = DirectorFragmentDirections.actionDirectorFragmentToDirectorsFragment()
            findNavController().navigate(action)
        }
    }
}
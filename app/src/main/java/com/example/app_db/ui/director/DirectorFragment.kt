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
import com.example.app_db.R
import com.example.app_db.data.Application
import com.example.app_db.data.director.DirectorViewModel
import com.example.app_db.data.movie.Movie
import com.example.app_db.databinding.FragmentDirectorBinding

class DirectorFragment : Fragment(R.layout.fragment_director) {

    private val directorViewModel: DirectorViewModel by viewModels {
        DirectorViewModel.DirectorViewModelFactory((activity?.application as Application).repository)
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

        binding.txtDirectorName.text = director.name

        binding.btnEdit.setOnClickListener {
            val action = DirectorFragmentDirections.actionDirectorFragmentToAddEditDirectorFragment(director)
            findNavController().navigate(action)
        }

        binding.btnRemove.setOnClickListener {
            directorViewModel.delete(director)
            val action = DirectorFragmentDirections.actionDirectorFragmentToDirectorsFragment()
            findNavController().navigate(action)
        }

//        val callbackList = requireActivity().onBackPressedDispatcher.addCallback(this) {
//            var action = DirectorFragmentDirections.actionDirectorFragmentToDirectorsFragment()
//            findNavController().navigate(action)
//        }
    }
}
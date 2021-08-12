package com.example.app_db.ui.add_edit_director

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
import com.example.app_db.data.director.Director
import com.example.app_db.data.director.DirectorViewModel
import com.example.app_db.databinding.FragmentAddEditDirectorBinding

class AddEditDirectorFragment : Fragment(R.layout.fragment_add_edit_director) {

    private val directorViewModel: DirectorViewModel by viewModels {
        DirectorViewModel.DirectorViewModelFactory((activity?.application as Application).repository)
    }

    private var _binding: FragmentAddEditDirectorBinding? = null
    private val binding get() = _binding!!

    val args: AddEditDirectorFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddEditDirectorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val newDirector = args.director == null
        val director = args?.director ?: Director(name="")

        binding.apply {
            directorNameEditText.setText(director.name)

            btnSave.setOnClickListener {
                var directorName = binding.directorNameEditText.text.toString()

                if (directorName.isBlank()) {
                    Toast.makeText(context, "Enter the director name", Toast.LENGTH_SHORT).show()
                } else {
                    if (newDirector) {
                        directorViewModel.insert(Director(name=directorName))
                    } else {
                        directorViewModel.update(director.copy(name=directorName))
                    }

                    val action = AddEditDirectorFragmentDirections.actionAddEditDirectorFragmentToDirectorFragment(
                        Director(name = directorName)
                    )
                    findNavController().navigate(action)
                }
            }
        }
    }
}
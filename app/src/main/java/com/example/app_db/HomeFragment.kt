package com.example.app_db

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class HomeFragment : Fragment(R.layout.fragment_home) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val addNewMovieButton =  getView()?.findViewById<Button>(R.id.btnAddNewMovie)
        addNewMovieButton?.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToAddEditMovieFragment()
            findNavController().navigate(action)
        }
    }
}
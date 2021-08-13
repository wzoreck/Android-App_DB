package com.example.app_db.ui.director

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.app_db.data.DirectorWithMovies
import com.example.app_db.data.director.Director
import com.example.app_db.databinding.DirectorItemBinding

class DirectorAdapter(private val listener: OnItemClickListener) : ListAdapter<DirectorWithMovies, DirectorAdapter.DirectorViewHolder>(DIRECTOR_COMPARATOR){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DirectorViewHolder {
        val binding = DirectorItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DirectorViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DirectorViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    inner class DirectorViewHolder(private val binding: DirectorItemBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.apply {
                root.setOnClickListener {
                    val position = adapterPosition
                    if(position != RecyclerView.NO_POSITION){
                        val director = getItem(position)
                        listener.onItemClickListener(director)
                    }
                }
            }
        }

        fun bind(directorWithMovies: DirectorWithMovies){
            binding.apply {
                txtDirectorName.text = directorWithMovies.director.name
            }
        }

    }

    interface OnItemClickListener {
        fun onItemClickListener(director: DirectorWithMovies)
    }

    companion object {
        private val DIRECTOR_COMPARATOR = object : DiffUtil.ItemCallback<DirectorWithMovies>(){
            override fun areItemsTheSame(oldItem: DirectorWithMovies, newItem: DirectorWithMovies): Boolean {
                return oldItem.director.id == newItem.director.id
            }

            override fun areContentsTheSame(oldItem: DirectorWithMovies, newItem: DirectorWithMovies): Boolean {
                return oldItem == newItem
            }
        }
    }

}
package com.example.app_db.ui.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.app_db.data.MovieWithDirector
import com.example.app_db.data.movie.Movie
import com.example.app_db.databinding.MovieItemBinding


class MovieAdapter(private val listener: OnItemClickListener): ListAdapter<MovieWithDirector, MovieAdapter.MovieViewHolder>(MovieAdapter.MOVIE_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    inner class MovieViewHolder(private val binding: MovieItemBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.apply {
                root.setOnClickListener {
                    val position = adapterPosition
                    if(position != RecyclerView.NO_POSITION){
                        val movie = getItem(position)
                        listener.onItemClickListener(movie)
                    }
                }
            }
        }

        fun bind(movieWithDirector: MovieWithDirector){
            binding.apply {
                txtMovieName.text = movieWithDirector.movie.name
                directorNameTextView.text = "by ${movieWithDirector.director.name}"
            }
        }

    }

    interface OnItemClickListener {
        fun onItemClickListener(movie: MovieWithDirector)
    }

    companion object {
        private val MOVIE_COMPARATOR = object : DiffUtil.ItemCallback<MovieWithDirector>(){
            override fun areItemsTheSame(oldItem: MovieWithDirector, newItem: MovieWithDirector): Boolean {
                return oldItem.movie.id == newItem.movie.id
            }

            override fun areContentsTheSame(oldItem: MovieWithDirector, newItem: MovieWithDirector): Boolean {
                return oldItem == newItem
            }
        }
    }

}
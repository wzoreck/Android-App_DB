package com.example.app_db.ui.director

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.app_db.data.Director
import com.example.app_db.databinding.DirectorItemBinding

class DirectorAdapter(private val listener: OnItemClickListener) : ListAdapter<Director, DirectorAdapter.DirectorViewHolder>(DIRECTOR_COMPARATOR){

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
                        val task = getItem(position)
                        listener.onItemClickListener(task)
                    }
                }
            }
        }

        fun bind(director: Director){
            binding.apply {
                txtDirectorName.text = director.name
            }
        }

    }

    interface OnItemClickListener {
        fun onItemClickListener(director: Director)
    }

    companion object {
        private val DIRECTOR_COMPARATOR = object : DiffUtil.ItemCallback<Director>(){
            override fun areItemsTheSame(oldItem: Director, newItem: Director): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Director, newItem: Director): Boolean {
                return oldItem == newItem
            }
        }
    }

}
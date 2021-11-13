package com.example.movietv.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.movietv.databinding.ItemMainRecyclerviewBinding
import com.example.movietv.model.TvShowModel

class MainAdapter(private val listener: OnItemClick) : RecyclerView.Adapter<MainAdapter.MyViewHolder>() {

    inner class MyViewHolder(val binding: ItemMainRecyclerviewBinding) :
        RecyclerView.ViewHolder(binding.root){
            init {
               binding.layoutID.setOnClickListener { v ->
                    listener
                        ?.OnItemClicked(tvShows[adapterPosition])
                }
            }
        }




    private val diffCallback = object : DiffUtil.ItemCallback<TvShowModel>() {
        override fun areItemsTheSame(oldItem: TvShowModel, newItem: TvShowModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: TvShowModel, newItem: TvShowModel): Boolean {
            return newItem == oldItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    var tvShows: List<TvShowModel>
        get() = differ.currentList
        set(value) {
            differ.submitList(value)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ItemMainRecyclerviewBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentTvShow = tvShows[position]

        holder.binding.apply {
            tvMoveName.text = currentTvShow.name

            ivMovie.load(currentTvShow.image.original) {
                crossfade(true)
                crossfade(1000)
            }
        }

    }

    override fun getItemCount() = tvShows.size

}
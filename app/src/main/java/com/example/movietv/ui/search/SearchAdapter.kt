package com.example.movietv.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.movietv.databinding.ItemMainRecyclerviewBinding
import com.example.movietv.model.search.TvSearchModel
import com.example.movietv.ui.main.OnItemClick

class SearchAdapter(private val listener: ONITemClickedSearch) : RecyclerView.Adapter<SearchAdapter.MyViewHolder>() {

    inner class MyViewHolder(val binding: ItemMainRecyclerviewBinding) :
        RecyclerView.ViewHolder(binding.root){
        init {
            binding.layoutID.setOnClickListener { v ->
                listener
                    ?.OnItemClickedSearch(tvShows[adapterPosition])
            }
        }
    }


    private val diffCallback = object : DiffUtil.ItemCallback<TvSearchModel>() {
        override fun areItemsTheSame(oldItem: TvSearchModel, newItem: TvSearchModel): Boolean {
            return oldItem.show.id == newItem.show.id
        }

        override fun areContentsTheSame(oldItem: TvSearchModel, newItem: TvSearchModel): Boolean {
            return newItem == oldItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    var tvShows: List<TvSearchModel>
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
            tvMoveName.text = currentTvShow.show.name
            ivMovie.load(currentTvShow.show.image.original) {
                crossfade(true)
                crossfade(1000)
            }
        }

    }

    override fun getItemCount() = tvShows.size

}
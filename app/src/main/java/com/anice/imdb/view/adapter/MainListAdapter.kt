package com.anice.imdb.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.anice.imdb.R
import com.anice.imdb.data.model.Search
import com.anice.imdb.databinding.ItemMainListBinding
import com.anice.imdb.view.activity.main_page.OnItemClickListener


class MainListAdapter(private val list: List<Search>, private val listener: OnItemClickListener) :
    RecyclerView.Adapter<MainListAdapter.MainListViewHolder>() {


    @NonNull
    override fun onCreateViewHolder(@NonNull parent: ViewGroup, viewType: Int): MainListViewHolder {
        val binding = DataBindingUtil.inflate<ItemMainListBinding>(
            LayoutInflater.from(parent.getContext()),
            R.layout.item_main_list,
            parent,
            false
        )
        return MainListViewHolder(binding)

    }

    override fun onBindViewHolder(@NonNull holder: MainListViewHolder, position: Int) {
        val movie = list.get(position)
        holder.binding.item = movie
        holder.binding.cardView.setOnClickListener {
            listener.onItemClick(movie.imdbID)
        }
    }


    inner class MainListViewHolder(@NonNull binding: ItemMainListBinding) :
        RecyclerView.ViewHolder(binding.getRoot()) {
        val binding: ItemMainListBinding

        init {
            this.binding = binding
        }
    }


    override fun getItemCount(): Int {
        return list.size
    }
}
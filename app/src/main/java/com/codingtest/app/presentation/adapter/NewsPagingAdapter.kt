package com.codingtest.app.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.codingtest.app.data.model.Articles
import com.codingtest.app.databinding.ListItemBinding
import com.codingtest.app.presentation.adapter.viewHolder.NewsViewHolder

class NewsPagingAdapter(private val listener: NewsWithPagingAdapterListener) :
    PagingDataAdapter<Articles , NewsViewHolder>(UserComparator) {

    interface NewsWithPagingAdapterListener {
        fun onClickItem(articles: Articles)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = ListItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding)
    }
    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val articles = getItem(position)
        articles?.let { article ->
            holder.bind(article, position,listener)
        }

    }
    object UserComparator : DiffUtil.ItemCallback<Articles>() {
        override fun areItemsTheSame(oldItem: Articles, newItem: Articles): Boolean {
            return oldItem.source?.id == newItem.source?.id
        }

        override fun areContentsTheSame(oldItem: Articles, newItem: Articles): Boolean {
            return oldItem == newItem
        }
    }
}
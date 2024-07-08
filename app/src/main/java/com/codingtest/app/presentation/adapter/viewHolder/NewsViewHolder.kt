package com.codingtest.app.presentation.adapter.viewHolder

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.codingtest.app.R
import com.codingtest.app.data.model.Articles
import com.codingtest.app.databinding.ListItemBinding
import com.codingtest.app.presentation.adapter.NewsPagingAdapter
import com.codingtest.app.presentation.extension.formatToDisplay

class NewsViewHolder(private val itemsView: ListItemBinding) :
    RecyclerView.ViewHolder(itemsView.root) {
    @SuppressLint("SetTextI18n")
    fun bind(
        article: Articles,
        position: Int,
        listener: NewsPagingAdapter.NewsWithPagingAdapterListener
    ) {
        itemsView.apply {
            Glide.with(itemsView.root)
                .load(article.urlToImage)
                .placeholder(R.mipmap.ic_loading)
                .error(R.mipmap.ic_no_photo)
                .into(thumbnailImageView)
            titleTextView.text = "${position.plus(1)}. ${article.title}"
            descTextView.text = article.description
            publishedAtTextView.text = article.publishedAt?.formatToDisplay()
            mainLayout.setOnClickListener {
                listener.onClickItem(article)
            }
        }
    }
}
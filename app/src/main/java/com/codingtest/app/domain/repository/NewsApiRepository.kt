package com.codingtest.app.domain.repository

import com.codingtest.app.data.model.NewsModel
import kotlinx.coroutines.flow.Flow

interface NewsApiRepository {
    fun getNews(page:Int): Flow<NewsModel>
}
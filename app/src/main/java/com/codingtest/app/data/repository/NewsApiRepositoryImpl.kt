package com.codingtest.app.data.repository

import com.codingtest.app.data.ApiInterface
import com.codingtest.app.data.model.NewsModel
import com.codingtest.app.domain.repository.NewsApiRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class NewsApiRepositoryImpl(private val apiInterface: ApiInterface) : NewsApiRepository {
    private val apiKey = "d8079fc4d3e14739905814765d9be0e3"
    override fun getNews(page: Int): Flow<NewsModel> {
        return flow {
            emit(
                apiInterface.getNewsApi(
                    "apple",
                    "2024-07-04",
                    "2024-07-04",
                    "popularity",
                    apiKey,
                    page
                )
            )
        }
    }
}

package com.codingtest.app.domain.usecase

import android.util.Log
import com.codingtest.app.data.model.NewsModel
import com.codingtest.app.domain.repository.NewsApiRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface NewsApiUseCase {
    fun execute(page: Int, loadSize: Int): Flow<NewsModel>
}

class NewsApiUseCaseImpl(
    private val newsApiRepository: NewsApiRepository
) : NewsApiUseCase {
    override fun execute(page: Int, loadSize: Int): Flow<NewsModel> {
        Log.i("TAG", "execute -> page[$page] , loadSize[$loadSize]")
        return flow {
            newsApiRepository.getNews(page).collect { result ->
                emit(result)
            }
        }
    }
}
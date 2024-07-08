package com.codingtest.app.di

import com.codingtest.app.data.netWorkService
import com.codingtest.app.data.repository.NewsApiRepositoryImpl
import com.codingtest.app.domain.repository.NewsApiRepository
import com.codingtest.app.domain.usecase.NewsApiUseCase
import com.codingtest.app.domain.usecase.NewsApiUseCaseImpl
import com.codingtest.app.presentation.viewmodel.NewsApiViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModule = module {
    factory { netWorkService() }

    factory<NewsApiRepository> {
        NewsApiRepositoryImpl(apiInterface = get())
    }
    factory<NewsApiUseCase> {
        NewsApiUseCaseImpl(
            newsApiRepository = get()
        )
    }
    viewModel {
        NewsApiViewModel(
            newsApiUseCase = get()
        )
    }
}
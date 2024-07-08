package com.codingtest.app.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.codingtest.app.data.model.Articles
import com.codingtest.app.domain.usecase.NewsApiUseCase
import com.codingtest.app.paging.NewsPagingSource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class NewsApiViewModel(
    private val newsApiUseCase: NewsApiUseCase
) : ViewModel() {

    private var newsMutableLiveData = MutableLiveData<PagingData<Articles>>()
    fun observeNewsLiveData(): LiveData<PagingData<Articles>> = newsMutableLiveData

    fun getNewsData() {
        viewModelScope.launch {
            Pager(PagingConfig(pageSize = 15)) {
                NewsPagingSource(newsApiUseCase)
            }.flow.cachedIn(viewModelScope)
                .collectLatest {
                    delay(1000)
                    newsMutableLiveData.postValue(it)
                }
        }
    }

}
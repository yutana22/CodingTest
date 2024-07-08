package com.codingtest.app.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.codingtest.app.data.model.Articles
import com.codingtest.app.domain.usecase.NewsApiUseCase
import kotlinx.coroutines.flow.single

class NewsPagingSource(
    private val newsApiUseCase: NewsApiUseCase
) : PagingSource<Int, Articles>() {
    override fun getRefreshKey(state: PagingState<Int, Articles>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Articles> {
        val page = params.key ?: 1
        return try {
            val response = newsApiUseCase.execute(page, params.loadSize).single()
            LoadResult.Page(
                data = response.articles,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (response.articles.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}
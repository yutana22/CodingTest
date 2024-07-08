package com.codingtest.app.data.repository


import com.codingtest.app.data.ApiInterface
import com.codingtest.app.data.model.Articles
import com.codingtest.app.data.model.NewsModel
import com.codingtest.app.data.model.Source
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
internal class NewsApiRepositoryImplTest {

    private val apiInterface: ApiInterface = mock()
    private val repository = NewsApiRepositoryImpl(apiInterface)

    @Test
    fun testGetNews() = runTest {
        val articles = arrayListOf(
            Articles(
                source = Source("1", "Source Name"),
                author = "Author Name",
                title = "Article Title",
                description = "Article Description",
                url = "http://example.com",
                urlToImage = "http://example.com/image.jpg",
                publishedAt = "2024-01-01T00:00:00Z",
                content = "Article Content"
            )
        )
        val newsModel = NewsModel("ok", 1, articles)
        whenever(
            apiInterface.getNewsApi(
                "apple",
                "2024-07-04",
                "2024-07-04",
                "popularity",
                "d8079fc4d3e14739905814765d9be0e3",
                1
            )
        )
            .thenReturn(newsModel)
        val result = repository.getNews(1).first()
        assertEquals(newsModel, result)
    }
}
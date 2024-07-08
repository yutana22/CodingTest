package com.codingtest.app.domain.usecase

import com.codingtest.app.data.model.Articles
import com.codingtest.app.data.model.NewsModel
import com.codingtest.app.data.model.Source
import com.codingtest.app.domain.repository.NewsApiRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class NewsApiUseCaseImplTest {
    private val newsApiRepository: NewsApiRepository = mock()
    private val useCase: NewsApiUseCase = NewsApiUseCaseImpl(newsApiRepository)

    @Test
    fun testExecuteSuccess() = runTest {
// Mocked response
        val expectedNewsModel = NewsModel(
            status = "ok",
            totalResults = 1,
            articles = arrayListOf(
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
        )
        whenever(newsApiRepository.getNews(1)).thenReturn(flow {
            emit(expectedNewsModel)
        })
        // Call the use case method
        val flow = useCase.execute(1, 10)

        // Collect the flow and assert the result
        val result = flow.first()
        assertEquals(expectedNewsModel, result)
    }
}
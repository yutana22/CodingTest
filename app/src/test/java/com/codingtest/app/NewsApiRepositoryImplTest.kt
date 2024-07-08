package com.codingtest.app

import com.codingtest.app.data.ApiInterface
import com.codingtest.app.data.model.NewsModel
import com.codingtest.app.data.netWorkService
import com.codingtest.app.data.repository.NewsApiRepositoryImpl
import com.codingtest.app.domain.repository.NewsApiRepository
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class NewsApiRepositoryImplTest {
    // Mock the network service
    private lateinit var apiInterface: ApiInterface
    private lateinit var newsApiRepository: NewsApiRepository

    @Before
    fun setUp() {
        // Initialize mocks
        apiInterface = mockk()
        newsApiRepository = NewsApiRepositoryImpl(netWorkService())
    }

    @Test
    fun `test getNews function`() = runBlockingTest {
        // Given
        val page = 1
        val expectedNewsModel = NewsModel()

        // Mock network service response
        coEvery { netWorkService().getNewsApi(any(), any(), any(), any(), any(), any()) } returns expectedNewsModel

        // When
        val result = newsApiRepository.getNews(page).first()

        // Then
        assertEquals(expectedNewsModel, result)

        // Verify network service was called with correct parameters
//        verify {
//            apiInterface.getNewsApi(
//                query = "apple",
//                from = "2024-07",
//                to = "2024-07-04",
//                sortBy = "popularity",
//                apiKey = "d8079fc4d3e14739905814765d9be0e3",
//                page = page
//            )
//        }
    }

}
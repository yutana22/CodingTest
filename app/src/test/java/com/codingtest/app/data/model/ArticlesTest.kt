package com.codingtest.app.data.model

import com.google.gson.Gson
import org.junit.Assert.assertEquals
import org.junit.Test

class ArticlesTest {
    @Test
    fun testParseArticlesModel() {
        val source = Source("1", "Source Name")
        val article = Articles(
            source = source,
            author = "Author Name",
            title = "Article Title",
            description = "Article Description",
            url = "http://example.com",
            urlToImage = "http://example.com/image.jpg",
            publishedAt = "2024-01-01T00:00:00Z",
            content = "Article Content"
        )
        val gson = Gson()

        val json = gson.toJson(article)
        val expectedJson = """{"source":{"id":"1","name":"Source Name"},"author":"Author Name","title":"Article Title","description":"Article Description","url":"http://example.com","urlToImage":"http://example.com/image.jpg","publishedAt":"2024-01-01T00:00:00Z","content":"Article Content"}"""
        assertEquals(expectedJson, json)
    }
}
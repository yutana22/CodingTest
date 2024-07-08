package com.codingtest.app.data.model

import com.google.gson.Gson
import org.junit.Assert.assertEquals
import org.junit.Test

class SourceTest {
    @Test
    fun testParseSourceModel() {
        val source = Source("1", "Test Source")
        val gson = Gson()

        val json = gson.toJson(source)
        val expectedJson = """{"id":"1","name":"Test Source"}"""
        assertEquals(expectedJson, json)
    }
}
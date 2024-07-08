package com.codingtest.app.data

import com.codingtest.app.data.model.NewsModel
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiInterface {
    @Headers("Content-Type: application/json")
    @GET("everything")
    suspend fun getNewsApi(
        @Query("q") q: String,
        @Query("from") from: String,
        @Query("to") to: String,
        @Query("sortBy") sortBy: String,
        @Query("apiKey") apiKey: String,
        @Query("page") page: Int

    ): NewsModel
}
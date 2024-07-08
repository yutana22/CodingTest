package com.codingtest.app.data.model

import com.google.gson.annotations.SerializedName


class NewsModel(
    @SerializedName("status")
    var status: String? = null,
    @SerializedName("totalResults")
    var totalResults: Int? = null,
    @SerializedName("articles")
    var articles: ArrayList<Articles> = arrayListOf()
)
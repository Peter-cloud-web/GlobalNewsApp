package com.example.globalnewsapp.api


import com.example.globalnewsapp.BuildConfig
import com.example.globalnewsapp.models.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface NewsAPI {

    companion object {
        val api_key = BuildConfig.NEWS_API_KEY
    }


    @GET("v2/top-headlines")
    suspend fun getBreakingNews(
        @Query("country") countryCode: String = "us",
        @Query("page") pageNumber: Int = 1,
        @Query("apiKey") apiKey: String = NewsAPI.api_key
    ): Response<NewsResponse>

    @GET("v2/everything")
    suspend fun searchNews(
        @Query("q") searchQuery: String,
        @Query("page") pageNumber: Int = 1,
        @Query("apiKey") apiKey: String = NewsAPI.api_key
    ): Response<NewsResponse>
}
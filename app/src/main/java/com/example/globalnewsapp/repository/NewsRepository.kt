package com.example.globalnewsapp.repository

import com.example.globalnewsapp.api.RetrofitInstance
import com.example.globalnewsapp.db.ArticleDatabase
import com.example.globalnewsapp.models.Article

class NewsRepository(
    val db: ArticleDatabase
) {
    suspend fun getBreakingNews(countryCode: String, pageNumber: Int) =
        RetrofitInstance.api.getBreakingNews(countryCode, pageNumber)

    suspend fun searchNews(searchQuery: String, pageNumber: Int) =
        RetrofitInstance.api.searchNews(searchQuery, pageNumber)

    suspend fun insertArticle(article: Article) =
        db.getArticleDao().insertArticle(article)

    fun getSavedNews() = db.getArticleDao().getAllArticles()

    suspend fun deleteArticle(article: Article) {
        db.getArticleDao().deleteArticle(article)
    }


}
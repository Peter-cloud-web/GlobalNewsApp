package com.example.globalnewsapp.ui.viewmodel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.globalnewsapp.models.Article
import com.example.globalnewsapp.models.NewsResponse
import com.example.globalnewsapp.repository.NewsRepository
import com.example.globalnewsapp.util.AppApplication
import com.example.globalnewsapp.util.Constants
import com.example.globalnewsapp.util.Resource
import com.google.android.material.internal.ContextUtils.getActivity
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException

class NewsViewModel(val newsRepository: NewsRepository) : ViewModel() {

    val breakingNews: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var breakingNewsPage = 1

    val searchNews: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var searchNewsPage = 1

    init {
        getBreakingNews("us")

    }

    fun getBreakingNews(countryCode: String) = viewModelScope.launch {
            breakingNews.postValue(Resource.Loading())
            val response = newsRepository.getBreakingNews(countryCode, breakingNewsPage)
            breakingNews.postValue(handleBreakingNewsResponse(response))
    }

    fun searchNews(searchQuery: String) = viewModelScope.launch {

                searchNews.postValue(Resource.Loading())
                val response = newsRepository.searchNews(searchQuery, searchNewsPage)
                searchNews.postValue(handleSearchNewsResponse(response))

    }

    private fun handleBreakingNewsResponse(response: Response<NewsResponse>): Resource<NewsResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

;    private fun handleSearchNewsResponse(response: Response<NewsResponse>): Resource<NewsResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    fun saveArticle(article: Article) = viewModelScope.launch {
        newsRepository.insertArticle(article)
    }

    fun fetchSavedArticles() = newsRepository.getSavedNews()

    fun deleteArticle(article: Article) = viewModelScope.launch {
        newsRepository.deleteArticle(article)
    }
//
//    private fun hasInternetConnection(): Boolean {
//
//        val connectivityManager = getApplication<AppApplication>().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//        val activeNetwork = connectivityManager.activeNetwork ?: return false
//        val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
//        return when {
//            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
//            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
//            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
//            else -> false
//        }
//    }



}

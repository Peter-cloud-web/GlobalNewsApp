package com.example.globalnewsapp.ui.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.globalnewsapp.repository.NewsRepository

class NewsViewModelProvider(val newsRepository: NewsRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if(modelClass.isAssignableFrom(NewsViewModel::class.java)){
            return NewsViewModel(newsRepository) as T
        }
        throw IllegalArgumentException("Unknown View Model class")

    }
}
package com.example.globalnewsapp.ui.fragments

import android.os.Bundle
import android.view.View
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.globalnewsapp.R
import com.example.globalnewsapp.ui.NewsActivity
import com.example.globalnewsapp.ui.viewmodel.NewsViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_article.*

class ArticleFragment: Fragment(R.layout.fragment_article) {

    lateinit var viewModels: NewsViewModel
    val args:ArticleFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view,savedInstanceState)
        viewModels = (activity as NewsActivity).viewModels
        val article = args.article
        webView.apply{
            webViewClient = WebViewClient()
            loadUrl(article.url)
        }
        fab.setOnClickListener{
            viewModels.saveArticle(article)
            Snackbar.make(view,"Article successfully saved",Snackbar.LENGTH_SHORT).show()
        }
    }

}
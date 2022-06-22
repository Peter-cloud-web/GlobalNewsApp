package com.example.globalnewsapp.ui.fragments

import android.app.Application
import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.globalnewsapp.R
import com.example.globalnewsapp.databinding.FragmentArticleBinding
import com.example.globalnewsapp.db.ArticleDatabase
import com.example.globalnewsapp.repository.NewsRepository
import com.example.globalnewsapp.ui.NewsActivity
import com.example.globalnewsapp.ui.viewmodel.NewsViewModel
import com.example.globalnewsapp.ui.viewmodel.NewsViewModelProvider
import com.google.android.material.snackbar.Snackbar

class ArticleFragment : Fragment(R.layout.fragment_article) {

    class ArticleFragment : Fragment(R.layout.fragment_article) {

        lateinit var viewModel: NewsViewModel
        val args: ArticleFragmentArgs by navArgs()
        private var fragmentArticleBinding: FragmentArticleBinding? = null

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)

            val binding = FragmentArticleBinding.bind(view)

            fragmentArticleBinding = binding

            val newsRepository = NewsRepository(ArticleDatabase(requireContext() as NewsActivity))
            val viewModelProviderFactory = NewsViewModelProvider(newsRepository)
            viewModel = ViewModelProvider(this, viewModelProviderFactory).get(NewsViewModel::class.java)

            val article = args.article
            binding.webView.apply {
                webViewClient = WebViewClient()
                loadUrl(article.url)

                webViewClient = object : WebViewClient() {
                    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                        super.onPageStarted(view, url, favicon)
                        binding.progressBar.visibility = View.VISIBLE
                    }

                    override fun onPageFinished(view: WebView?, url: String?) {
                        super.onPageFinished(view, url)
                        binding.progressBar.visibility = View.GONE
                    }

                }
            }
            binding.fab.setOnClickListener {
                viewModel.saveArticle(article)
                Snackbar.make(view, "Article successfully saved", Snackbar.LENGTH_SHORT).show()
            }
        }

    }
}
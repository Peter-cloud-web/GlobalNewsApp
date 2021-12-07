package com.example.globalnewsapp.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.globalnewsapp.R
import com.example.globalnewsapp.databinding.FragmentSavedNewsBinding
import com.example.globalnewsapp.db.ArticleDatabase
import com.example.globalnewsapp.repository.NewsRepository
import com.example.globalnewsapp.ui.NewsActivity
import com.example.globalnewsapp.ui.adapters.NewsAdapter
import com.example.globalnewsapp.ui.viewmodel.NewsViewModel
import com.example.globalnewsapp.ui.viewmodel.NewsViewModelProvider

class SavedNewsFragment : Fragment(R.layout.fragment_saved_news) {

    lateinit var viewModel: NewsViewModel
    lateinit var newsAdapter: NewsAdapter
    private var fragmentSavedNewsFragment: FragmentSavedNewsBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var binding = FragmentSavedNewsBinding.bind(view)

        fragmentSavedNewsFragment = binding

        val newsRepository = NewsRepository(ArticleDatabase(requireContext() as NewsActivity))
        val viewModelProviderFactory = NewsViewModelProvider(newsRepository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(NewsViewModel::class.java)

        setUpRecyclerView(binding)

        newsAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("article", it)
            }
            findNavController().navigate(
                R.id.action_savedNewsFragment3_to_articleFragment4,
                bundle
            )
        }

        viewModel.fetchSavedArticles().observe(viewLifecycleOwner, Observer { articles ->
            newsAdapter.differ.submitList(articles)

        })
    }

    private fun setUpRecyclerView(binding: FragmentSavedNewsBinding) {
        newsAdapter = NewsAdapter()
        binding.rvSavedNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}
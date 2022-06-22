package com.example.globalnewsapp.ui

import android.content.Intent
import android.content.Intent.ACTION_VIEW
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.globalnewsapp.R
import com.example.globalnewsapp.databinding.ActivityNewsBinding
import com.example.globalnewsapp.db.ArticleDatabase
import com.example.globalnewsapp.repository.NewsRepository
import com.example.globalnewsapp.ui.viewmodel.NewsViewModel
import com.example.globalnewsapp.ui.viewmodel.NewsViewModelProvider

class NewsActivity : AppCompatActivity() {
    lateinit var toggle: ActionBarDrawerToggle
    val viewModel:NewsViewModel by viewModels{
        NewsViewModelProvider(NewsRepository(ArticleDatabase(this)))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpNavigation(binding)

        toggle = ActionBarDrawerToggle(this, binding.drawerLayout, R.string.open, R.string.close)
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.navMenu.itemIconTintList = null

        binding.navMenu.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.github_repo ->
                    startActivity(
                        Intent(
                            ACTION_VIEW,
                            Uri.parse("https://github.com/Peter-cloud-web/GlobalNewsApp")
                        )
                    )
                R.id.developer ->
                    startActivity(
                        Intent(
                            ACTION_VIEW,
                            Uri.parse("https://t.co/Y6b7mMOtRr?amp=1")
                        )
                    )
            }
            true
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setUpNavigation(binding: ActivityNewsBinding) {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.newsNavHostFragment) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bottomNavigationView.setupWithNavController(navController)

    }
}
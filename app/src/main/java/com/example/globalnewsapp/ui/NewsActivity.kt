package com.example.globalnewsapp.ui

import android.content.Intent
import android.content.Intent.ACTION_VIEW
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.globalnewsapp.R
import com.example.globalnewsapp.db.ArticleDatabase
import com.example.globalnewsapp.repository.NewsRepository
import com.example.globalnewsapp.ui.viewmodel.NewsViewModel
import com.example.globalnewsapp.ui.viewmodel.NewsViewModelProvider
import kotlinx.android.synthetic.main.activity_news.*

class NewsActivity : AppCompatActivity() {

    lateinit var viewModels:NewsViewModel
    lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

        val newsRepository = NewsRepository(ArticleDatabase(this))
        val viewModelProviderFactory = NewsViewModelProvider(application,newsRepository)
        viewModels = ViewModelProvider(this, viewModelProviderFactory).get(NewsViewModel::class.java)

        setUpNavigation()

        toggle = ActionBarDrawerToggle(this,drawer_layout,R.string.open,R.string.close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        navMenu.itemIconTintList = null

        navMenu.setNavigationItemSelectedListener {
            when(it.itemId){
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
        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setUpNavigation(){
        val navHostFragment = getSupportFragmentManager().findFragmentById(R.id.newsNavHostFragment) as NavHostFragment
        val navController= navHostFragment.navController
        bottomNavigationView.setupWithNavController(navController)

    }
}
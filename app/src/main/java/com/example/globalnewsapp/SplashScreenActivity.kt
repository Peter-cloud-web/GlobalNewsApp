package com.example.globalnewsapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.globalnewsapp.databinding.ActivitySplashScreenBinding
import com.example.globalnewsapp.ui.NewsActivity

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.splashIcon.alpha = 0f
        binding.textView3.alpha = 0f

        binding.splashIcon.animate().setDuration(3500).alpha(1f).withEndAction {
            val i = Intent(this, NewsActivity::class.java)
            startActivity(i)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }

        binding.textView3.animate().setDuration(1500).alpha(1f).withEndAction {
            val i = Intent(this, NewsActivity::class.java)
            startActivity(i)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }
    }
}
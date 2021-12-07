package com.example.globalnewsapp.util

import androidx.recyclerview.widget.DiffUtil
import com.example.globalnewsapp.models.Article

class RecyclerViewDiffUtil {

    companion object {
        val differCallBack = object : DiffUtil.ItemCallback<Article>() {
            override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem.url == newItem.url
            }

            override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem == newItem
            }

        }
    }
}
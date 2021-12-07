package com.example.globalnewsapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.globalnewsapp.databinding.ItemArticlePreviewBinding
import com.example.globalnewsapp.models.Article
import com.example.globalnewsapp.util.RecyclerViewDiffUtil


class NewsAdapter : RecyclerView.Adapter<NewsAdapter.MyViewHolder>() {

    inner class MyViewHolder(val binding: ItemArticlePreviewBinding) :
        RecyclerView.ViewHolder(binding.root)

    val differ = AsyncListDiffer(this, RecyclerViewDiffUtil.differCallBack)

    private var onItemClickListener: ((Article) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsAdapter.MyViewHolder {
        val binding =
            ItemArticlePreviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val article = differ.currentList[position]
        with(holder) {
            with(article) {
                binding.apply {
                    Glide.with(itemView)
                        .load(article.urlToImage)
                        .into(ivArticleImage)

                    tvSource.text = article.source.name
                    tvTitle.text = article.title
                    tvDescription.text = article.description
                    tvPublishedAt.text = article.publishedAt

                    itemView.setOnClickListener {
                        onItemClickListener?.let { it(article) }
                    }
                }
            }
        }

    }


    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun setOnItemClickListener(listener: (Article) -> Unit) {
        onItemClickListener = listener
    }
}
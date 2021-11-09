package com.example.globalnewsapp.ui.adapters

import android.view.LayoutInflater
import android.view.OrientationEventListener
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.globalnewsapp.R
import com.example.globalnewsapp.models.Article
import com.example.globalnewsapp.util.RecyclerViewDiffUtil
import kotlinx.android.synthetic.main.item_article_preview.view.*

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.MyViewHolder>() {

    inner class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)

    val differ = AsyncListDiffer(this, RecyclerViewDiffUtil.differCallBack)

    private var onItemClickListener:((Article) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsAdapter.MyViewHolder {
       return MyViewHolder(
           LayoutInflater.from(parent.context).inflate(R.layout.item_article_preview,parent,false)
       )
    }

    override fun onBindViewHolder(holder: NewsAdapter.MyViewHolder, position: Int) {
        val article = differ.currentList[position]
        holder.itemView.apply {
            Glide.with(this)
                .load(article.urlToImage)
                .into(ivArticleImage)

            tvSource.text = article.source.name
            tvTitle.text = article.title
            tvDescription.text = article.description
            tvPublishedAt.text = article.publishedAt

            setOnClickListener{
                onItemClickListener?.let { it(article) }
            }

        }

    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun setOnItemClickListener(listener:(Article) -> Unit){
        onItemClickListener = listener
    }
}
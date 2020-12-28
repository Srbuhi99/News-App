package com.example.newsapp.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.databinding.NewsListItemBinding
import com.example.newsapp.local_db.NewsPost


class NewsListAdapter(private var newsList:ArrayList<NewsPost>):RecyclerView.Adapter<NewsListAdapter.NewsViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<NewsListItemBinding>(inflater,
                R.layout.news_list_item, parent, false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
     holder.binding.model = newsList[position]
    }

    override fun getItemCount(): Int {
    return newsList.size
    }

    class NewsViewHolder(var binding: NewsListItemBinding  ): RecyclerView.ViewHolder(binding.root)
}
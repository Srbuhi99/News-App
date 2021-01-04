package com.example.newsapp.view.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.databinding.NewsListItemBinding
import com.example.newsapp.local_db.NewsPost
import com.example.newsapp.util.ItemClickListener
import kotlinx.android.synthetic.main.news_list_item.view.*


class NewsListAdapter(private var newsList:ArrayList<NewsPost>):RecyclerView.Adapter<NewsListAdapter.NewsViewHolder>()
    ,ItemClickListener {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<NewsListItemBinding>(inflater,
                R.layout.news_list_item, parent, false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
     holder.binding.model = newsList[position]
     holder.binding.listener = this
    }

    override fun getItemCount(): Int {
    return newsList.size
    }

    class NewsViewHolder(var binding: NewsListItemBinding  ): RecyclerView.ViewHolder(binding.root)

    override fun onItemClickListener(view: View) {
        var description = view.description.text.toString()
        var title = view.news_title.text.toString()
        var creator = view.creator.text.toString()
        var bundle = Bundle()
        bundle.putString("desc",description)
        bundle.putString("title",title)
        bundle.putString("creator",creator)
       Navigation.findNavController(view).navigate(R.id.detailNewsFragment,bundle)
    }
}
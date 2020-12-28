package com.example.newsapp.view.fragment

import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.local_db.NewsPost
import com.example.newsapp.view.adapter.NewsListAdapter

open class BaseFragment :Fragment() {

    lateinit var newsListAdapter: NewsListAdapter

    internal fun addVerticalLayManager(view : RecyclerView){
        view.layoutManager = this.let {
            LinearLayoutManager(
                requireContext(), LinearLayoutManager.VERTICAL,
                false
            )
        }
    }

    internal fun initAdapter(list: ArrayList<NewsPost>, view: RecyclerView){
        newsListAdapter = this.let {
           NewsListAdapter(
                list
            )
        }
        view.adapter = newsListAdapter
    }

}
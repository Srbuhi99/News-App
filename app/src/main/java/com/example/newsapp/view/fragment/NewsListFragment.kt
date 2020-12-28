package com.example.newsapp.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.ToggleButton
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.newsapp.R
import com.example.newsapp.databinding.FragmentNewsListBindingImpl
import com.example.newsapp.util.ItemClickListener
import com.example.newsapp.viewmodel.NewsViewModel
import kotlinx.android.synthetic.main.fragment_news_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class NewsListFragment : BaseFragment(),ItemClickListener {

    private val newsviewmodel: NewsViewModel by viewModel()
    lateinit var newsListBindingImpl: FragmentNewsListBindingImpl
    var test = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        newsListBindingImpl = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_news_list, container, false
        )
        return newsListBindingImpl.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        newsListBindingImpl.listener = this
        addVerticalLayManager(news_recview)
        newsviewmodel.refresh()
        observNewData()
    }


    fun observNewData(){
        newsviewmodel.postLiveData.observe(viewLifecycleOwner, Observer {
            initAdapter(it,news_recview)
            test = it[0].title
        })

    }


    override fun onItemClickListener(view: View) {
        view as ToggleButton
//        if(view.isChecked == true){
//            newsviewmodel.loadData(test)
//                newsviewmodel.loadTestData.observe(viewLifecycleOwner, Observer {
//                Toast.makeText(context,it.toString(),Toast.LENGTH_SHORT).show()
//            })
//
//        }

    }

}
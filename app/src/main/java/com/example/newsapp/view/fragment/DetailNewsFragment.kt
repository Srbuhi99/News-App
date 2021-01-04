package com.example.newsapp.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.newsapp.R
import com.example.newsapp.databinding.FragmentDetailNewsBindingImpl
import com.example.newsapp.util.HtmlToStringUtil
import kotlinx.android.synthetic.main.fragment_detail_news.*
import org.jsoup.Jsoup


class DetailNewsFragment : BaseFragment() {

    lateinit var detailNewsBindingImpl: FragmentDetailNewsBindingImpl
    var postTitle = ""
    var postDesc = ""
    var postCreator = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       detailNewsBindingImpl = DataBindingUtil.inflate(
           inflater,
           R.layout.fragment_detail_news, container, false
       )
        return detailNewsBindingImpl.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //get data from bundle
        arguments.apply {
           postCreator = this!!.getString("creator").toString()
           postDesc = this.getString("desc").toString()
           postTitle = this.getString("title").toString()
        }


        // add data to TextView
        post_creator.text = postCreator
        post_description.text = HtmlToStringUtil.getNeedString(postDesc)
        post_title.text = postTitle
    }


}
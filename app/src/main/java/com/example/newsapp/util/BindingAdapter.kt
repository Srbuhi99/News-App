package com.example.newsapp.util

import android.text.format.DateUtils
import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.text.SimpleDateFormat
import java.util.*

class BindingAdapter {


    companion object{

        val DATE_FORMAT = SimpleDateFormat("E, dd MMM yyyy HH:mm:ss z", Locale.ENGLISH)

        @JvmStatic
        @BindingAdapter("app:setdate")
        fun setdate(textview: TextView,text:String) {

            var longTime = DATE_FORMAT.parse(text)?.time ?: System.currentTimeMillis()

            textview.text = DateUtils.getRelativeTimeSpanString(
                longTime, System.currentTimeMillis(),
                0
            )
        }
    }
}
package com.example.newsapp.util

import org.jsoup.Jsoup

class HtmlToStringUtil {

    companion object{

        fun  getNeedString(string: String): String{
          var needString = htmltotext(string)!!.split("Ч")

            return needString[0]
        }

        fun htmltotext(html: String?): String? {
            return Jsoup.parse(html).text()
        }
    }
}
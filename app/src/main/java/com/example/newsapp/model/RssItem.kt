package com.example.newsapp.model

import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "item", strict = false)
data class RssItem(


    @field:Element
    var title: String = "",

    @field:Element
    var description: String = "",

    @field:Element
    var link: String = "",

    @field:Element
    var pubDate: String = "",

    @field:Element(name = "creator")
    var creator: String = ""

)

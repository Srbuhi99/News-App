package com.example.newsapp.model

import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "channel", strict = false)
data class RssChannel(

    @field:Element
    var title: String = "",

    @field:Element
    var link: String = "",

    @field:Element
    var image: RssImage = RssImage(),

    @field:ElementList(entry = "item", inline = true, required = false)
    var items: MutableList<RssItem> = mutableListOf()
)
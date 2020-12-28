package com.example.newsapp.model

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "image", strict = false)
data class RssImage(

        @field:Element
        var url: String = "",

    @field:Element
    var title: String = "",

    @field:Element
    var link: String = ""
)

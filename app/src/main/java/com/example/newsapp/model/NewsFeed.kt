package com.example.newsapp.model

import com.example.newsapp.local_db.NewsPost
import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "rss", strict = false)
data class NewsFeed(

        @field:Element
        var channel: RssChannel = RssChannel()
)

    fun NewsFeed.convertToHabrFeed(): HabrFeed {
        return HabrFeed(
                HabrChannel(
                        channel.title,
                        channel.link,
                        channel.image.url,
                        channel.image.title,
                        channel.image.link
                ),
                channel.items.map { rssItem ->
                    NewsPost(
                        rssItem.title,
                        rssItem.description,
                        rssItem.link,
                        rssItem.pubDate,
                        rssItem.creator
                    )
                }.toMutableList()
        )
    }


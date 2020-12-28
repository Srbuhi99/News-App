package com.example.newsapp.model

import android.os.Parcelable
import com.example.newsapp.local_db.NewsPost
import kotlinx.android.parcel.Parcelize

@Parcelize
data class HabrFeed(
    var channel: HabrChannel = HabrChannel(),
    var posts: MutableList<NewsPost> = mutableListOf()
) : Parcelable

package com.example.newsapp.model

import android.os.Parcelable
import android.webkit.URLUtil
import kotlinx.android.parcel.Parcelize

@Parcelize
data class HabrChannel(
        var title: String = "",
        var link: String = "",
        var imageTitle: String = "",
        var imageUrl: String = "",
        var imageLink: String = ""
) : Parcelable {

    fun isValid(): Boolean {
        return URLUtil.isValidUrl(link)
    }
}

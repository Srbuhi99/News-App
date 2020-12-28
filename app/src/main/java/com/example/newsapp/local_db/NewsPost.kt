package com.example.newsapp.local_db

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class NewsPost(

    val title: String = "",
    val description: String = "",
    val link: String = "",
    val pubDate: String = "",
    val creator: String = ""
): Parcelable {
    @PrimaryKey(autoGenerate = true)
    var uuid: Int = 0
}

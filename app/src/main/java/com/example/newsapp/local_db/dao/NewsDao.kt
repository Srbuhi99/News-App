package com.example.newsapp.local_db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.newsapp.local_db.NewsPost

@Dao
interface NewsDao {

    @Query("SELECT * FROM  NewsPost")
    suspend fun getAllFavoriteList(): List<NewsPost>

    @Query("SELECT * FROM NewsPost WHERE  title=:title")
    suspend fun loadData(title: String): NewsPost

    @Query("DELETE FROM NewsPost")
    suspend fun deleteAllNews()

    @Insert
    suspend fun insert(vararg post: NewsPost)

    @Delete
    suspend fun delete(post: NewsPost)

    @Update
    suspend fun update(vararg post: NewsPost)

    @Insert
    suspend fun insertList(postList: List<NewsPost>)
}
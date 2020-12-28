package com.example.newsapp.api


import com.example.newsapp.model.NewsFeed
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("all/all")
    suspend fun getNews(): Response<NewsFeed>

}
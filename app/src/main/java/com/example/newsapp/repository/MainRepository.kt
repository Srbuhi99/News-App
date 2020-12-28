package com.example.newsapp.repository

import android.app.Application
import com.example.newsapp.util.Result
import com.example.newsapp.api.ApiService
import com.example.newsapp.api.RetrofitBuilder
import com.example.newsapp.local_db.NewsPost
import com.example.newsapp.local_db.dao.NewsDao
import com.example.newsapp.local_db.database.AppDatabase
import com.example.newsapp.model.HabrFeed
import com.example.newsapp.model.convertToHabrFeed
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.java.KoinJavaComponent.inject
import kotlin.coroutines.CoroutineContext


class MainRepository( var application: Application) : BaseRepository(), CoroutineScope{

    private val api: ApiService = RetrofitBuilder.apiService
    val mDao: NewsDao

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main


    init {
        mDao = AppDatabase.invoke(application).newsDao()
    }

    override suspend fun getData(): List<NewsPost> =
       withContext(Dispatchers.IO){
            mDao.getAllFavoriteList()
        }


    override suspend fun getData(title: String): NewsPost =
        withContext(Dispatchers.IO){
            mDao.loadData(title)
        }




    fun insertFavoriteList(favoriteList: List<NewsPost>) {
        launch {
            insertFavoriteListSuspend(favoriteList)
        }
    }

    fun deleteFavorite(post:NewsPost){
        launch {
            deleteFavoriteSuspend(post)
        }
    }

    fun insert(post:NewsPost){
        launch {
            insertFavourteSuspend(post)
        }
    }

    fun deleteAllNews(){
        launch {
            mDao.deleteAllNews()
        }
    }


    private suspend fun insertFavoriteListSuspend(postList: List<NewsPost>) {
        withContext(Dispatchers.IO) {
            mDao.insertList(postList)
        }
    }

    private suspend fun deleteFavoriteSuspend(post: NewsPost) {
        withContext(Dispatchers.IO){
            mDao.delete(post)
        }
    }

    private suspend fun insertFavourteSuspend(post: NewsPost) {
        withContext(Dispatchers.IO) {
            mDao.insert(post)
        }
    }


    suspend fun getAllNews(): Result<HabrFeed> {
        return safeApiCall(converter = {it.convertToHabrFeed()}) {
            api.getNews()
        }
    }

}
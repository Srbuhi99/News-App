package com.example.newsapp.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.newsapp.local_db.NewsPost
import com.example.newsapp.model.HabrFeed
import com.example.newsapp.repository.MainRepository
import com.example.newsapp.util.Result
import com.example.newsapp.util.SharedPreferencesHelper
import kotlinx.coroutines.launch
import org.koin.core.component.inject
import org.koin.java.KoinJavaComponent.inject
import java.util.*
import kotlin.collections.ArrayList

class NewsViewModel(application: Application,var  repo: MainRepository): AndroidViewModel(application){

    private var prefHelper = SharedPreferencesHelper(getApplication())
    private var refreshTime = 5 * 60 * 1000 * 1000 * 1000L



    var postLiveData: MutableLiveData<ArrayList<NewsPost>> = MutableLiveData()
    var loadTestData: MutableLiveData<NewsPost> = MutableLiveData()


   init {
       viewModelScope.launch {
         repo = MainRepository(application)
       }
   }

    fun refresh() {
        checkCacheDuration()
        val updateTime = prefHelper.getUpdateTime()
        if(updateTime != null && updateTime != 0L && System.nanoTime() - updateTime < refreshTime) {
           fetchFromDatabase()
        } else {
            fetchFromRemote()
        }
    }

    private fun fetchFromDatabase() {
        viewModelScope.launch {
            val news = repo!!.getData()
            newsRetrieved(news as ArrayList<NewsPost>)
        }
    }

    private fun checkCacheDuration() {
        val cachePreference = prefHelper.getCacheDuration()

        try {
            val cachePreferenceInt = cachePreference?.toInt() ?: 5 * 60
            refreshTime = cachePreferenceInt.times(1000 * 1000 * 1000L)
        } catch (e: NumberFormatException) {
            e.printStackTrace()
        }
    }


    fun fetchFromRemote() {
     viewModelScope.launch {
        funstoreNewsLocally(repo.getAllNews())

     }
    }

   private fun  funstoreNewsLocally(result: Result<HabrFeed>){
       repo.deleteAllNews()
               when (result) {
                   is Result.Success -> {
                       repo.insertFavoriteList(result.data.posts)
                       newsRetrieved(result.data.posts as ArrayList<NewsPost>)
                   }
   }

   }

    fun newsRetrieved(result: ArrayList<NewsPost>)   {

        postLiveData.value = result as ArrayList<NewsPost>

        prefHelper.saveUpdateTime(System.nanoTime())
        //Singleton.saved = true
    }


    fun getdata(){
        viewModelScope.launch {
            postLiveData.value = repo!!.getData() as ArrayList<NewsPost>
        }
    }

    fun loadData(title: String){
        viewModelScope.launch {
            loadTestData.value = repo!!.getData(title)
        }
    }


    fun gettestLiveData() : MutableLiveData<ArrayList<NewsPost>>?{
      return postLiveData
    }


}
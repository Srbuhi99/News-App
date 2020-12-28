package com.example.newsapp.sync

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.newsapp.repository.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SyncWorker(var context: Context,
                 params: WorkerParameters
) : CoroutineWorker(context,params),KoinComponent {

    val mainRepository:MainRepository by inject ()

    override suspend fun doWork(): Result {
        withContext(Dispatchers.IO) {
            NotificationsHelper(applicationContext).createNofitication()
         var result = mainRepository.getAllNews()
         // mainRepository.insertFavourteSuspend()
        }
        return Result.success()
    }
}
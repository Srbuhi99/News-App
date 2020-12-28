package com.example.newsapp.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.work.*
import com.example.newsapp.R
import com.example.newsapp.sync.SyncWorker
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //init navController
        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        navController.navigate(R.id.newsListFragment)
        startWorkManager()

    }

    private fun startWorkManager() {
        val myConstraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .setRequiresStorageNotLow(true)
            .build()
        val syncWork = PeriodicWorkRequest
            .Builder(SyncWorker::class.java, 15, TimeUnit.MINUTES)
            .setConstraints(myConstraints)
            .build()
       var k =  WorkManager.getInstance()
            .enqueueUniquePeriodicWork(SyncWorker::class.java.name, ExistingPeriodicWorkPolicy.KEEP, syncWork)
    }


}
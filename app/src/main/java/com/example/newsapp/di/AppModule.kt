package com.example.newsapp.di

import com.example.newsapp.repository.MainRepository
import com.example.newsapp.viewmodel.NewsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object AppModule {
    val appModule = module {

        single { MainRepository(get()) }

        viewModel { NewsViewModel(get(),get()) }

    }
}
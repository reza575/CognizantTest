package com.moeiny.reza.cognizanttest

import android.app.Application
import com.moeiny.reza.cognizanttest.data.inforepository.InfoRepository
import com.moeiny.reza.cognizanttest.data.inforepository.InfoRepositoryDefault
import com.moeiny.reza.cognizanttest.data.retrofit.ApiService

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AndroidApplication : Application() {

    val infoRepository: InfoRepository by lazy {
        InfoRepositoryDefault(apiService)
    }

    private val apiService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://dl.dropboxusercontent.com/s/2iodh4vg0eortkl/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    override fun onCreate() {
        super.onCreate()
    }
}
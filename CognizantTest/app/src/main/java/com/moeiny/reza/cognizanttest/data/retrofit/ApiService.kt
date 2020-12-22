package com.moeiny.reza.cognizanttest.data.retrofit


import com.moeiny.reza.cognizanttest.data.model.apimodel.Info
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("facts.json")
    // Communicates responses from a server are executed on the background thread which performed the request
    fun getInfo(): Call<Info>
}
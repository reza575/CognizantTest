package com.moeiny.reza.cognizanttest.data.inforepository

import com.moeiny.reza.cognizanttest.core.result.Result
import com.moeiny.reza.cognizanttest.data.model.apimodel.Info
import com.moeiny.reza.cognizanttest.data.retrofit.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Class NewsRepositoryDefault trying to  receive data from Api nd this class has 3 state:
 * 1- Loading : When data started to load from API until loading completed.
 * 2- Response: Data has loaded completely without any error. this data deliver to App in this state.
 * 3- error: if any error happen during loading data this error will be handel by any predefined decision.
 */

class InfoRepositoryDefault(private val apiService: ApiService) : InfoRepository{
    override fun fetchNews( onResult: (result: Result<Info>) -> Unit) {
        //TODO
        onResult(Result.Loading)
        //TODO
        apiService.getInfo().enqueue(object :Callback<Info>{
            override fun onFailure(call: Call<Info>, t: Throwable) {
                onResult(Result.Error(t))
            }

            override fun onResponse(call: Call<Info>, response: Response<Info>) {
                response.body()?.let {
                    onResult(Result.Success(it))
                }
            }
        })
    }
}

interface InfoRepository {
    fun fetchNews(onResult: (result: Result<Info>) -> Unit)
}



package com.polish.postwebservice.service

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceBuilder {

    // the url
    private const val URL = "https://jsonplaceholder.typicode.com/"

    // create okHttp client
    private val okHttp = OkHttpClient.Builder()

    // create a Retrofit builder
    private val builder = Retrofit.Builder()
        .baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttp.build())

    // create a retrofit instance
    private val retrofit = builder.build()

    // create the api (i.e retrofit.create(the api class(It's the interface that comes here)))
    fun <T> builderService(serviceType:Class<T>):T{
        return retrofit.create(serviceType)
    }


}
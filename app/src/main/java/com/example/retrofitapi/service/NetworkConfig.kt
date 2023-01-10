package com.example.retrofitapi.service

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class NetworkConfig {

    fun getInterceptor(): OkHttpClient{
        var logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder().addInterceptor(logging).build()

        return okHttpClient
    }

    fun getRetrofit(): Retrofit{
        return Retrofit.Builder()
            .baseUrl("http://www.omdbapi.com")
            .client(getInterceptor())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getServiceOMDB() = getRetrofit().create(OMDBApiInterface::class.java)
}
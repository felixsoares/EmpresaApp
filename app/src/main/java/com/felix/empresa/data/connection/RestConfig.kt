package com.felix.empresa.data.connection

import com.felix.empresa.data.database.LocalStorage
import com.felix.empresa.util.Constants
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RestConfig {

    fun get(localStorage: LocalStorage): API {
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor { chain ->
            val original = chain.request()
            val originalHttpUrl = original.url()

            val url = originalHttpUrl.newBuilder().build()
            val requestBuilder = original.newBuilder()
                .addHeader(
                    "access-token",
                    localStorage.load(Constants.ACCESS_TOKEN_KEY, Constants.EMPTY_KEY)
                )
                .addHeader(
                    "client",
                    localStorage.load(Constants.CLIENT_KEY, Constants.EMPTY_KEY)
                )
                .addHeader(
                    "uid",
                    localStorage.load(Constants.UID_KEY, Constants.EMPTY_KEY)
                )
                .url(url)

            val request = requestBuilder.build()
            chain.proceed(request)
        }

        val retrofit = Retrofit.Builder()
            //.baseUrl("https://empresasapi.herokuapp.com/api/v1/")
            .baseUrl("http://192.168.1.101:3000/api/v1/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .client(httpClient.build())
            .build()

        return retrofit.create(API::class.java)
    }
}
package com.nepplus.practiceproject.network

import com.google.gson.Gson
import com.nepplus.practiceproject.BuildConfig
import com.nepplus.practiceproject.remotedatasource.GithubAPI
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class Client(private val gSon : Gson){

    val client : Retrofit = createClient()
    val githubAPI : GithubAPI = createClient(USER_BASE_URL).create(GithubAPI::class.java)


    private val httpLogLevel
        get() = if(BuildConfig.DEBUG) HttpCustomLoggingInterceptor.Level.BODY else HttpCustomLoggingInterceptor.Level.NONE

    fun createClient(host : String = USER_BASE_URL) : Retrofit{

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(HttpCustomLoggingInterceptor().apply { level = httpLogLevel })
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .build()

        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gSon))
            .client(okHttpClient)
            .baseUrl(host)
            .build()

    }



    companion object{
        private const val CONNECT_TIMEOUT = 10L
        private const val WRITE_TIMEOUT = 10L
        private const val READ_TIMEOUT = 10L

        private const val USER_BASE_URL = "https://api.github.com/"
    }
}
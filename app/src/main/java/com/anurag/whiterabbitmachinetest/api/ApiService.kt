package com.anurag.whiterabbitmachinetest.api

import com.anurag.whiterabbitmachinetest.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiService {

    private const val BASE_URL = "https://www.mocky.io/"

    fun getApiMethods(): ApiMethods {

        return getRetrofitInstance().create(ApiMethods::class.java)
    }

    private fun getRetrofitInstance(): Retrofit {

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(getOkhttpClient())
            .build()

    }

    private fun getOkhttpClient(): OkHttpClient {

        return if (BuildConfig.DEBUG){

            OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .addNetworkInterceptor(getHttpLoggingInterceptor())
                .build()
        }else {

            OkHttpClient.Builder()
                .connectTimeout(1500, TimeUnit.SECONDS)
                .readTimeout(1500, TimeUnit.SECONDS)
                .build()
        }
    }

    private fun getHttpLoggingInterceptor(): HttpLoggingInterceptor {

        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }
}
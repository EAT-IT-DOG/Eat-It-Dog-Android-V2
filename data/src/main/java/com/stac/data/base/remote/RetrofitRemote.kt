package com.stac.data.base.remote

import com.stac.data.network.url.DogUrl
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.Executors

abstract class RetrofitRemote<SV> : BaseRemote<SV>() {
    protected fun <T> createApi(service: Class<T>): T {
        return RETROFIT.create(service)
    }

    private val RETROFIT: Retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl(DogUrl.SERVER_HOST)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create()))
        .addConverterFactory(ScalarsConverterFactory.create())
        .callbackExecutor(Executors.newSingleThreadExecutor())
        .build()

    private val client: OkHttpClient
        get() {
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            val okHttpBuilder = OkHttpClient().newBuilder().addInterceptor(interceptor)
            return okHttpBuilder.build()
        }
}
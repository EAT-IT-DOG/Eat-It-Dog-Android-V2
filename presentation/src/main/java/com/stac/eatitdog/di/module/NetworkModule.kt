package com.stac.eatitdog.di.module

import com.stac.data.network.interceptor.TokenInterceptor
import com.stac.data.network.url.DogUrl
import com.stac.eatitdog.di.OtherOkHttpClient
import com.stac.eatitdog.di.OtherRemoteRetrofit
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.Executors
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideGson(): Gson {
        val gsonBuilder = GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
        return gsonBuilder.create()
    }

    @OtherOkHttpClient
    @Provides
    @Singleton
    fun provideHttpClient(tokenInterceptor: TokenInterceptor): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val okhttpBuilder = OkHttpClient().newBuilder()
            .addInterceptor(interceptor)
            //.addInterceptor(tokenInterceptor)
        return okhttpBuilder.build()
    }

    @OtherRemoteRetrofit
    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson, @OtherOkHttpClient okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(DogUrl.SERVER_HOST)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .callbackExecutor(Executors.newSingleThreadExecutor())
            .build()
    }
}
package com.eatitdog.eatitdog.di.module

import com.eatitdog.data.network.api.SearchApi
import com.eatitdog.data.network.remote.SearchRemote
import com.eatitdog.eatitdog.di.OtherRemoteRetrofit
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteModule {
    @Singleton
    @Provides
    fun provideMealRemote(@OtherRemoteRetrofit retrofit: Retrofit): SearchRemote =
        SearchRemote(retrofit.create(SearchApi::class.java))

}
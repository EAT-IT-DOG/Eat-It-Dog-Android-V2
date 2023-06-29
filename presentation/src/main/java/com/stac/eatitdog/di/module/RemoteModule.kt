package com.stac.eatitdog.di.module

import com.stac.data.network.api.SearchApi
import com.stac.data.network.remote.SearchRemote
import com.stac.eatitdog.di.OtherRemoteRetrofit
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
    fun provideSearchRemote(@OtherRemoteRetrofit retrofit: Retrofit): SearchRemote =
        SearchRemote(retrofit.create(SearchApi::class.java))

}
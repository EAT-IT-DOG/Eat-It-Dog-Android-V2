package com.stac.eatitdog.di.module

import com.stac.data.repository.AuthRepositoryImpl
import com.stac.data.repository.SearchRepositoryImpl
import com.stac.domain.repository.AuthRepository
import com.stac.domain.repository.SearchRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Singleton
    @Provides
    fun provideSearchRepository(searchRepositoryImpl: SearchRepositoryImpl): SearchRepository = searchRepositoryImpl

    @Singleton
    @Provides
    fun provideAuthRepository(authRepositoryImpl: AuthRepositoryImpl): AuthRepository = authRepositoryImpl
}
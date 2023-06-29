package com.stac.eatitdog.di.module

import com.stac.domain.repository.SearchRepository
import com.stac.domain.usecases.search.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {
    @Provides
    @Singleton
    fun provideSearchUseCases(repository: SearchRepository): SearchUseCases =
        SearchUseCases(
            getResultByCategory = GetResultByCategory(repository),
            getResultAll = GetResultAll(repository),
            getResultByName = GetResultByName(repository),
            getResult = GetResult(repository)
        )
}
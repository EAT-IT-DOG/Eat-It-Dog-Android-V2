package com.stac.eatitdog.di.module

import com.stac.domain.repository.SearchRepository
import com.stac.domain.usecases.search.GetResult
import com.stac.domain.usecases.search.GetResultByCategory
import com.stac.domain.usecases.search.GetResultByName
import com.stac.domain.usecases.search.SearchUseCases
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
            getResult = GetResult(repository),
            getResultByName = GetResultByName(repository)
        )
}
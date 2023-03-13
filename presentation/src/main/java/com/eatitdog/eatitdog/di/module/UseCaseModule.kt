package com.eatitdog.eatitdog.di.module

import com.eatitdog.domain.repository.SearchRepository
import com.eatitdog.domain.usecases.search.GetResult
import com.eatitdog.domain.usecases.search.GetResultByCategory
import com.eatitdog.domain.usecases.search.GetResultByName
import com.eatitdog.domain.usecases.search.SearchUseCases
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
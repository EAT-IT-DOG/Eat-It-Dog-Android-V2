package com.stac.eatitdog.di.module

import com.stac.domain.repository.AuthRepository
import com.stac.domain.repository.SearchRepository
import com.stac.domain.usecases.auth.AuthUseCases
import com.stac.domain.usecases.auth.Join
import com.stac.domain.usecases.auth.Login
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

    @Provides
    @Singleton
    fun provideAuthUseCases(repository: AuthRepository): AuthUseCases =
        AuthUseCases(
            join = Join(repository),
            login = Login(repository)
        )
}
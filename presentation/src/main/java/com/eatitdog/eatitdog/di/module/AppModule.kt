package com.eatitdog.eatitdog.di.module

import android.app.Application
import android.content.Context
import com.eatitdog.data.util.AppDispatchers
import com.eatitdog.data.util.AppDispatchersImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Singleton
    @Provides
    fun bindContext(application: Application): Context = application

    @Singleton
    @Provides
    fun bindAny(): Any = Any()

    @Singleton
    @Provides
    fun appDispatchers(impl: AppDispatchersImpl): AppDispatchers = impl
}
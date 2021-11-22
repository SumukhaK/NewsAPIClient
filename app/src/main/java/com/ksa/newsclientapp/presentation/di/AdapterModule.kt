package com.ksa.newsclientapp.presentation.di

import com.ksa.newsclientapp.presentation.adapter.NewsheadlinesAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AdapterModule {

    @Singleton
    @Provides
    fun provideNewsAdapter():NewsheadlinesAdapter{
        return NewsheadlinesAdapter()
    }
}
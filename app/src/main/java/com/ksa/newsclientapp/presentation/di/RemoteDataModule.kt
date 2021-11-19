package com.ksa.newsclientapp.presentation.di

import com.ksa.newsclientapp.data.api.NewsAPIService
import com.ksa.newsclientapp.data.repository.NewsRemoteDataSource
import com.ksa.newsclientapp.data.repository.NewsRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteDataModule {

    @Singleton
    @Provides
    fun provideNewsRemoteDataSource(newsAPIService: NewsAPIService):NewsRemoteDataSource{

        return NewsRemoteDataSourceImpl(newsAPIService)
    }

}
package com.ksa.newsclientapp.presentation.di


import com.ksa.newsclientapp.data.repository.NewsRemoteDataSource
import com.ksa.newsclientapp.data.repository.NewsRepositoryImpl
import com.ksa.newsclientapp.domain.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideNewsRepository(newsRemoteDataSource: NewsRemoteDataSource): NewsRepository {
        return  NewsRepositoryImpl(newsRemoteDataSource)
    }
}
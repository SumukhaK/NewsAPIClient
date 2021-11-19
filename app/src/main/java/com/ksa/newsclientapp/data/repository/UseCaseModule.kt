package com.ksa.newsclientapp.data.repository

import com.ksa.newsclientapp.domain.repository.NewsRepository
import com.ksa.newsclientapp.domain.usecase.GetNewsHeadlinesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import javax.inject.Singleton


@Module
@InstallIn(Singleton::class)
class UseCaseModule {

    @Provides
    fun providesHeadLinesUseCase(newsRepository: NewsRepository):GetNewsHeadlinesUseCase{
        return GetNewsHeadlinesUseCase(newsRepository)
    }
}
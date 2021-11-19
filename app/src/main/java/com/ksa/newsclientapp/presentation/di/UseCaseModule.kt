package com.ksa.newsclientapp.presentation.di

import com.ksa.newsclientapp.domain.repository.NewsRepository
import com.ksa.newsclientapp.domain.usecase.GetNewsHeadlinesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {
   @Singleton
   @Provides
   fun provideGetNewsheadLinesUseCase(
       newsRepository: NewsRepository
   ): GetNewsHeadlinesUseCase {
      return GetNewsHeadlinesUseCase(newsRepository)
   }
}



















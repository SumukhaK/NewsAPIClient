package com.ksa.newsclientapp.presentation.di

import android.app.Application
import com.ksa.newsclientapp.domain.usecase.GetNewsHeadlinesUseCase
import com.ksa.newsclientapp.presentation.viewmodel.NewsViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class FactoryModule {

    @Singleton
    @Provides
    fun providesFactoryModule(app: Application,getNewsHeadlinesUseCase: GetNewsHeadlinesUseCase):NewsViewModelFactory{
        return NewsViewModelFactory(app, getNewsHeadlinesUseCase)
    }
}
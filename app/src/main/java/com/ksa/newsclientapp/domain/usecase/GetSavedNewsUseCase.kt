package com.ksa.newsclientapp.domain.usecase

import com.ksa.newsclientapp.data.model.Article
import com.ksa.newsclientapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetSavedNewsUseCase(private val newsRepository: NewsRepository) {

     fun execute():Flow<List<Article>>{
         return newsRepository.getAllSavedNews()
     }
}
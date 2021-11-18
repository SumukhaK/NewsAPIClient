package com.ksa.newsclientapp.domain.usecase

import com.ksa.newsclientapp.data.model.Article
import com.ksa.newsclientapp.domain.repository.NewsRepository

class DeleteSavedNewsUseCase(private val newsRepository: NewsRepository) {

    suspend fun execute(article: Article)= newsRepository.deleteNewsLocally(article)
}
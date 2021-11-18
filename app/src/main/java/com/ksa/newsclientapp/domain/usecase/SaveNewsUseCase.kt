package com.ksa.newsclientapp.domain.usecase

import com.ksa.newsclientapp.data.model.Article
import com.ksa.newsclientapp.domain.repository.NewsRepository

class SaveNewsUseCase(private val newsRepository: NewsRepository) {

    suspend fun execute(article: Article) = newsRepository.saveNewsLocally(article)

}
package com.ksa.newsclientapp.domain.usecase

import com.ksa.newsclientapp.data.model.APIResponse
import com.ksa.newsclientapp.data.util.Resource
import com.ksa.newsclientapp.domain.repository.NewsRepository

class GetSearchedNewsUseCase(private val newsRepository: NewsRepository) {

    suspend fun execute(searchQuery:String):Resource<APIResponse>{
        return newsRepository.getSearchedNews(searchQuery)
    }
}
package com.ksa.newsclientapp.domain.usecase

import com.ksa.newsclientapp.data.model.APIResponse
import com.ksa.newsclientapp.data.util.Resource
import com.ksa.newsclientapp.domain.repository.NewsRepository

class GetNewsHeadlinesUseCase(private val newsRepository: NewsRepository) {

suspend fun execute(country:String,page:Int):Resource<APIResponse>{

    return newsRepository.getNewsHeadlines(country ,page )
}
}
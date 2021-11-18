package com.ksa.newsclientapp.data.repository

import com.ksa.newsclientapp.data.api.NewsAPIService
import com.ksa.newsclientapp.data.model.APIResponse
import retrofit2.Response

class NewsRemoteDataSourceImpl(private val newsAPIService: NewsAPIService,
                               private val country:String,
                               private val page:Int):NewsRemoteDataSource {

    override suspend fun getTopHeadlines(): Response<APIResponse> {

        return newsAPIService.getTopHeadlines(country, page)
    }

}
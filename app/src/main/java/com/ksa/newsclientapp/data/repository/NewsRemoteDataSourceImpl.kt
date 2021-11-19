package com.ksa.newsclientapp.data.repository

import com.ksa.newsclientapp.data.api.NewsAPIService
import com.ksa.newsclientapp.data.model.APIResponse
import retrofit2.Response

class NewsRemoteDataSourceImpl(private val newsAPIService: NewsAPIService,
                              ):NewsRemoteDataSource {

    override suspend fun getTopHeadlines(country:String,page:Int): Response<APIResponse> {

        return newsAPIService.getTopHeadlines(country, page)
    }

}
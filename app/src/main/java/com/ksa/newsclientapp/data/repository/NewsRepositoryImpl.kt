package com.ksa.newsclientapp.data.repository

import com.ksa.newsclientapp.data.model.APIResponse
import com.ksa.newsclientapp.data.model.Article
import com.ksa.newsclientapp.data.util.Resource
import com.ksa.newsclientapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class NewsRepositoryImpl(private val newsRemoteDataSource: NewsRemoteDataSource):NewsRepository {

    override suspend fun getNewsHeadlines(country:String,page:Int): Resource<APIResponse> {
        return responseToResource(newsRemoteDataSource.getTopHeadlines(country, page))
    }

    override suspend fun getSearchedNews(searchQuery: String): Resource<APIResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun saveNewsLocally(article: Article) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteNewsLocally(article: Article) {
        TODO("Not yet implemented")
    }

    override fun getAllSavedNews(): Flow<List<Article>> {
        TODO("Not yet implemented")
    }

    private fun responseToResource(response: Response<APIResponse>):Resource<APIResponse>{

        if(response.isSuccessful){
            response.body()?.let {
                return  Resource.Success(it)
            }
        }

        return Resource.Error(response.message())
    }

}
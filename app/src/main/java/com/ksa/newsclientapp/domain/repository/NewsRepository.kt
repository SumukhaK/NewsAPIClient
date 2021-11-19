package com.ksa.newsclientapp.domain.repository

import com.ksa.newsclientapp.data.model.APIResponse
import com.ksa.newsclientapp.data.model.Article
import com.ksa.newsclientapp.data.util.Resource
import kotlinx.coroutines.flow.Flow


interface NewsRepository {

    suspend fun getNewsHeadlines(country:String,page:Int):Resource<APIResponse>
    suspend fun getSearchedNews(searchQuery:String):Resource<APIResponse>
    suspend fun saveNewsLocally(article:Article)
    suspend fun deleteNewsLocally(article: Article)
    fun getAllSavedNews(): Flow<List<Article>>
}
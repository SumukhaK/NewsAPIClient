package com.ksa.newsclientapp.data.repository


import com.ksa.newsclientapp.data.model.APIResponse
import retrofit2.Response

interface NewsRemoteDataSource {

    suspend fun getTopHeadlines(country:String,page:Int):Response<APIResponse>
}
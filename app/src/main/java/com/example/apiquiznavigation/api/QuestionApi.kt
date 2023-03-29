package com.example.apiquiznavigation.api

import com.example.apiquiznavigation.models.QuestionModelItem
import com.example.apiquiznavigation.models.QuizCat
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface QuestionApi {

    companion object{
        const val BASE_URL = "https://the-trivia-api.com/api/"
    }

    @GET("questions?limit=5")
    suspend fun getQuestionCat(
        @Query("categories") categories:String
    ): Response<List<QuestionModelItem>>

    @GET("categories")
    fun getCategories(): Call<QuizCat>

}
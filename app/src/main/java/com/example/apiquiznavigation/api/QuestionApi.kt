package com.example.apiquiznavigation.api

import com.example.apiquiznavigation.models.QuestionModelItem
import com.example.apiquiznavigation.models.QuizCat
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Inject

interface QuestionApi {

    companion object{
        const val BASE_URL = "https://the-trivia-api.com/api/"
    }

    @GET("https://the-trivia-api.com/api/questions?limit=5")
    suspend fun getQuestionCat(
        @Query("categories") categories:String
    ): Response<List<QuestionModelItem>>

    @GET("https://the-trivia-api.com/api/categories")
    fun getCategories(): Call<QuizCat>

}
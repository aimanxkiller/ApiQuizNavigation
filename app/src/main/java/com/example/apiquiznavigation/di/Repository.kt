package com.example.apiquiznavigation.di

import com.example.apiquiznavigation.api.QuestionApi
import com.example.apiquiznavigation.models.QuestionModelItem
import retrofit2.await
import javax.inject.Inject

class Repository @Inject constructor(
    private val api:QuestionApi
){
    suspend fun getQuestions(selection:String): List<QuestionModelItem>? {
        val response = api.getQuestionCat(selection)
        if(response.isSuccessful){
            return response.body()
        }else{
            throw Exception("API CALLING FAILED")
        }
    }

    suspend fun getCategories(): Map<String, ArrayList<String>> {
        val response = api.getCategories()
        return response.await()
    }
}
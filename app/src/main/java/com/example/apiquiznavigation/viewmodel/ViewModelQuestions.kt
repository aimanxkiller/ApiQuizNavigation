package com.example.apiquiznavigation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.apiquiznavigation.api.QuestionApi
import com.example.apiquiznavigation.models.QuestionModelItem
import com.example.apiquiznavigation.models.QuizCat
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class ViewModelQuestions @Inject constructor(
    private val api:QuestionApi
):ViewModel() {

    var countQ: IntArray? = null
    var scoreQ: IntArray? = null
    var selection:String? = null

    suspend fun getQuestions(): Response<List<QuestionModelItem>> {
        return api.getQuestionCat(this.selection!!)
    }

    fun getCategories(): Call<QuizCat> {
        return api.getCategories()
    }

    fun setPick(x:String){
        this.selection = x
    }

}
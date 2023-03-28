package com.example.apiquiznavigation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.apiquiznavigation.api.QuestionApi
import com.example.apiquiznavigation.models.QuestionModelItem
import com.example.apiquiznavigation.models.QuizCat
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response
import retrofit2.awaitResponse
import javax.inject.Inject

@HiltViewModel
class ViewModelQuestions @Inject constructor(
    private val api:QuestionApi
):ViewModel() {

    val categories: QuizCat? = null

    init {
        viewModelScope.launch {
            val categories = api.getCategories().awaitResponse().body()
        }
    }


    suspend fun getQuestions(selection:String): Response<List<QuestionModelItem>> {
        return api.getQuestionCat(selection)
    }

    fun getCategories(): Call<QuizCat> {
        return api.getCategories()
    }

}
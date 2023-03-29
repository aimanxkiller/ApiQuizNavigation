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

    fun getPick(): String? {
        return selection
    }
}
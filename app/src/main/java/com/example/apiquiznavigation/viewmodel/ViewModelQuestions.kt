package com.example.apiquiznavigation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apiquiznavigation.api.QuestionApi
import com.example.apiquiznavigation.di.Repository
import com.example.apiquiznavigation.models.QuestionModelItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class ViewModelQuestions @Inject constructor(
    private val api:QuestionApi,
    private val repo:Repository
):ViewModel() {

    var countQ: IntArray? = null
    var scoreQ: IntArray? = null
    var selection:String? = null

    suspend fun getQuestions(): Response<List<QuestionModelItem>> {
        return api.getQuestionCat(this.selection!!)
    }

    fun getCategories(): Call<Map<String,ArrayList<String>>> {
        return api.getCategories()
    }

    fun setPick(x:String){
        this.selection = x
    }

    suspend fun repoCategories(): Map<String, ArrayList<String>> {
        return repo.getCategories()
    }

}
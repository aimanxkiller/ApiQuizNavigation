package com.example.apiquiznavigation.models

data class QuestionModelItem(
    val category: String,
    val correctAnswer: String,
    val difficulty: String,
    val id: String,
    val incorrectAnswers: List<String>,
    val isNiche: Boolean,
    val question: String,
    val regions: List<Any>,
    val tags: List<String>,
    val type: String
)
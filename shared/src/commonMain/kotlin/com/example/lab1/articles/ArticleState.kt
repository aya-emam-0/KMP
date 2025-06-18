package com.example.lab1.articles

class ArticleState(
    val articles: List<ArticleRaw> = listOf(),
    val error: String? = null,
    val loading: Boolean = false
)
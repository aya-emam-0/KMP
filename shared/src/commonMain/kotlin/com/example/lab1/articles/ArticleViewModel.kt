package com.example.lab1.articles

import com.example.lab1.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ArticleViewModel(
    private var repo: ArticleRepository
) : BaseViewModel() {
    private var _articleStates: MutableStateFlow<ArticleState> =
        MutableStateFlow(ArticleState(loading = true))
    val articleState = _articleStates.asStateFlow()


    init {
        getAllArticles()
    }

    private fun getAllArticles() {
        scope.launch {
            val articlesData = repo.getArticles()
            _articleStates.emit(ArticleState(articles = articlesData))
        }
    }

}


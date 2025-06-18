package com.example.lab1.articles


class ArticleRepository(
    private val service: ArticlesService,
    private val database: ArticleLocalDataSource
) {

    suspend fun getArticles(): List<ArticleRaw> {
        val articlesDb = database.getAllArticles()
        if (articlesDb.isEmpty()){
            val fetchedArticle = service.fetchArticles()
            database.insertAllArticles(fetchedArticle)
            return fetchedArticle
        }
        return articlesDb
    }

}
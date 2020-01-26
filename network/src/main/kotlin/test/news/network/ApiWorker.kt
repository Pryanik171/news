package test.news.network

import io.reactivex.Single
import test.news.network.model.NewsResponse
import test.news.network.model.Response

interface ApiWorker {
    /**
     * Пагинированный список новостей
     */
    fun getNews(page: Int): Single<Response<NewsResponse>>
}

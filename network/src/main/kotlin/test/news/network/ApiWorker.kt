package test.news.network

import io.reactivex.Single
import test.news.network.model.Response

interface ApiWorker {

    fun getNews(page: Int): Single<Response<Any>>

}

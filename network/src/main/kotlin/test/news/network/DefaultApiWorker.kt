package test.news.network

import io.reactivex.Single
import test.news.network.model.NewsResponse
import test.news.network.model.Response

/**
 * @author Grigoriy Pryamov
 */
class DefaultApiWorker(
    private val api: Api,
    private val responseConverter: ResponseConverter
) : ApiWorker {

    override fun getNews(page: Int): Single<Response<NewsResponse>> {
        return api.getNews(page).map { response -> responseConverter.convert(response) }
    }
}
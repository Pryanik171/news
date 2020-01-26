package test.news.network

import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author Grigoriy Pryamov
 */
interface Api {
    /**
     * Пагинированный список новостей
     */
    @GET
    fun getNews(@Query("page") page: Int): Single<Response<Any>>
}